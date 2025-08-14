console.log("login js exe")

// [1] 로그인
const login = async() =>{
    console.log("login func exe")

    // [1.1] 입력 아이디/패스워드 가져오기
    const idInput = document.querySelector("#idInput");
    const pwdInput = document.querySelector("#pwdInput");

    const mid = idInput.value;
    const mpwd = pwdInput.value;

    // [1.2] Fetch
    try{
        const obj = {mid , mpwd}
        const option = { 
            method : "POST",
            headers : { "Content-Type" : "application/json"},
            body : JSON.stringify( obj )
        }
        const response = await fetch( "/member/login" , option );
        const data = await response.json();
        console.log(data)

        // [1.3] Fetch 결과
        if( data > 0 ){
            alert("로그인 성공")
            location.href = "/index.jsp"
        } else {
            alert("[로그인 실패] 아이디·비밀번호를 확인해주세요.")
        }
    }catch{
        alert("[오류발생] 관리자에게 문의하세요.")
    }
} // func end