console.log("pointCharge js exe")

// [1] radio 버튼에서 누른 value를 가져오는 func
const onCharge = async () => {
    console.log("onCharge func exe")
    
    const pointChargeInput = document.querySelector('input[name="pointCharge"]:checked')
    const pointCharge = pointChargeInput.value;

    console.log(pointCharge)
} // func end


// const checkout = new Checkout(
//     "store-a09be0fd-0564-4c1f-9345-9e177643addd", // 상점 ID
//     "channel-key-c9cde3ea-0844-41c9-bc9c-4dc54c6f4804", // 채널 키
// )

// checkout.load()

// function Checkout(storeId, channelKey) {
//     let item = null
//     this.load = async () => {
//         const waitPortOne = new Promise((resolve) => {
//             const polling = setInterval(() => {
//                 if (window.PortOne != null) {
//                     clearInterval(polling)
//                     resolve()
//                 }
//             }, 50)
//         })
//         const waitItem = await fetch("/api/item").then(
//             async (response) => (item = await response.json()),
//         )
//         await Promise.all([waitPortOne, waitItem])
//         window.loadingDialog.open = false
//         window.checkoutDialog.open = true
//         await this.showCheckout()
//     }
//     this.showCheckout = async () => {
//         window.itemName.replaceChildren(item.name)
//         window.itemImage.src = `/${item.id}.png`
//         for (const priceElement of document.querySelectorAll(
//             ".price-value",
//         )) {
//             priceElement.replaceChildren(`${item.price.toLocaleString()}원`)
//         }
//         window.checkoutDialog.onsubmit = async (e) => {
//             e.preventDefault()
//             this.setWaitingPayment(true)
//             const paymentId = randomId()
//             console.log(item.currency)
//             const payment = await PortOne.requestPayment({
//                 storeId,
//                 channelKey,
//                 paymentId,
//                 orderName: item.name,
//                 totalAmount: item.price,
//                 currency: item.currency,
//                 payMethod: "CARD",
//                 customData: {
//                     item: item.id,
//                 },
//             })
//             if (payment.code != null) {
//                 this.setWaitingPayment(false)
//                 console.log(payment)
//                 this.openFailDialog(payment.message)
//                 return
//             }
//             const completeResponse = await fetch("/api/payment/complete", {
//                 method: "POST",
//                 headers: {
//                     "Content-Type": "application/json",
//                 },
//                 body: JSON.stringify({
//                     paymentId: payment.paymentId,
//                 }),
//             })
//             this.setWaitingPayment(false)
//             if (completeResponse.ok) {
//                 const paymentComplete = await completeResponse.json()
//                 switch (await paymentComplete.status) {
//                     case "PAID":
//                         this.openSuccessDialog()
//                         break
//                     case "VIRTUAL_ACCOUNT_ISSUED":
//                         this.openVirtualAccountDialog()
//                         break
//                 }
//             } else {
//                 this.openFailDialog(await completeResponse.text())
//             }
//         }
//         for (const dialogButton of document.getElementsByClassName(
//             "closeDialog",
//         )) {
//             dialogButton.onclick = () => {
//                 dialogButton.closest('dialog').open = false
//             }
//         }
//         window.checkoutDialog.style = ""
//     }

//     this.setWaitingPayment = (isWaiting) => {
//         window.checkoutButton.setAttribute("aria-busy", isWaiting.toString())
//         window.checkoutButton.disabled = isWaiting
//     }
//     this.openFailDialog = (message) => {
//         window.failMessage.replaceChildren(message)
//         window.failDialog.open = true
//     }
//     this.openSuccessDialog = () => {
//         window.successDialog.open = true
//     }
//     this.openVirtualAccountDialog = () => {
//         window.virtualAccountDialog.open = true
//     }
//     function randomId() {
//         return [...crypto.getRandomValues(new Uint32Array(2))]
//             .map((word) => word.toString(16).padStart(8, "0"))
//             .join("")
//     }
// }