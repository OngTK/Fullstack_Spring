console.log("header js exe")

// [1] 내 정보 요청해서 메뉴 나누기
const myinfo = async () => {
    console.log("myinfo func exe")
    // [1.1] jsp 영역 불러오기
    const logMenu = document.querySelector("#log-menu")
    let html = ""

    try {
        // [1.2] Fetch
        // [1.2.1] data / 생략
        // [1.2.2] option
        const option = { method: "GET" }
        // [1.2.3] fetch
        const response = await fetch("/member/info", option)
        const d = await response.json()
        console.log(d)

        const point = await mnoPoint()
        console.log(point)
        // [1.3.1] 로그인 시, 정상 통신 fetch 
        html += `<li><span>${d.mname}님 (현재 포인트:</span> <span id="mnoPoint" style="color:#0E2841; font-weight:600;">${point}</span> 점) </li>
                <li><a href="/member/info.jsp">내 정보</a></li>
                <li><a href="#" onclick="logout()">로그아웃</a></li>`

    } catch {
        // [1.3.2] 비로그인 시, 비정상 통신 fetch 
        html += `<li><a href="/member/login.jsp">로그인</a></li>
                <li><a href="/member/signup.jsp">회원가입</a></li>`

        // 참고 
        // 비로그인 시 Java에서 반환은 null
        // null 은 .json() 실행시 오류를 반환함!
        // 따라서  비로그인 상황에 대한 결과는 catch 문에 작성
    }

    // [1.4] innerHTML 삽입
    logMenu.innerHTML = html;
} // func end
myinfo()

// [2] 로그아웃
const logout = async () => {
    console.log("logout func exe")

    try {
        // [2.1] fetch
        const option = { method: "GET" }
        const response = await fetch("/member/logout", option);
        const data = await response.json();

        // [2.2] 결과 
        if (data == true) {
            alert("로그아웃 했습니다.")
            location.href = "/index.jsp"
        } else {
            alert("비정상 요청입니다. 관리자에게 문의하세요.")
        }

    } catch {
    }
} // func end

// [3] 특정 mno의 포인트 합계 (※ 250819 추가)
const mnoPoint = async () => {
    console.log("mnoPoint func exe")
    const mnoPoint = document.querySelector("#mnoPoint")

    let html = ''
    try{
        const option = { method : "GET"}
        const r = await fetch("/point/totalpoint",option)
        const d = await r.text()
        console.log(d)
        html += `${d}`
    } catch (error){
        alert("[경고] 관리자에게 문의하세요. \t" + error)
    }

    return html;
} // func end

