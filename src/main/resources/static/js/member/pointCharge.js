console.log("pointCharge js exe")

// [1] radio 버튼에서 누른 value를 가져오는 func
const onCharge = async () => {
    console.log("onCharge func exe")
    
    const pointChargeInput = document.querySelector('input[name="pointCharge"]:checked')
    const pointCharge = pointChargeInput.value;

    console.log(pointCharge)
} // func end