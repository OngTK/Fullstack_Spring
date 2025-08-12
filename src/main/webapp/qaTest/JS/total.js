console.log("total js exe")

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


// 합계 출력 함수
const moneyRead = async () => {
    const option = { method: "GET" }
    const response = await fetch("/moneyprint", option)
    const data = await response.json()

    const totalTbody = document.querySelector("#totalTbody")
    let html = ""

    for (let i = 0; i < data.length; i++) {
        const grade = changeGrade(data[i].grade)

        html += `<tr>
        <td>${data[i].custNo}</td>
        <td>${data[i].custName}</td>
        <td>${grade}</td>
        <td>${data[i].totalPrice}</td>
        </tr>`
    }
    totalTbody.innerHTML = html
}
moneyRead()
