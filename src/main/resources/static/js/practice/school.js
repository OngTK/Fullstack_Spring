console.log("school practice7 js exe")

// [1] 관내 학교 정보 전체 출력
const shcoolData = async () => {
    console.log("school Data func exe")
    // [1.1] 공공 데이터 연결
    const URL = "https://api.odcloud.kr/api/15039731/v1/uddi:1fcb72a0-ba75-4c97-a045-9ef7e3ef43c0?page=1&perPage=72&serviceKey="
    const serviceKey = "yTgyAULtBe8wrcJ7hrxA%2Bp52DqaIrbAbyIFPfKBWCrmnNshV1wHF4lP1S5bJaimGt6uLT8Qb3RPOQbyKyAdUiw%3D%3D"

    // [1.2] Fetch
    const option = { method: "GET" }
    const response = await fetch(URL + serviceKey, option)
    const data = await response.json()
    console.log(data)

    // [1.3] JSP 화면 출력
    const sidebar = document.querySelector("#sidebar")
    let html = '';

    data.data.forEach(value => {
        // console.log(value)
        html += `<div id="school">
            <div style="font-size:1.5rem; font-weight:600;">${value.학교명}</div>
            <div>주소 : ${value.주소}</div>
            <div>연락처 : ${value.교무실}</div>
        </div>
        `
    });
    sidebar.innerHTML = html;
}
shcoolData()

// [2] 카카오 지도 불러오기 / 클러스터러 표시하기
const schoolMap = async () => {
    console.log('schoolMap func exe')

    // [2.1] kakao map  
    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center: new kakao.maps.LatLng(37.381050, 126.648563), // 지도의 중심좌표 
        level: 5 // 지도의 확대 레벨 
    });

    // 마커 클러스터러를 생성합니다 
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
        minLevel: 10 // 클러스터 할 최소 지도 레벨 
    });

    // [2.2] 공공Data 불러오기
    // [2.2.1] 공공 데이터 연결
    const URL = "https://api.odcloud.kr/api/15039731/v1/uddi:1fcb72a0-ba75-4c97-a045-9ef7e3ef43c0?page=1&perPage=72&serviceKey="
    const serviceKey = "yTgyAULtBe8wrcJ7hrxA%2Bp52DqaIrbAbyIFPfKBWCrmnNshV1wHF4lP1S5bJaimGt6uLT8Qb3RPOQbyKyAdUiw%3D%3D"
    // [2.2.2] Fetch
    const option = { method: "GET" }
    const response = await fetch(URL + serviceKey, option)
    const data = await response.json()
    console.log(data)

    // [2.3] jQuery > fetch 문으로 바꾸기
    // map 반복문 
    let markers = data.data.map((value) => {
        let marker = new kakao.maps.Marker({
            // 위도·경도 정보 삽입
            position: new kakao.maps.LatLng(value.위도, value.경도)
        });

        // [2.4] Marker 클릭 이벤트
        kakao.maps.event.addListener(marker, 'click', () => {
            const sidebar = document.querySelector("#sidebar")
            let html = `
            <button type="button" onclick="shcoolData()">전체보기</button>
            <div id="school">
                <div style="font-size:1.5rem; font-weight:600;">${value.학교명}</div>
                <div>주소 : ${value.주소}</div>
                <div>연락처 : ${value.교무실}</div>
            </div>`
            sidebar.innerHTML = html;

        })
        // [2.3] 반복문 결과인 marker을 반환 
        return marker;
    }//maps end
    )
    // [2.5] 클러스터러 마커 반환
    clusterer.addMarkers(markers);
} // func end
schoolMap()