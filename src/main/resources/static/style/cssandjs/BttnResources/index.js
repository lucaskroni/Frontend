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

const setValueMS = () => {
    for(let item of document.querySelectorAll('.Cbox')){
        item.value = item.dataset.realdata
    }
}

const noContained = (children, str) => {
    for(let item of children){
        if(item.innerText === str){
            return false
        }
    }
    return true
}

const noContainedUltimate = (classname,str) => {
    const list = document.getElementsByClassName(classname)
    for(let ulItem of list){
        if(!noContained(ulItem.children, str)){
            return false
        }
    }
    return true
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
            setValueMS()
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
    }else if(condi === "sub_button03"){
        const txtInput = document.getElementById('newInput')
        const list = document.getElementById('phslist')

        if((txtInput.value !== "" || txtInput.value !== null) && noContained(list.children, txtInput.value)){
            const item = document.createElement('li')
            const listMSs = document.createElement('ul')
            //Drag Lists
            listMSs.id = `phaseList_${txtInput.value.replace(" ", "_")}`
            listMSs.className = "phaseList"
            item.innerText = txtInput.value
            item.dataset.key = txtInput.value
            item.draggable = true
            item.style.cursor = "move"
            item.className = "phase"
            //Adding Stuff i guess
            addDragListeners(item)
            item.appendChild(listMSs)
            list.appendChild(item)
        }
    }
}


function addDragListeners(item){
    item.addEventListener('dragstart', onItemDragStart)
    item.addEventListener('dragover', onItemDragOver)
    item.addEventListener('dragenter', onItemDragEnter)
    item.addEventListener('drop', handleItemDropDiffrent)
    item.addEventListener('dragend', onItemDragEnd)
    item.addEventListener('dragleave', onItemDragLeave)
}


function handleItemDropDiffrent(e){  //Very different stuff on god
    e.stopPropagation()
    if(dragSrcEl !== this){
        if(dragSrcEl.className === "phase"){
            dragSrcEl.innerHTML = this.innerHTML
            this.innerHTML = e.dataTransfer.getData('text/html')
        }else{
            const ulList = document.getElementById(`phaseList_${this.dataset.key.replace(" ", "_")}`)
            if(noContainedUltimate("phaseList",dragSrcEl.innerHTML)) {
                const newItem = document.createElement("li")
                const bindingItem = document.getElementById('phaseOutput')
                newItem.innerHTML = dragSrcEl.innerHTML
                newItem.className = "listItem"
                newItem.style.cursor = "move"
                newItem.draggable = true
                newItem.addEventListener('dragstart', () => {
                    this.classList.add(newItem.innerText.replaceAll(" ", "_"))
                })
                //TODO: Go on here and make it so that you are able to remove also one by one scopeModule in the Phase
                newItem.addEventListener('dragend', () => {
                    this.classList.remove(newItem.innerText.replaceAll(" ", "_"))
                })
                if(bindingItem.value === "" || bindingItem.value === undefined || bindingItem.value === null){
                    bindingItem.value = `${this.dataset.key}_${dragSrcEl.innerHTML}`
                }else{
                    bindingItem.value = `${bindingItem.value};${this.dataset.key}_${dragSrcEl.innerHTML}`
                }
                console.log(bindingItem.value)
                ulList.appendChild(newItem)
                this.appendChild(ulList)
            }
        }
    }
    return false
}

