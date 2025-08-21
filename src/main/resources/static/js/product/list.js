console.log('list js exe')

let productList = [];

// [1] 전체 제품 정보 출력
const getList = async () => {
    console.log('getList func exe')

    // fetch
    const response = await fetch("/product/list");
    const data = await response.json();
    console.log(data);
    // 전역변수로 제품 정보 저장

    productList = data;

} // func end


// [2] 카카오 지도, 클러스터러 이용
// kakao map api > sample > 마커 클러스터러에 클릭이벤트 추가하기
const getMap = async () => {
    console.log('getMap func exe')

    // [추가!] 현재 사용자의 위치 좌표 가져오기
    const position = await myPosition();

    // [2.1] 지도 생성
    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center: new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    });

    // [2.2] 마커 클러스터러를 생성
    // 마커 클러스터러를 생성할 때 disableClickZoom 값을 true로 지정하지 않은 경우
    // 클러스터 마커를 클릭했을 때 클러스터 객체가 포함하는 마커들이 모두 잘 보이도록 지도의 레벨과 영역을 변경합니다
    // 이 예제에서는 disableClickZoom 값을 true로 설정하여 기본 클릭 동작을 막고
    // 클러스터 마커를 클릭했을 때 클릭된 클러스터 마커의 위치를 기준으로 지도를 1레벨씩 확대합니다
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 10, // 클러스터 할 최소 지도 레벨
        disableClickZoom: true // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
    });

    // [2.3] JQuery >> 전역변수 productList로 마커 생성
    // // [2.3.1] 반복문
    // let markerList = [];
    // for (let i = 0; i < productList.length; i++) {
    //     const product = productList[i];
    //     // 제품의 위도·경도 정보로 marker 생성
    //     let marker = new kakao.maps.Marker({ position: new kakao.maps.LatLng(product.plat, product.plng) })
    //     // marker을 리스트에 push
    //     markerList.push(marker);
    // }

    // [2.3.2] Map 반복문
    let markers = productList.map((product) => {
        let marker = new kakao.maps.Marker({ position: new kakao.maps.LatLng(product.plat, product.plng) })
        kakao.maps.event.addListener(marker, 'click', () => {
            alert(`클릭한 제품명은 : ${product.pname}`);
        })


        // [ 250821 추가 ] 업로드 이미지 출력 ======================
        const productBox = document.querySelector('#productBox')
        let html = ""

        if (product.images.length == 0) {
            html += `<img src="/upload/basic_profile.jpg"/>`
        } else {
            for (let i = 0; i < product.images.length; i++) {
                let img = product.images[i]
                html += `<img src="/upload/${img}"/>`

            }
        }
        productBox.innerHTML = html;

        return marker;
    })

    // 클러스터러에 마커들을 추가
    clusterer.addMarkers(markers);

    // [2.4] 마커 클러스터러에 클릭이벤트를 등록
    // 마커 클러스터러를 생성할 때 disableClickZoom을 true로 설정하지 않은 경우
    // 이벤트 헨들러로 cluster 객체가 넘어오지 않을 수도 있습니다
    kakao.maps.event.addListener(clusterer, 'clusterclick', function (cluster) {

        // 현재 지도 레벨에서 1레벨 확대한 레벨
        var level = map.getLevel() - 1;

        // 지도를 클릭된 클러스터의 마커의 위치를 기준으로 확대합니다
        map.setLevel(level, { anchor: cluster.getCenter() });
    });

} // func end

// [*] 함수 동기화를 위한 순서 실행 함수
const _init = async () => {
    await getList();    // 제품 정보 불러오기 우선 실행
    await getMap();
}
_init()