console.log("signup js exe")
// *** 유효성 검사 체크리스트 ***
// index 0 : idCheck / 1 : phone 
const signPass = [false, false]

// [1] 회원가입
const signup = async () => {
    console.log("signup exe func")

    // [1.0] signPass 유효성 검사 확인하기
    if(signPass.includes(false)){
        alert("[경고] 유효성 검사에 오류가 있습니다. 올바른 정보를 입력해주세요.")
        return;
    } 

    // [1.1] 입력정보 가져오기
    const idInput = document.querySelector("#idInput")
    const pwdInput = document.querySelector("#pwdInput")
    const nameInput = document.querySelector("#nameInput")
    const phoneInput = document.querySelector("#phoneInput")

    const mid = idInput.value;
    const mpwd = pwdInput.value;
    const mname = nameInput.value;
    const mphone = phoneInput.value;

    try {
        const obj = { mid, mpwd, mname, mphone }
        const option = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(obj)
        }
        const response = await fetch("/member/signup", option);
        const data = await response.json();
        console.log(data)

        if (data > 0) {
            alert("회원가입 성공")
            location.href = "/member/login.jsp"
        } else {
            alert("회원가입 실패")
        }
    } catch {
        alert("[경고] 관리자에게 문의하세요.")
    }
} // func end

// [2] 아이디 중복검사
const idCheck = async () => {
    console.log("idcheck func exe")

    // [2.1] JSP 영역 가져오기
    const idInput = document.querySelector("#idInput")
    const mid = idInput.value;
    const idCheck = document.querySelector(".idCheck")

    let html = ""

    // [2.2] ID 길이 유효성 검사
    if(mid.length<6){
        html += `<span style="color:red">아이디는 6글자 이상으로만 가능합니다.</span>`
        signPass[0] = false;
        idCheck.innerHTML = html
        return;
    }

    // [2.3] Fetch
    try {
        const option = { method: "GET" }
        const response = await fetch(`/member/check?type=mid&data=${mid}`, option);
        const data = await response.json();

        // [2.4] 결과
        if (data) {
            html += `<span style="color:red">이미 존재하는 아이디입니다.</span>`
                    signPass[0] = false;
        } else {
            html += `<span style="color:blue">사용 가능한 아이디입니다.</span>`
                    signPass[0] = true;
        }
    } catch {
        alert("[경고] 관리자에게 문의하세요")
    }
    idCheck.innerHTML = html;
}

// [3] 연락처 중복검사
const phoneCheck = async () => {
    console.log("phonecheck func exe")

    // [2.1] JSP 영역 가져오기
    const phoneInput = document.querySelector("#phoneInput")
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
            signPass[1] = false;
        } else {
            html += `<span style="color:blue">사용 가능한 전화번호입니다.</span>`
            signPass[1] = true;
        }
    } catch(error) {
        alert("[경고] 관리자에게 문의하세요")
        console.log(error)
    }
    phoneCheck.innerHTML = html;
}

