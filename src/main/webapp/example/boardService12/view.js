console.log("boardFind js exe")

// 개별 게시물 조회

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
    const option = { method: "GET" }

    // [2.3] fetch
    const response = await fetch(`/board/find?bno=${bno}`, option)
    const data = await response.json()
    console.log(data)

    // [3] 결과 반환
    bnoInput.value = data.bno
    bcontentInput.value = data.bcontent
    bwriterInput.value = data.bwriter
}
boardFind()

// 개별 게시물 수정

const boardUpdateView = () => {
    console.log("boardUpdateView func exe")

    // [0] QueryString에서 bno 가져오기
    let url = new URLSearchParams(location.search)
    let bno = url.get('bno')

    // [2] 수정 페이지로 이동
    location.href = `/example/boardService12/update.jsp?bno=${bno}`

}

// 개별 게시물 삭제

const boardDelete = async () => {
    console.log("boardDelete func exe")

    // [1] html 가져오기
    const bno = document.querySelector('#bno').value;

    let check = confirm("정말 삭제할까요?")

    if (check = true) {
        // [2] Fetch 
        // [2.1] data  / 생략
        // [2.2] option
        const option = { method: "DELETE" }
        // [2.3] fetch
        const response = await fetch(`/board?bno=${bno}`, option)
        const data = await response.json()

        // [3] 결과 반환
        if (data == true) {
            alert("삭제 성공")
            location.href = "/example/boardService12/list.jsp"
        } else {
            alert("삭제 실패")
        }        
    }

}

