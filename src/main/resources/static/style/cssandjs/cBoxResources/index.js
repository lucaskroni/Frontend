const oncBoxClick = () => {
    const list = document.getElementById("slctList")
    list.innerHTML = ""
    const cBoxes = document.querySelectorAll("#cBoxes input[type=checkbox]")
    for(let item of cBoxes){
        if(item.checked){
            const option = document.createElement("option")
            option.text = item.value
            list.appendChild(option)
        }
    }
}