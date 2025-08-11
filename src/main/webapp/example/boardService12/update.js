// 번호 내용 출력
const boardFind = async () => {
    console.log("boardFind func exe")

    // [0] QueryString에서 bno 가져오기
    let url = new URLSearchParams(location.search)
    let bno = url.get('bno')
    console.log(bno)
    
    // [1] html 가져오기
    let bnoInput = document.querySelector("#bno")
    let bcontentInput = document.querySelector("#bcontent")
    let bwriterInput = document.querySelector("#bwriter")
    
    // [2] Fetch 
    // [2.1] data // 생략
        
    // [2.2] option
    const option = {method : "GET"}

    // [2.3] fetch
    const response = await fetch(`/board/find?bno=${bno}`,option)
    const data = await response.json()
    console.log(data)

    // [3] 결과 반환
    bnoInput.value = data.bno
    bcontentInput.value = data.bcontent
    bwriterInput.value = data.bwriter
}
boardFind()

// 수정하기 func
const boardUpdate = async () => {
    console.log("boardUpdate func exe")

    // [1] html 가져오기
    let bnoInput = document.querySelector("#bno")
    let bcontentInput = document.querySelector("#bcontent")

    let bno = bnoInput.value;
    let bcontent = bcontentInput.value;

    // [2] Fetch 
    // [2.1] data
    const data = {"bno":bno,"bcontent":bcontent}
        
    // [2.2] option
    const option = { method : "PUT",
        headers : { "Content-Type": "application/json" },
        body : JSON.stringify(data)
    }

    // [2.3] fetch
    const response = await fetch("/board",option)
    const d = await response.json()
    console.log(d)

    // [3] 결과 반환
    if( d == true ) {
        alert("수정 성공")
        location.href = `/example/boardService12/view.jsp?bno=${bno}`
    } else {
        alert("수정 실패")
    }

}