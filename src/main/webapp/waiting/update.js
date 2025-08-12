// 대기 정보 보기
const waitingView = async () => {
    console.log("waitingView func exe")

    // [0] QueryString 읽기
    let wno = new URLSearchParams(location.search).get("wno")
    console.log(wno)

    // [1] html 불러오기 / 생략

    // [2] Fetch 
    // [2.1] data
    // [2.2] option
    const option = { method: "GET" }
    // [2.3] fetch
    const response = await fetch(`/waiting/view?wno=${wno}`, option)
    const d = await response.json()
    console.log(d)

    // [3] 결과 반환
    document.querySelector("#wno").value = d.wno
    document.querySelector("#phone").value = d.phone
    document.querySelector("#count").value = d.count
    document.querySelector("#wdate").value = d.wdate
}
waitingView()

// 대기 정보 수정
const waitingUpdate = async () => {
    console.log("waitingUpdate func exe")

    // [1] html 불러오기
    const wno = document.querySelector("#wno").value
    const phone = document.querySelector("#phone").value
    const count = document.querySelector("#count").value
    // [2] Fetch 

    // [2.1] data
    const data = {"wno":wno,"phone":phone, "count":count}
    // [2.2] option
    const option = {method : "PUT",
        headers : {"Content-Type":"application/json"},
        body : JSON.stringify(data)
    }    
    // [2.3] fetch
    const response = await fetch("/waiting",option)
    const d = await response.json()

    // [3] 결과 반환
    if (d == true){
        alert("수정 성공")
        location.href = "/waiting/list.jsp"
    } else {
        alert("수정 실패")
    }
}
