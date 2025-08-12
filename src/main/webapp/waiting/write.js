console.log("waiting wirte js exe")

const waitingWrite = async () => {
    console.log("waitingWrite func exe")

    // [1] html 불러오기
    const phone = document.querySelector("#phone").value
    const count = document.querySelector("#count").value

    // [2] Fetch 
    // [2.1] data
    const data = { "phone":phone , "count": count}
    // [2.2] option
    const option = { method : "POST",
        headers : {"Content-Type":"application/json"},
        body : JSON.stringify(data)
    }
    // [2.3] fetch
    const response = await fetch("/waiting",option)
    const d = await response.json()
    console.log(d)
    
    // [3] 결과 반환
    if( d == true ){
        alert("등록 성공")
        location.href = "/waiting/list.jsp"
    } else{
        alert("등록 실패")
    }
}