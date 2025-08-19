console.log("info js exe");

// [1] 내 정보 조회
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

        mnoInput.innerHTML = data.mno;
        midInput.innerHTML = data.mid;
        mnameInput.innerHTML = data.mname;
        mphoneInput.innerHTML = data.mphone;
        mdateInput.innerHTML = data.mdate;

    } catch { // 비로그인 상태
        alert("[경고] 관리자에게 문의하세요.")
        location.href = "/member/login.jsp"
    }
} // func end
oninfo()

// [2] 회원 탈퇴
const onDelete = async () => {
    let result = confirm("[경고] 탈퇴 후 아이디를 복구할 수 없습니다. 정말로 탈퇴하시겠습니까?")
    if (result == flase) { return }

    const mpwd = prompt("[안내] 회원삭제를 원하시면 비밀번호를 입력해주세요.")
    try {
        const option = { method: "DELETE" }
        const response = await fetch(`/member/delete?mpwd=${mpwd}`, option);
        const data = await response.json();

        if (data) {
            alert("탈퇴 성공")
            location.href = "/index.jsp"
        } else {
            alert("탈퇴 실패") // pw불일치
        }
    } catch {
        alert("[경고] 관리자에게 문의하세요.")
    }
} // func end

// [3] 포인트 이력 조회 (※250819)
const pointlog = async () => {
    console.log("pointlog func exe")

    const pointTbody = document.querySelector("#pointTbody")
    let html = '';
    try {
        const option = { method: "GET" }
        const response = await fetch("/point/pointlog", option)
        const data = await response.json()
        console.log(data)

        data.forEach((value) => {
            html += `<tr>
                <td>${value.pldate}</td>
                <td>${value.plpoint}</td>
                <td>${value.plcomment}</td>
            </tr>`
        });

    } catch (error) {
        alert("[경고 관리자에게 문의하세요. \t" + error);
    }
    pointTbody.innerHTML = html;
} // func end
pointlog()