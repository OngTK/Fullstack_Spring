console.log("post js exe")

// [1.1] 사용자가 요청한 url에서 Cno 가져오기
const params = new URL(location.href).searchParams;
const cno = params.get("cno");
console.log(cno)

// [1.2] page 번호 가져오기
const page = params.get('page') || 1; // 조건이 거짓일 경우(존재하지 않으면) || 뒤에 값을 사용 

// [1.3] key, keyword 가져오기
const key = params.get('key') || '';            // key가 존재하지 않으면 공백
const keyword = params.get('keyword') || '';    // keyword가 존재하지 않으면 공백

// [2] 요청 매개변수를 이용한 fetch : 게시물 출력
const findAll = async () => {

    // [2.1] try-catch
    try {
        //[2.2] fetch
        const url = `/post?cno=${cno}&page=${page}&key=${key}&keyword=${keyword}`
        const option = { method: "GET" } // get 이므로 option 생략 가능
        const r = await fetch(url, option);
        const d = await r.json()
        console.log(d)

        // [2.3] html 출력
        const postTbody = document.querySelector(".postTbody")
        let html = '';
        d.data.forEach((post) => {
            html += `<tr>
                        <td>${post.pno}</td>
                        <td><a href="/post/getPost.jsp?pno=${post.pno}">${post.ptitle}</a></td>
                        <td>${post.mid}</td>
                        <td>${post.pdate}</td>
                        <td>${post.pview}</td>
                    </tr>`

        });
        postTbody.innerHTML = html

        // [3] page 버튼 출력 함수
        viewPageBtn(d)
    } catch (error) {
        console.log(error)
    }

} // func end

// [3] pageBtn 출력 함수
// findAll fun에 종속되어 실행
const viewPageBtn = async (data) => {
    console.log('viewPageBtn func exe')

    let currentPage = parseInt(data.currentPage);
    let totalPage = parseInt(data.totalPage);
    let startBtn = parseInt(data.startBtn);
    let endBtn = parseInt(data.endBtn);

    const pageBtnBox = document.querySelector(".pageBtnBox")
    let html = '';

    // [3.4] 검색 시 페이지 유지 처리
    const searchURL = `&key=${key}&keyword=${keyword}`

    // [3.1] 이전 버튼
    html += `<li>
        <a href="post.jsp?cno=${cno}&page=${currentPage == 1 ? 1 : currentPage - 1}${searchURL}">이전</a>
        </li>`
    // [ currentPage == 1 ? 1 : currentPage-1 ] 현재 페이지가 1이면 이전에서 0으로 가지 못하도록 막는 삼항연산자

    // [3.2] 페이지 버튼
    for (let i = startBtn; i <= endBtn; i++) {
        html += `<li>
        <a href="post.jsp?cno=${cno}&page=${i}${searchURL}" style="${i==currentPage?"color:blue":""}" > ${i} </a></li>`
    }

    // [3.3] 다음 버튼
    html += `<li>
    <a href="post.jsp?cno=${cno}&page=${currentPage + 1 >= totalPage ? totalPage : currentPage + 1}${searchURL}">다음</a>
    </li>`
    pageBtnBox.innerHTML = html;

} // func end

// [4] 검색 버튼
const onSearch = async ( ) => {

    // [4.1] key, keyword 가져오기
    const newkey = document.querySelector(".key").value;
    const newkeyword = document.querySelector(".keyword").value;
    
    // [4.2] 검색하면 페이지 1로 이동
    location.href = `post.jsp?cno=${cno}&page=1&key=${newkey}&keyword=${newkeyword}`


} // func end
findAll();