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

// [1.4] KAKAO MAP 지도 가져오기
// 마커 클러스터러 사용하기
const kakaoMap = async () => {
    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center: new kakao.maps.LatLng(37.4905335508, 126.7244907601), // 지도의 중심좌표 
        level: 14 // 지도의 확대 레벨 
    });

    // 마커 클러스터러를 생성합니다 
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
        minLevel: 10 // 클러스터 할 최소 지도 레벨 
    });


    // [1.4.1] AJAX > Fetch
    const response = await fetch("", { "/download/web/data/chicken.json": "GET" })
    const data = await response.json();
    console.log(data)

    let markers = data.data.map((position) => {
        return new kakao.maps.Marker({
            position: new kakao.maps.LatLng(position.lat, position.lng)
        });
    })

    // // 데이터를 가져오기 위해 jQuery를 사용합니다
    // // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
    // $.get("/download/web/data/chicken.json", function (data) {
    //     // 데이터에서 좌표 값을 가지고 마커를 표시합니다
    //     // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
    //     var markers = $(data.positions).map(function (i, position) {
    //     });

    //     // 클러스터러에 마커들을 추가합니다
    //     clusterer.addMarkers(markers);
    // });

}
kakaoMap()