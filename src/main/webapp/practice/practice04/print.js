console.log("print js exe")

const print = async () => {
    console.log("print func exe")
    // [1] 출력 위치 가져오기
    const waitingTbody = document.querySelector("#waitingTbody")
  
    // [2] Fetch
    // [2.1] data / 없음 
    // [2.2] option
    const option = {method : "GET"}
    // [2.3] 동기식 fetch 실행
    const response = await fetch("/waiting/print",option);
    const data = await response.json();
    console.log(data)

    // [3] 결과 반환
    html = ""
    for( let i = 0 ; i < data.length ; i++ ){
        html += `<tr>
        <td>${data[i].wno}</td>
        <td>${data[i].phone}</td>
        <td>${data[i].count}</td>
        </tr>`
    }
    waitingTbody.innerHTML=html
} // func end

print()