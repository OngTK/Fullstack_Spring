console.log('position js exe')

// [1] IP 기반 위치 조회
// 오차가 있을 수 있음
const myPosition = async () => {
    console.log('myPosition func exe')

    const position = await new Promise((resolve, reject) => {
        // Promise : 비동기 객체
        // resolve : 성공 시
        // reject : 실패 시

        navigator.geolocation.getCurrentPosition(resolve, reject, {
            // navigator.geolocation.getCurrentPosition( 성공 , 실패 , 속성 )
            // 현재 브라우저의 IP 기반으로 위도·경도를 조회

            enableHighAccuracy: true,  // 가능한 정확한 위치 (속도 느림, 전기 소모 큼)
            timeout: 5000,             // 단위 : milli-sec => 5초 이내에 가져오지 못하면 reject를 반환
            maximumAge: 0              // 캐시(임시) 정보는 사용하지 않음 (항상 새로고침)
        })

    })
    console.log(`위치 정보 : ${position} `)
    console.log(`위도 정보 : ${position.coords.latitude} `)
    console.log(`경도 정보 : ${position.coords.longitude} `)
    return position;
} // func end