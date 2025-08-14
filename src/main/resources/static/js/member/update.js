console.log("update js exe")

// [1] 내 정보 조회 - 회원 정보 표시
const oninfo = async () => {
    console.log("info func exe")

    // [1.1] http 가져오기
    const mnoInput = document.querySelector("#mno")
    const midInput = document.querySelector("#mid")
    const mnameInput = document.querySelector("#mname")
    const mphoneInput = document.querySelector("#mphone")
    const mdateInput = document.querySelector("#mdate")

    // [1.2] fetch
    try {
        const option = { method: "GET" }
        const response = await fetch("/member/info", option);
        const data = await response.json();
        console.log(data)

        mnoInput.value = data.mno;
        midInput.value = data.mid;
        mnameInput.value = data.mname;
        mphoneInput.value = data.mphone;
        mdateInput.value = data.mdate;

    } catch { // 비로그인 상태
        alert("[경고] 관리자에게 문의하세요.")
        location.href = "/member/login.jsp"
    }
} // func end
oninfo()

// [2] 수정 정보 저장

const update = async () => {
    console.log('update func exe')

    const mnameInput = document.querySelector("#mname")
    const mphoneInput = document.querySelector("#mphone")
    const mname = mnameInput.value;
    const mphone = mphoneInput.value;
    
    try {
        const obj = {mname, mphone}
        const option = {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(obj)
        }
        const response = await fetch("/member/update", option);
        const data = await response.json();
        console.log(data)

        if(data){
            alert("수정 성공")
            location.href = "/member/info.jsp"
        } else {
            alert("수정 실패")
        }
    } catch {
        alert("[경고] 관리자에게 문의하세요.")
        location.href = "/member/login.jsp"
    }
}

// [3] 연락처 중복검사
const phoneCheck = async () => {
    console.log("phonecheck func exe")

    // [3.1] JSP 영역 가져오기
    const phoneInput = document.querySelector("#mphone")
    const mphone = phoneInput.value;

    const phoneCheck = document.querySelector(".phoneCheck")

    let html = ""

    // [2.2] 길이 검사
    if( mphone.length != 13 ){
        html += `<span style="color:red">하이픈(-)을 포함한 13자리로 입력해주세요.</span>`
        phoneCheck.innerHTML = html;
        return;
    }

    // [2.2] Fetch
    try {
        const option = { method: "GET" }
        const response = await fetch(`/member/check?type=mphone&data=${mphone}`, option);
        const data = await response.json();

        // [2.3] 결과
        if (data) {
            html += `<span style="color:red">이미 등록된 전화번호입니다.</span>`
        } else {
            html += `<span style="color:blue">사용 가능한 전화번호입니다.</span>`
        }
    } catch(error) {
        alert("[경고] 관리자에게 문의하세요")
        console.log(error)
    }
    phoneCheck.innerHTML = html;
}
// 연락처 중복검사에 따라, update() func의 실행여부를 조절하는 기능 추가 필요