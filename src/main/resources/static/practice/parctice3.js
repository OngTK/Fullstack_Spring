console.log("practice3 js exe")

// [1] 대기등록
const waitingRegi = () => {
    console.log("waitingRegi exe")

    // [1.1] 샘플 data
    let data = { "phone": "010-9999-9999", "count": 3 }
    // [1.2] fetch option
    let option = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    }
    // [1.3] fetch 실행
    fetch("/waiting", option)
        .then(r => r.json())
        .then(d => { console.log(d) })
        .catch(e => { console.log(e) })
}

// [2] 대기조회
const waitingPrint = () => {
    console.log("waitingPrint exe")
    // [2.1] 샘플 data : x
    // [2.2] fetch option 
    let option = { method: "GET" }
    // [2.3] fetch 실행
    fetch("/waiting", option)
        .then(r => r.json())
        .then(d => { console.log(d) })
        .catch(e => { console.log(e) })

}

// [3] 대기삭제
const waitingDelete = () => {
    console.log("waitingDelete exe")
    // [3.1] 샘플 data
    let wno = 2;
    // [3.2] fetch option
    let option = { method: "DELETE" }
    // [3.3] fetch 실행
    fetch(`/waiting?wno=${wno}`,option)
        .then(r => r.json())
        .then(d => {console.log(d)})
        .catch(e => {console.log(e)})

}

// [4] 대기수정
const waitingUpdate = () => {
    console.log("waitingUpdate exe")
    // [4.1] 샘플 data
    let data = { "wno": 2, "count":3 } 
    // [4.2] fetch option
    let option = {
        method : "PUT",
        headers : {"Content-Type":"application/json"},
        body : JSON.stringify(data)
    }
    // [4.3] fetch 실행
    fetch("/waiting",option)
        .then(r => r.json())
        .then(d => {console.log(d)})
        .catch(e => {console.log(e)})

}