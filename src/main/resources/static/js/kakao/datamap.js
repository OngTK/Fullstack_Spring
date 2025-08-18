console.log("data map js exe")

// [1] 공공데이터 가져오기
// 인천광역시 동구 약국 현황

const dataAPI = async () => {
    console.log('dataAPI func exe')

    // [1.1] 공공데이터 URL, Service Key 준비
    const URL = "https://api.odcloud.kr/api/15051492/v1/uddi:852bbc11-63ed-493e-ab09-caaaf54fd144?page=1&perPage=34&serviceKey="
    const serviceKey = "yTgyAULtBe8wrcJ7hrxA%2Bp52DqaIrbAbyIFPfKBWCrmnNshV1wHF4lP1S5bJaimGt6uLT8Qb3RPOQbyKyAdUiw%3D%3D"


    // [1.2] Fetch
    const option = { method: "GET" }
    const response = await fetch(URL + serviceKey, option)
    const data = await response.json()
    console.log(data)

    // [1.3] 사이드에 위도, 경도를 제외한 정보 출력하기
    let html = '';
    const sidebar = document.querySelector('#sidebar')
    data.data.forEach(value => {
        html += `<div id = "store"> 
            <div>${value.약국명}</div>
            <div>${value.전화번호}</div>
            <div>${value.소재지도로명주소}</div>
        </div>`
    });
    sidebar.innerHTML = html;
} // func end
dataAPI()

//=====================================================================
// [1.4] KAKAO MAP 지도 가져오기
// 마커 클러스터러 사용하기
// 마커 클러스터러 : 여러 개의 마커가 겹칠 때, 도형으로 마커수를 표현

const kakaoMap = async () => {
    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center: new kakao.maps.LatLng(37.473835, 126.643280), // 지도의 중심좌표 
        level: 5 // 지도의 확대 레벨 
    });

    // 마커 클러스터러를 생성합니다 
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
        minLevel: 4 // 클러스터 할 최소 지도 레벨 
    });

    // [1.4.1] AJAX (jQuery) > Fetch 변경 + 공공데이터 가져오기
    const URL = "https://api.odcloud.kr/api/15051492/v1/uddi:852bbc11-63ed-493e-ab09-caaaf54fd144?page=1&perPage=34&serviceKey="
    const serviceKey = "yTgyAULtBe8wrcJ7hrxA%2Bp52DqaIrbAbyIFPfKBWCrmnNshV1wHF4lP1S5bJaimGt6uLT8Qb3RPOQbyKyAdUiw%3D%3D"
    const option = { method: "GET" }
    const response = await fetch(URL + serviceKey, option)
    const data = await response.json()
    console.log(data)

    // [1.4.2] 마커 생성 코드
    // 공공 data를 map 반복문으로 반환 값을 받음
    let markers = data.data.map((value) => {
        let marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(value.위도, value.경도)
        });

        // [1.4.3] 마커의 클릭 이벤트로 사이드바에 특정 정보 출력
        kakao.maps.event.addListener(marker, 'click', () => {

            const sidebar = document.querySelector('#sidebar')
            let html = `<button type="button" onclick="dataAPI()">전체보기</button>
            <div id="store"> 
            <div>${value.약국명}</div>
            <div>${value.전화번호}</div>
            <div>${value.소재지도로명주소}</div>
            </div>`;

            sidebar.innerHTML = html
        })

        // [1.4.3] 마커 반환
        return marker;
    })

    // [1.4.3] 클러스터러에 마커들을 추가
    clusterer.addMarkers(markers);
}
kakaoMap()