setTimeout(function(){
    const what = document.getElementById("noTime_nor")
    what.className = ""
    console.log(what.className)
},500);


const getCondiRadiobttns = () => {
    let bool = false
    const listRadios = document.getElementsByClassName("radioInput")
    for (let item of listRadios) {
        if(item.checked){
            return true
        }
    }
    return bool
}

function handlecreateClick(event){
    //Leeeeetttsseee gooooo ahahhahahahahahaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhh
    //Call back into the other JS-File that only works with require.js or browserify both tools to download so = bad
    const condi = event.target.dataset.condition
    if(condi === "anim_bttn") {
        const elem = document.getElementById("noTime")
        const bttn = document.getElementById("input-lbl")
        document.documentElement.style.setProperty("--bttn_init_h", `${bttn.offsetHeight + bttn.style.paddingTop + bttn.style.paddingBottom}px`)
        document.documentElement.style.setProperty("--bttn_init_w", `${bttn.offsetWidth + bttn.style.paddingLeft + bttn.style.paddingRight}px`)
        elem.innerHTML = ""
        elem.className = "ballAnim circle"
        elem.addEventListener("animationend", () => {
            document.documentElement.style.setProperty("--anim_end_trnY", "78px")
            document.documentElement.style.setProperty("--anim_end_trnX", "79px")
        })
        elem.id = "noTime"
    }else if(condi === "sub_button01"){
        const elem = document.getElementById('noTime_nor')
        if(elem !== null){
            if(elem.className !== ""){
                elem.className = ""
            }
        }
        //Submit the data
        if(document.getElementById('input').value !== "" && document.getElementById('input').value !== null && document.getElementById('out_Path').value !== "" && document.getElementById('out_Path').value !== null && getCondiRadiobttns()){
            document.getElementById('input-form').submit()
        }
    }else if(condi === "sub_button02"){
        const elem = document.getElementById("noTime")
        if(elem.className === ''){
            elem.className = "NoListen"
        }
        let checkboxName = "cboxing"
        let counter = 0
        let box = document.getElementById(`${checkboxName}${counter}`)
        while(box) {
            let checker = (event.target.innerText === "Select All")
            box.checked = checker
            counter++
            box = document.getElementById(`${checkboxName}${counter}`)
        }
        if(event.target.innerText === "Select All"){
            event.target.innerText = "Unselect All"
        }else{
            event.target.innerText = "Select All"
        }
        oncBoxClick()
    }
}

