console.log('getPost js exe')

// [0] 쿼리스트링에서 pno 가져오기
const params = new URL(location.href).searchParams;
const pno = params.get("pno");

// [1] 게시물 개별조회
// 쿼리스트링으로 pno를 받아옴
const getPost = async () => {
    console.log('getPost func exe')

    // [1.1] fetch로 받아온 data를 출력할 영역 가져오기
    const pnoOutput = document.querySelector("#pno")
    const pdateOutput = document.querySelector("#pdate")
    const pviewOutput = document.querySelector("#pview")
    const ptitleOutput = document.querySelector("#ptitle")
    const cnoOutput = document.querySelector("#cno")
    const midOutput = document.querySelector("#mid")
    const pcontentOutput = document.querySelector("#pcontent")

    // [1.2] Fetch
    try {
        const opt = { method: "GET" }
        const r = await fetch(`/post/view?pno=${pno}`, opt);
        const d = await r.json();
        console.log(d)

        // [1.3] HTML 출력
        pnoOutput.innerHTML = `${d.pno}`
        pdateOutput.innerHTML = `${d.pdate}`
        pviewOutput.innerHTML = `${d.pview}`
        ptitleOutput.innerHTML = `${d.ptitle}`
        cnoOutput.innerHTML = `${d.cno}`
        midOutput.innerHTML = `${d.mid}`
        pcontentOutput.innerHTML = `${d.pcontent}`

        // [1.4] 본인의 글이면 수정/삭제 버튼 생성
        if( d.host == true ){
            document.querySelector(".etcBtn").innerHTML = `
            <button type="button" onclick="location.href='update.jsp?pno=${pno}'"> 수정하기 </button>
            <button type="button" onclick="deletePost()"> 삭제하기 </button>`
        }
    } catch (error) {
        console.log(error)
    }
} // func end
getPost()

// [4] 게시물 삭제

const deletePost = async () => {


}

// [2] 댓글 조회
const findAllReply = async () => {
        console.log('findAllReply func exe')

    const replyPrintArea = document.querySelector("#replyPrintArea")

    // [2.1] fetch
    try {
        const opt = { method: "GET" }
        const r = await fetch(`/post/reply?pno=${pno}`, opt);
        const d = await r.json();
        console.log(d)

        // [2.2] 화면에 프린트
        let html = "";

        d.forEach(value => {
            html += `<tr>
            <td>${value.rno}</td>
            <td>${value.rdate}</td>
            <td>${value.rcontent}</td>
            <td>${value.mid}</td>
            </tr>`
        });
        replyPrintArea.innerHTML = html;
    } catch (error) {
        console.log(error)
    }
} // func end
findAllReply()

// [3] 댓글쓰기
const writeReply = async () => {
        console.log('writeReply func exe')

    // [3.1] 댓글 가져오기
    const rcontentInput = document.querySelector("#rcontent");
    const rcontent = rcontentInput.value;
    try {
        // [3.2] fetch
        const obj = { rcontent , pno }
        const opt = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(obj)
        }
        const r = await fetch("/post/reply", opt)
        const d = await r.json()
        console.log(d)

        if (d > 0) {
            alert("댓글 작성 성공")
            findAllReply()
        } else {
            alert("댓글 작성 실패")
        }
    } catch (error) {
        console.log(error)
    }

} //func end
