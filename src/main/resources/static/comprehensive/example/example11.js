console.log("example11.js exe")

// [1] 등록 실행
// 주의! 람다식 함수 : const 함수명 = () => {}

const boardWrite = () => {
    console.log("boardWrite func exe")
    
    // fetch
    // (1) 등록할 샘플 data 준비
    let data = {"bcontent" : "hello world test2" , "bwriter":"강형호"}
    // (2) fetch 옵션 작성 : method / header / body
    let option = { 
        method : "POST",
        header : {"Content-Type" : "application/json"},
        body : JSON.stringify( data )
    }
    // (3) fetch 작성 : URL / 옵션 / .then(응답타입).then(응답자료).catch(오류 발생)
    fetch("/board", option)
    .then(response => response.json())
    .then(data => {console.log(data)})
    .catch(error => {console.log(error)})

}

// [2] 전체 조회 실행
const boardPrint = () => {
    console.log("boardPrint func exe")

}

// [3] 삭제 실행
const boardDelete = () => {
    console.log("boardDelete func exe")

}

// [4] 수정 실행
const boardUpdate = () => {
    console.log("boardUpdate func exe")

}
