console.log('write js exe')

// [ Summer Note 연동 ]
$(document).ready(function () {
    $('#summernote').summernote({
        lang: 'ko-KR', // default: 'en-US'
        // 부가 기능
        minHeight : 300, 
        placeholder : "미리 입력되는 text",
    });
});

// [1] 글쓰기 등록
const onWrite = async () => {
    // 첨부파일이 있는 게시물 : multipart/form
    // 첨부파일 없고 내용에 text + img 포함 > editer templete

    const cno = document.querySelector(".cno").value;
    const ptitle = document.querySelector('.ptitle').value;
    const pcontent = document.querySelector('.pcontent').value;

    let obj = { cno, ptitle, pcontent }
    let option = {
        method: "POST",
        headers: { "Content-Type": "Application/json" },
        body: JSON.stringify(obj)
    }

    try {
        const r = await fetch("/post", option)
        const d = await r.json()
        if (d > 0) {
            alert("등록성공")
            location.href = `post.jsp?cno=${cno}`
        } else {
            alert("등록 실패")
        }
    } catch (error) {
        console.log(error)
    }

} // func end