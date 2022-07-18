setTimeout(function(){
    const what = document.getElementById("noTime_nor")
    what.className = ""
    console.log(what.className)
},500);

function handlecreateClick(condi){
    //Leeeeetttsseee gooooo ahahhahahahahahaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaahhhhhhhhhhhhhhhh
    //Call back into the other JS-File that only works with require.js or browserify both tools to download so = bad
    if(condi) {
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
    }else{
        //Submit the data
        const elem = document.getElementById("noTime")
        if(elem.className === ''){
            elem.className = "NoListen"
        }
        if(document.getElementById('input').value !== "" && document.getElementById('input').value !== null){
            document.getElementById('input-form').submit()
        }
    }
}

