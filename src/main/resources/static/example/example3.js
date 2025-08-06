 // 비동기 fetch vs 동기 fetch

const func1 = () => {

    console.log("[1] fetch 전");

    // 1. data
    // 2. option
    const option = { method: "GET" }
    
    
    
    // 3. fetch
    fetch("/day06/exam1", option)
        .then(response => response.json())
        .then(data => { 
            console.log(data);
            console.log("[2] fetch 통신결과");

        })
        .catch(error => { console.log(error) })

    console.log("[3] fetch 후");


    // 예상 실행결과 : 1 > 2 > 3
    // 실제 실행결과 : 1 > 3 > 2

    // 함수가 실행되면, JS는 fetch가 실행 결과가 반환되기 전, 다음 코드로 이동 

}