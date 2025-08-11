console.log("boardWrite js exe")

const boardWrite = async () => {
    console.log("boardWrite func exe")

    // [1] html 가져오기
    const bcontentInput = document.querySelector("#bcontent")
    const bwriterInput = document.querySelector("#bwriter")

    const bcontent = bcontentInput.value;
    const bwriter = bwriterInput.value;

    // [2] Fetch 
    // [2.1] data
    const data = {"bcontent":bcontent,"bwriter":bwriter}
    // [2.2] option
    const option = {
        method : "POST",
        headers : { "Content-Type": "application/json" },
        body : JSON.stringify(data)
        }
    // [2.3] fetch
    const response = await fetch("/board",option)
    const d = await response.json()
    
    // [3] 결과 반환
    if (d == true) {
        alert("등록 성공")
    } else {
        alert("등록 실패")
    }

    location.href = "/example/boardService12/list.jsp"
}
