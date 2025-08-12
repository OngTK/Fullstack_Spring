console.log("list js exe")

const waitingList = async() => {

    // [1] html 불러오기
    const waitingTbody = document.querySelector("#waitingTbody")

    // [2] Fetch 
    // [2.1] data / 생략
    // [2.2] option
    const option = { method : "GET"}
    // [2.3] fetch
    const response = await fetch("/waiting",option)
    const d = await response.json()
    console.log(d)

    // [3] 결과 반환
    let html = ""
    for (let i = 0 ; i < d.length ; i++ ){
        html += `
        <tr>
        <td><a href="/waiting/view.jsp?wno=${d[i].wno}">${d[i].wno}</a></td>
        <td>${d[i].phone}</td>
        <td>${d[i].count}</td>
        <td>${d[i].wdate}</td>
        </tr>
        `
    }

    waitingTbody.innerHTML = html
}
waitingList()

const transferWirte = () =>{
    location.href = "/waiting/write.jsp"
}