const getDirectory = (ev) => {
    ev.preventDefault() //It would have been opened if not for that
    const hdTextIn = document.getElementById('filePath')
    console.log("myhomie")
    if(ev.target.files){
        let path = (window.URL || window.webkitURL).createObjectURL(ev.target.files[0]);
        console.log(path)
        hdTextIn.value = path
    }
}

const setListenersFile = () => {
    const fileClicker = document.getElementById('fileDirClicker')
    fileClicker.onchange((event) => {
        getDirectory(event)
    })
    fileClicker.onclick(() => {
        fileClicker.value = null
    })
}

const setup = () => {
    setListenersFile()
}
