window.addEventListener('load', () => {
    addDragDropEventListener(document.getElementById('optilist'))
})

let itemTODelete_opti = "all"

function addDragDropEventListener (item){
    item.addEventListener('dragover', onItemDragOver)
    item.addEventListener('dragenter', onItemDragEnter)
    item.addEventListener('drop', dropOPT)
    item.addEventListener('dragend', dragEndOPT)
    item.addEventListener('dragleave', onItemDragLeave)
}

function createOptiBinding(){
    const optionBinding = document.getElementById('optionOutput')
    optionBinding.value = ""
    const optiList = document.getElementById('optilist')
    let str = ""
    for(let li of optiList.querySelectorAll('li')){
        if(str === ""){
            str += `${li.innerHTML}`
        }else{
            str += `????${li.innerHTML}`
        }
    }
    optionBinding.value = str
}

function dragEndOPT (){
    const deleteArea = document.getElementById('deletefield_opti')
    deleteArea.style.opacity = '0'
    this.classList.remove('over')
    this.style.opacity = '1'
    this.style.fontWeight = 'normal'
}

function dropOPT(e){
    e.stopPropagation()
    if(dragSrcEl !== this && dragSrcEl.className !== "phase" && noContained(document.getElementById('optilist').children, dragSrcEl.innerHTML) && noContainedUltimate("phaseList", dragSrcEl.innerHTML)){
        const liItem = document.createElement('li')
        liItem.innerHTML = dragSrcEl.innerHTML
        liItem.className = "listItem_option"
        liItem.style.cursor = "move"
        liItem.draggable = true
        liItem.addEventListener('dragstart', (e) => {
            const deleteArea = document.getElementById('deletefield_opti')
            deleteArea.style.opacity = '1'
            itemTODelete_opti = liItem.innerText
            dragSrcEl = liItem
            e.dataTransfer.setData('text/html', liItem.innerHTML)
        })
        //TODO: Go on here and make it so that you are able to remove also one by one scopeModule in the Phase
        liItem.addEventListener('dragend', () => {
            dragSrcEl.style.opacity = '1'
            const deleteArea = document.getElementById('deletefield_opti')
            deleteArea.style.opacity = '0'
            itemTODelete_opti = "all"
        })
        liItem.addEventListener('drop', (e) => {
            e.stopPropagation()
            if(liItem !== dragSrcEl && dragSrcEl.classList.contains("listItem_option")){
                dragSrcEl.innerHTML = liItem.innerHTML
                liItem.innerHTML = e.dataTransfer.getData('text/html')
            }
        })
        this.appendChild(liItem)
        createOptiBinding()
    }
}