console.log("list js exe")

// [0] 고객 등급 변환 함수
const changeGrade = (grade) => {
    if (grade == "A") {
        return "VIP"
    } else if (grade == "B") {
        return "일반"
    } else if (grade == "C") {
        return "직원"
    } else if (grade == "VIP") {
        return "A"
    } else if (grade == "일반") {
        return "B"
    } else if (grade == "직원") {
        return "C"
    }
}

// 회원정보 출력

const memberRead = async () => {

    const memberTbody = document.querySelector("#memberTbody")

    const option = {method : "GET"}

    const response = await fetch("/member",option)
    const data = await response.json()

    html = ""
    for (let i = 0 ; i < data.length ; i++){

        const grade = changeGrade(data[i].grade)

        html += `<tr>
        <td><a href="/qaTest/update.jsp?custNo=${data[i].custNo}">${data[i].custNo}</a></td>
        <td>${data[i].custName}</td>
        <td>${data[i].phone}</td>
        <td>${data[i].address}</td>
        <td>${data[i].joinDate}</td>
        <td>${grade}</td>
        <td>${data[i].city}</td>
        </tr>`
    }

    memberTbody.innerHTML = html;
}
memberRead()