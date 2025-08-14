console.log('pwdipdate js exe')

const updatePassword = async () => {
    console.log("updatePassword func exe")
    const oldPwdInput = document.querySelector('#oldpwd')
    const newPwdInput = document.querySelector('#newpwd')
    const oldPwd = oldPwdInput.value;
    const newPwd = newPwdInput.value;

    try {
        const obj = { "oldPwd": oldPwd, "newPwd": newPwd }
        const option = {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(obj)
        }
        const response = await fetch("/member/update/password", option);
        const data = await response.json();

        if (data) {
            alert("수정 성공")
            location.href = "/member/info.jsp"
        } else {
            alert("수정 실패")
        }
    } catch (error) {
        console.log(error)
        alert("[경고] 관리자에게 문의하세요.")
        location.href = '/member/login.jsp'
    }
} // func end