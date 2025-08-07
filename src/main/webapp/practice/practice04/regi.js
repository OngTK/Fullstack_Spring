console.log("regi js exe")

const regi = async () => {
    console.log("regi func exe")

    // [1] HTML에서 매개변수 가져오기
    const phoneInput = document.querySelector("#phone")
    const countInput = document.querySelector("#count")

    const phone = phoneInput.value;
    const count = countInput.value;

    let data = ""
    try {
        // [2] Fetch
        // [2.1] data
        const object = { "phone": phone, "count": count };
        // [2.2] option
        const option = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(object)
        }
        // [2.3] 동기식 fetch 실행
        const respose = await fetch("/waiting/regi", option)
        data = await respose.json()
    } catch (error) { console.log(error) }

    // [3] 결과 반환

    if (data == true) {
        alert("등록 성공")
        location.href = "/practice/practice04/print.jsp"
    } else {
        alert("등록 실패")
    }
}