console.log("save.js exe")

// 저장
const save = async () => {
    console.log("save func exe")

    // [1] html에서 매개변수 호출
    const snameInput = document.querySelector(".sname")
    const skorInput = document.querySelector(".skor")
    const smathInput = document.querySelector(".smath")

    const sname = snameInput.value;
    const skor = skorInput.value;
    const smath = smathInput.value;

    // [2] 동기식 fetch 실행
    // [2.1] data
    let data = {
        sname: sname,
        skor: skor,
        smath: smath
    }   // 속성명과 속성값이 같을 경우, 생략 가능

    // [2.2] option
    let option = {
        method: "POST",
        headers: {"Content-Type" : "application/json"} ,
        body: JSON.stringify(data)
    }
    // [2.3] fetch
    const response = await fetch("/student/save",option)
    const d = await response.json();
    console.log(d)

    // [3] 결과
    if(d == true){
        alert("등록 성공")
        location.href="/student/find.jsp"
    } else {
        alert("등록 실패")
    }

} // func end
