console.log("data js exe")

// [1] 부평구 주요소 현황 API / GET 방식
const dataAPI = async () => {

    // [1.1] 요청 URL 가져오기
    const URL = "https://api.odcloud.kr/api/15102672/v1/uddi:d26dabc4-e094-463d-a4b1-cab3af66bb6d?page=1&perPage=38&serviceKey="
    const serviceKey = "yTgyAULtBe8wrcJ7hrxA%2Bp52DqaIrbAbyIFPfKBWCrmnNshV1wHF4lP1S5bJaimGt6uLT8Qb3RPOQbyKyAdUiw%3D%3D"

    // [1.2] Fetch
    const option = { method: "GET" }
    const response = await fetch(URL + serviceKey, option)
    const data = await response.json()
    console.log(data)
    
    // [1.3] JSP/html 작업하기
    const dataTbody = document.querySelector("#dataTbody")
    let html = "";

    // [1.3.1] forEach문-반복문 사용하기
    data.data.forEach((value) => {
        html += `<tr>
            <td>${value.연번}</td>
            <td>${value.상호}</td>
            <td>${value.업종}</td>
            <td>${value.전화번호}</td>
            <td>${value.주소}</td>
        </tr>`
    })
    /* 위의 forEach와 동일
    for( let i = 0 ; i < data.data.length ; i++ ){
        let value = data.data[i]
    }
    */

    dataTbody.innerHTML = html;
}

dataAPI()

// [2] 사업자등록정보 상태조회 서비스 / POST 방식
const dataAPI2 = async () => {

    const URL = "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey="
    const serviceKey = "yTgyAULtBe8wrcJ7hrxA%2Bp52DqaIrbAbyIFPfKBWCrmnNshV1wHF4lP1S5bJaimGt6uLT8Qb3RPOQbyKyAdUiw%3D%3D"

    // [2.1] data 준비
    const bNoInput = document.querySelector(".b_no")
    const b_no = bNoInput.value

    const obj = { // var : 변수명 중복가능 변수 타입
        "b_no": [b_no]
    }
    const option = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(obj)
    }

    // [2.2] fetch
    const response = await fetch(URL + serviceKey, option)
    const data = await response.json()
    console.log(data)

    alert( data.data[0]["tax_type"])
    // 특수문자가 있을 경우, 접근연산자( . ) 사용불가
    // 대괄호 ( [] ) 안에 큰따옴표( "" )로 작성
}