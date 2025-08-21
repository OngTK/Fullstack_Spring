console.log("porintChargeTest js exe")

const checkout = new Checkout(
    "store-a09be0fd-0564-4c1f-9345-9e177643addd", // 상점 ID
    "channel-key-c9cde3ea-0844-41c9-bc9c-4dc54c6f4804", // 채널 키
)

checkout.load()

function Checkout(storeId, channelKey) {
    let item = null
    this.load = async () => {
        const waitPortOne = new Promise((resolve) => {
            const polling = setInterval(() => {
                if (window.PortOne != null) {
                    clearInterval(polling)
                    resolve()
                }
            }, 50)
        })

        // 상품정보 불러오기
        // 서버에서 결제할 상품 정보를 불러옴 >> DB에 저장된 장바구니 속 상품을 의미
        // 화면단에서 Input의 radio 버튼으로 교체
        const waitItem = await fetch("/api/item").then(
            async (response) => (item = await response.json()),
        )
        await Promise.all([waitPortOne, waitItem])
        window.loadingDialog.open = false
        window.checkoutDialog.open = true
        await this.showCheckout()
    }

    this.showCheckout = async () => {
        window.itemName.replaceChildren(item.name)
        window.itemImage.src = `/${item.id}.png`
        for (const priceElement of document.querySelectorAll(
            ".price-value",
        )) {
            priceElement.replaceChildren(`${item.price.toLocaleString()}원`)
        }
        window.checkoutDialog.onsubmit = async (e) => {
            e.preventDefault()
            this.setWaitingPayment(true)

            // 결제요청 
            // 포트원 SDK를 사용하여 결제를 요청
            const paymentId = randomId()
            console.log(item.currency)
            const payment = await PortOne.requestPayment({
                storeId,                    // 상점ID, 위에서 기재했음
                channelKey,                 // 채널Key, 위에서 기재했음
                paymentId,                  // String 고객사 주문 고유번호, 위에서 randomId()로 발생
                orderName: item.name,
                totalAmount: item.price,
                currency: item.currency,    // 화폐
                payMethod: "CARD",          // 결제수단
                customer: {                 // 구매자정보?
                    fullName: "포트원",
                    email: "example@portone.io",
                    phoneNumber: "01012341234",
                },
                customData: {
                    item: item.id,
                },
            })

            // 결제 중 오류 처리 
            if (payment.code != null) {
                this.setWaitingPayment(false)
                console.log(payment)
                this.openFailDialog(payment.message)
                return
            }

            // 서버 측으로 결제 완료 요청
            const completeResponse = await fetch("/api/payment/complete", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    paymentId: payment.paymentId,
                }),
            })
            this.setWaitingPayment(false)
            if (completeResponse.ok) {
                // 결제완료 상태 처리
                const paymentComplete = await completeResponse.json()
                switch (await paymentComplete.status) {
                    case "PAID":
                        this.openSuccessDialog()
                        break
                    case "VIRTUAL_ACCOUNT_ISSUED":
                        this.openVirtualAccountDialog()
                        break
                }
            } else {
                // 결제 실패 상태 처리
                this.openFailDialog(await completeResponse.text())
            }
        }
        for (const dialogButton of document.getElementsByClassName(
            "closeDialog",
        )) {
            dialogButton.onclick = () => {
                dialogButton.closest('dialog').open = false
            }
        }
        window.checkoutDialog.style = ""
    }

    this.setWaitingPayment = (isWaiting) => {
        window.checkoutButton.setAttribute("aria-busy", isWaiting.toString())
        window.checkoutButton.disabled = isWaiting
    }
    this.openFailDialog = (message) => {
        window.failMessage.replaceChildren(message)
        window.failDialog.open = true
    }
    this.openSuccessDialog = () => {
        window.successDialog.open = true
    }
    this.openVirtualAccountDialog = () => {
        window.virtualAccountDialog.open = true
    }
    function randomId() {
        return [...crypto.getRandomValues(new Uint32Array(2))]
            .map((word) => word.toString(16).padStart(8, "0"))
            .join("")
    }
}


// server.js============================
const express = require("express")
const bodyParser = require("body-parser")
const PortOne = require("@portone/server-sdk")

const portone = PortOne.PortOneClient({ secret: process.env.V2_API_SECRET })

const app = express()

app.use("/api/payment/webhook",
    bodyParser.text({ type: "application/json", }),
)
app.use(bodyParser.json())

const items = new Map([["shoes", {
    name: "신발",
    price: 1000,
    currency: "KRW",
},],])

app.get("/api/item", (req, res) => {
    const id = "shoes"    res.json({
        id, ...items.get(id),
    })
})

app.post("/api/payment/complete", async (req, res, next) => {
    try {
        const { paymentId } = req.body
        if (typeof paymentId !== "string")
            return res.status(400).send("올바르지 않은 요청입니다.").end()
        const payment = await syncPayment(paymentId)
        if (!payment) return res.status(400).send("결제 동기화에 실패했습니다.")
        res.status(200).json({
            status: payment.status,
        })
    } catch (e) {
        next(e)
    }
})

const paymentStore = new Map()
async function syncPayment(paymentId) {
    if (!paymentStore.has(paymentId)) {
        paymentStore.set(paymentId, {
            status: "PENDING",
        })
    }
    const payment = paymentStore.get(paymentId)
    let actualPayment
    try {
        actualPayment = await portone.payment.getPayment({ paymentId })
    } catch (e) {
        if (e instanceof PortOne.Errors.PortOneError) return false
        throw e
    }
    if (actualPayment.status === "PAID") {
        if (!verifyPayment(actualPayment)) return false
        if (payment.status === "PAID") return payment
        payment.status = "PAID"
        console.info("결제 성공", actualPayment)
    } else {
        return false
    }
    return payment
}

function verifyPayment(payment) {
    if (payment.channel.type !== "LIVE") return false
    if (payment.customData == null) return false
    const customData = JSON.parse(payment.customData)
    const item = items.get(customData.item)
    if (item == null) return false
    return (
        payment.orderName === item.name &&
        payment.amount.total === item.price &&
        payment.currency === item.currency
    )
}

app.post("/api/payment/webhook", async (req, res, next) => {
    try {
        let webhook
        try {
            webhook = await PortOne.Webhook.verify(
                process.env.V2_WEBHOOK_SECRET,
                req.body,
                req.headers,
            )
        } catch (e) {
            if (e instanceof PortOne.Webhook.WebhookVerificationError)
                return res.status(400).end()
            throw e
        }
        if ('data' in webhook && 'paymentId' in webhook.data)
            await syncPayment(webhook.data.paymentId)
        res.status(200).end()
    } catch (e) {
        next(e)
    }
})

const server = app.listen(8080, "localhost", () => {
    console.log("server is running on", server.address())
})