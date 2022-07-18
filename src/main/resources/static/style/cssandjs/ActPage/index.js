let index = 0

const animateText = () => {
    const maxStr = "end"
    const wrtTxt = ['W','Wo','Wor','Work','Wor','Wo','W','',' ', '', 'C', 'Co', 'Con', 'Conv', 'Conve', 'Conver', 'Convert', 'Converti',  'Convertin', 'Converting','end']
    if(maxStr !== wrtTxt[index]){
        const lbl = document.getElementById('mgc_lbl')
        const wrtLtrs = wrtTxt[index]
        lbl.innerText = wrtLtrs
        console.log(wrtLtrs)
        index = index + 1
        setTimeout(animateText, 250)
    }
}

window.addEventListener('load', animateText)