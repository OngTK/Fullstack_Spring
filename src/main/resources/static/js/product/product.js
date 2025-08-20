console.log("product js exe")

let latlng = null; // 위도·경도 전역변수 처리

// [1] 카카오 지도로 위도·경도 가져오기
// kakao map api-sample : 클릭한 위치에 마커 표시하기
const getMap = async () => {
    console.log('getMap func exe')
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = {
            center: new kakao.maps.LatLng(37.489915, 126.723603), // 지도의 중심좌표
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

        // 클릭한 위도, 경도 정보를 가져옵니다  *****>> 전역변수로 수정
         latlng = mouseEvent.latLng;

        // 마커 위치를 클릭한 위치로 옮깁니다
        marker.setPosition(latlng);

        var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
        message += '경도는 ' + latlng.getLng() + ' 입니다';

        var resultDiv = document.getElementById('clickLatlng');
        resultDiv.innerHTML = message;

    });
} // func end
getMap()

// [2] 제품 등록
const onCreate = async () => {
    console.log('onCreate func exe')

    // form을 이용하여 input의 값을 한 번에 가져오기
    // 주의! 
    // form 안에 속성명은 name, name은 java dto 속성명과 일치해야함

    // [2.1] 전송할 폼 가져오기
    const productForm = document.querySelector('#productForm')

    // [2.2] FormData를 이용하여 mulitpart form으로 변환
    const productFormData = new FormData(productForm);

    // [2.3] kakao map에서 위도·경도 정보를 가져와서 form에 넣기
    // .append("속성명", 값)
    productFormData.append("plat", latlng.getLat() )
    productFormData.append("plng", latlng.getLng() )
    
    // [2.4] Fetch
    try{
    const option = {method : "POST", body : productFormData } // header 생략 가능, form 형태는 body에서 JSON으로 변환할 필요 없음
    const response = await fetch ("/product/create",option)
    const data = await response.json()
    console.log(data)

    if (data > 0 ){alert('등록 성공')}
    } catch(error){
        console.log("[경고] 관리자에게 문의하세요. \t" + e)
    }

} // func end