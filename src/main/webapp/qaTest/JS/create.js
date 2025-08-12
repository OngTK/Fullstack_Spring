console.log("create js exe")

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


// [1] 회원번호 자동 발생

const maxCustNo = async () => {
    console.log("maxCustNo func exe")

    const option = { method: "GET" }
    const response = await fetch("/member/custNo", option)
    const data = await response.json()
    console.log(data)

    document.querySelector(".custNo").value = data
}
maxCustNo()

// [2] 회원 정보 등록
const memberCreate = async () => {
    console.log("memberCreate func exe")
    const custName = document.querySelector(".custName").value
    const phone = document.querySelector(".phone").value
    const address = document.querySelector(".address").value
    const joinDate = document.querySelector(".joinDate").value
    const grade = document.querySelector(".grade").value
    const city = document.querySelector(".city").value

    // 유효성 검사
    if (custName == "") {
        alert("회원성명이 입력되지 않았습니다.")
        return;
    }
    if (phone == "") {
        alert("회원 전화가 입력되지 않았습니다.")
        return;
    } 
    if (address == "") {
        alert("회원 주소가 입력되지 않았습니다.")
        return;
    } 
    if (joinDate == "") {
        alert("가입일자가 입력되지 않았습니다.")
        return;
    } 
    if (grade == "") {
        alert("고객등급이 입력되지 않았습니다.")
        return;
    } 
    if (city == "") {
        alert("도시코드가 입력되지 않았습니다.")
        return;
    } 

    const data = {
        "custName" : custName,
        "phone" : phone,
        "address" :address, 
        "joinDate" : joinDate,
        "grade" :grade ,
        "city" : city
    }
    const option = {method : "POST",
        headers : {"Content-Type":"application/json"},
        body : JSON.stringify(data)
    }
    const response = await fetch("/member", option)
    const d = await response.json()

    if(d==true){
        alert("회원 등록이 완료되었습니다.")
    } else {
        alert("회원 등록을 실패하였습니다.")
    }
}

const gotoList = () => {
    location.href = "/qaTest/list.jsp"
}