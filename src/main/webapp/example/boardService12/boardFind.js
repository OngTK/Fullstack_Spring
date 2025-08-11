console.log("boardFind js exex")

const boardFind = async () => {
    
    // [1] html 가져오기
    let bnoInput = document.querySelector("#bno")
    let bcontentInput = document.querySelector("#bcontent")
    let bwriterInput = document.querySelector("#bwirter")

    let bno = bnoInput.value;

    
    // [2] Fetch 
    // [2.1] data // 생략
        
    // [2.2] option
    const option = {
        method : "GET",
    }
    // [2.3] fetch
    const response = await fetch(`/board/find?bno=${bno}`)
    const data = await response.json()
    console.log(data)

    // [3] 결과 반환
    bcontentInput.innerHTML(data.bwriter)
    bcontentInput.innerHTML(data.bcontent)
    
}