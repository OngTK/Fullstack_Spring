console.log('kakao js exe')


// [1] kakao 지도 출력
const kakaoMap1 = async () => {
    var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
    var options = { //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.503752, 126.787025), //지도의 중심좌표.
        level: 3 //지도의 레벨(확대, 축소 정도)
    };

    var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
}
//kakaoMap1()


// [2] 클릭한 위치에 마커 표시하기
const kakaoMap2 = async () => {
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = {
            center: new kakao.maps.LatLng(37.503752, 126.787025), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    // 지도를 클릭한 위치에 표출할 마커입니다
    var marker = new kakao.maps.Marker({
        // 지도 중심좌표에 마커를 생성합니다 
        position: map.getCenter()
    });
    // 지도에 마커를 표시합니다
    marker.setMap(map);

    // 지도에 클릭 이벤트를 등록합니다
    // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
    kakao.maps.event.addListener(map, 'click', function (mouseEvent) {

        // 클릭한 위도, 경도 정보를 가져옵니다 
        var latlng = mouseEvent.latLng;

        // 마커 위치를 클릭한 위치로 옮깁니다
        marker.setPosition(latlng);

        var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
        message += '경도는 ' + latlng.getLng() + ' 입니다';

        console.log(message)
    })
}
// kakaoMap2()

// [3] 마커에 클릭 이벤트 등록하기
const kakaoMap3 = async () => {
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = {
            center: new kakao.maps.LatLng(37.503752, 126.787025), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    // 마커를 표시할 위치입니다 
    var position = new kakao.maps.LatLng(37.503752, 126.787025);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        position: position,
        clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
    });

    // 아래 코드는 위의 마커를 생성하는 코드에서 clickable: true 와 같이
    // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
    // marker.setClickable(true);

    // 마커를 지도에 표시합니다.
    marker.setMap(map);

    // // 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
    // var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    //     iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

    // // 인포윈도우를 생성합니다
    // var infowindow = new kakao.maps.InfoWindow({
    //     content: iwContent,
    //     removable: iwRemoveable
    // });

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function () {
        // 마커 위에 인포윈도우를 표시합니다
        // infowindow.open(map, marker);

        // 추후에는 사이드바를 변화시키거나 모달, 오프 캔버스.. 등등
        alert('마커를 클릭했습니다.')
    });
}
// kakaoMap3()

// [4] 여러개 마커 표시하기
const kakaoMap4 = async () => {
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
        mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    // 마커를 표시할 위치와 title 객체 배열입니다 
    var positions = [
        {
            title: '카카오',
            latlng: new kakao.maps.LatLng(33.450705, 126.570677)
        },
        {
            title: '생태연못',
            latlng: new kakao.maps.LatLng(33.450936, 126.569477)
        },
        {
            title: '텃밭',
            latlng: new kakao.maps.LatLng(33.450879, 126.569940)
        },
        {
            title: '근린공원',
            latlng: new kakao.maps.LatLng(33.451393, 126.570738)
        }
    ];

    // 마커 이미지의 이미지 주소입니다
    var imageSrc = "http://localhost:8080/img/tj_logo.jpg";

    for (var i = 0; i < positions.length; i++) {

        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다    
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage // 마커 이미지 
        });
    }
}
kakaoMap4()