console.log("boardPrint js exe")

const boardPrint = async () => {
    console.log("boardPrint func exe")
    // [1] html 가져오기
    const boardTbody = document.querySelector("#boardTbody")

    // [2] Fetch 
    // [2.1] data / 생략
    // [2.2] option
    const option = {method : "GET"}
    // [2.3] fetch
    const response = await fetch("/board",option)
    const data = await response.json()
    console.log(data)

    let html = "";
    // [3] 결과 반환
    for(let i =0 ; i < data.length ; i++){
        html += `<tr>
        <td>${data[i].bno}</td>
        <td>${data[i].bwriter}</td>
        <td><a href="/example/boardService12/boardFind.jsp?bno=${data[i].bno}">${data[i].bcontent}</a></td>
        </tr>`
    }

    boardTbody.innerHTML = html;    
}

boardPrint()