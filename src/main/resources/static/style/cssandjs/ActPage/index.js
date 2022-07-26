let index = 0

const animateText = () => {
    setup()
    const maxStr = "end"
    const wrtTxt = ['W','Wo','Wor','Work','Wor','Wo','W','',' ', '', 'C', 'Co', 'Con', 'Conv', 'Conve', 'Conver', 'Convert', 'Converti',  'Convertin', 'Converting','end']
    if(maxStr !== wrtTxt[index]){
        const lbl = document.getElementById('mgc_lbl')
        const wrtLtrs = wrtTxt[index]
        lbl.innerText = wrtLtrs
        console.log(wrtLtrs)
        index = index + 1
        setTimeout(animateText, 250)
    }else{
        document.getElementById("hd_check").click()
    }
}

const setupListeners = () => {
    document.getElementById("hd_check").addEventListener("click", () => {
        console.log("hmmmmm")
        document.getElementById("switch_Form").submit()
    })
}

const setup = () => {
    setupListeners()
}


window.addEventListener('load', animateText)