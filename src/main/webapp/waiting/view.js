console.log("view js exe")

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

// 대기 정보 삭제
const waitingDelete = async () => {
    // [1] html 불러오기
    let wno = new URLSearchParams(location.search).get("wno")

    // [2] Fetch 
    // [2.1] data / 생략    
    // [2.2] option
    const option = { method: "DELETE" }
    // [2.3] fetch
    const response = await fetch(`/waiting?wno=${wno}`, option)
    const d = await response.json()
    console.log(d)
    // [3] 결과 반환

    if (d == true){
        alert("삭제 성공")
        location.href = "/waiting/list.jsp"
    } else {
        alert("삭제 실패")
    }
}

// 수정하기로 이동
const gotoWaitingUpdate = () => {
    let wno = new URLSearchParams(location.search).get("wno")
    location.href = `/waiting/update.jsp?wno=${wno}`
}