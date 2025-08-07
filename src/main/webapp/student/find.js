console.log("find.js exe")

// 전체 조회 func
const find = async () => {
    console.log("find func exe")
    
    // [1] fetch 실행
    // [1.1] data , 없음
    // [1.2] option
    option = { method: "GET" }
    // [1.3] 동기화 fetch
    const response = await fetch("/student/find", option)
    const data = await response.json();
    // console.log(data) -정상 출력 확인

    // [2] 출력
    // [2.1] studentTbody
    const studentTbody = document.querySelector("#studentTbody");

    // [2.2] fetch로 받은 결과로 html에 작성
    html = "";

    for( let i = 0 ; i < data.length ; i++){
        const student = data[i];

        html += `<tr>
        <td>${student.sno}</td>
        <td>${student.sname}</td>
        <td>${student.skor}</td>
        <td>${student.smath}</td>
        <td>${student.sdate}</td>
        </tr>`
    }

    // [2.3] html로 반환
    studentTbody.innerHTML = html;

} // func end

find(); // 함수 실행
