console.log("find ID PWD JS exe")

// [1] 아이디 찾기
const findID = async () => {
    console.log("findID func exe")

    const mnameInput = document.querySelector('#nameInput')
    const mphoneInput = document.querySelector('#phoneInput')
    const mname = mnameInput.value;
    const mphone = mphoneInput.value;

    const findIDOutput = document.querySelector("#findIDOutput")

    let html = "";
    try {
        const option = { method: "GET" }
        const response = await fetch(`/member/findID?mname=${mname}&mphone=${mphone}`, option);
        const data = await response.text() // java에서 String으로 전달했으므로, text로 받음
        console.log(data)

        html += `<div>확인 아이디</div>
        <div style="font-size:1.5rem; font-weight:600;">${data}</div>`

        findIDOutput.innerHTML = html;
    } catch (error) {
        console.log(error)
        html += `아이디가 확인되지 않습니다. 이름과 연락처를 다시 확인해주세요.`
        findIDOutput.innerHTML = html;
    }
} // func end

// [2] 비밀번호 찾기

const findPwd = async () => {
    console.log('findpwd func exe')
    
    const idInput = document.querySelector('#idInput')
    const mphoneInput = document.querySelector('#phoneInput2')
    const mid = idInput.value;
    const mphone = mphoneInput.value;

    const newpwd = document.querySelector('#newpwd')

    let html = "";
    try {
        const option = { method: "GET" }
        const response = await fetch(`/member/findPwd?mid=${mid}&mphone=${mphone}`, option);
        const data = await response.text();
        // data가 단일 String 이므로 .json() 이 아닌 .text()로 받음
        console.log(data)

        html += `<div>임시 비밀번호 발급</div>
        <div style="font-size:1.5rem; font-weight:600;">${data}</div>`

        newpwd.innerHTML = html;

    } catch (error) {
        console.log(error)
        html += `아이디와 연락처가 일치하는 계정정보가 없습니다. 다시 확인해주세요.`
        newpwd.innerHTML = html;
    }

}