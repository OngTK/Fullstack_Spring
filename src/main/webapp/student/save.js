console.log("save.js exe")

// [1] 저장
const save = () => {
    // 1. data
    let data = {"sname":"강형호",
        "skor":"91",
        "smath":"99",
        "sdate":"2025-08-07 11:11:11"
    }
    // 2. option
    let option = { method : "POST",
        headers : "Content-Type : application/json",
        body : JSON.stringify(data)
    }
    // 3. fetch
}
