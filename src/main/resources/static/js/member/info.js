console.log("info js exe");

// [1] 내 정보 조회
const info = async () => {
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

    } catch {
        alert("[경고] 관리자에게 문의하세요.")
    }
} // func end
info()

// [2] 회원 탈퇴
const onDelete =async() => {
    const mpwd = prompt("[안내] 회원삭제를 원하시면 비밀번호를 입력해주세요.")
    try{
        const option = { method : "DELETE"}
        const response = await fetch( `/member/delete?mpwd=${mpwd}` , option );
        const data = await response.json();

        if(data){
            alert("삭제 성공")
            location.href = "/index.jsp"
        } else {
            alert("삭제 실패")
        }
    }catch{
        alert("[경고] 관리자에게 문의하세요.")
    }

}