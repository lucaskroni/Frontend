let MS_Map = new Map()
const SPLTR = ':'


const parseStringMap = (str) => {
    const res = new Map()
    str = str.replace('{','')
    str = str.replace('}','')
    let splitArr = str.split('],')
    for(let item of splitArr){
        item = item.trim()
        let splitwArr = item.split('=')
        let mod = splitwArr[0]
        let scopes = []
        let scopesStr = splitwArr[1]
        scopesStr = scopesStr.replace('[','')
        scopesStr = scopesStr.replace(']','')
        for(let scope of scopesStr.split(',')){
            scopes.push(scope.trim())
        }
        res.set(mod, scopes)
    }
    return res
}


const getAllScopes = (map) => {
    let list = []
    let length = 0
    for (let [key, value] of map.entries()){
        length += value.length
    }

    for (let i = 0; i < length; i++) {
        list.push(document.getElementById(`cboxlbl${i}`).innerText)
    }
    return list
}

const hd_all = (scpLen) => {
    for(let i = 0; i < scpLen; i++){
        const cbox = document.getElementById(`cbox${i}`).hidden = true
    }

}

const setBoxes = () => {
    const ddwn = document.getElementById('slct_modules')
    const _mod = ddwn.value
    let scopes = getAllScopes(MS_Map)
    hd_all(scopes.length)
    for(let i = 0; i < scopes.length; i++){
        let item = scopes[i]
        let cbox = document.getElementById(`cbox${i}`)
        let cboxlbl = document.getElementById(`cboxlbl${i}`)
        if(_mod === cboxlbl.dataset.module && item === cboxlbl.innerText){
            cbox.hidden = false;
        }
    }
}

const setDDwnListeners = () => {
    const ddwn = document.getElementById('slct_modules')
    ddwn.addEventListener('change', (ev) => {
        setBoxes()
    })
}


const setupDDwn = () => {
    const ddwn = document.getElementById('slct_modules')
    const _msData = ddwn.dataset.map
    MS_Map = parseStringMap(_msData)
    setBoxes() // One time just to flex no eehh cause there is by default something selected
    setDDwnListeners()
}

setTimeout(setupDDwn, 450)
