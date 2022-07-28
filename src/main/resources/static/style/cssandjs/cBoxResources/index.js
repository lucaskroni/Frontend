const oncBoxClick = () => {
    const list = document.getElementById("slctList")
    list.innerHTML = ""
    const cBoxes = document.querySelectorAll("#cBoxes input[type=checkbox]")
    let counter = 0;
    for(let item of cBoxes){
        if(item.checked){
            const listItem = document.createElement("li")
            listItem.id = `listItem_${counter}`
            listItem.innerText = item.value
            listItem.draggable = true
            listItem.style.cursor = "move"
            list.appendChild(listItem)
            addEventListeners(listItem)
        }
        counter++
    }
}

let dragSrcEl = ""

const addEventListeners = (item) => {
    item.addEventListener('dragstart', onItemDragStart)
    item.addEventListener('dragover', onItemDragOver)
    item.addEventListener('dragenter', onItemDragEnter)
    item.addEventListener('drop', onItemDrop)
    item.addEventListener('dragend', onItemDragEnd)
    item.addEventListener('dragleave', onItemDragLeave)
}

function onItemDrop(e) {
    e.stopPropagation()

    if(dragSrcEl !== this && !dragSrcEl.classList.contains('phase')){
        dragSrcEl.innerHTML = this.innerHTML
        let idTo = this.id
        let idFrom = dragSrcEl.id
        const toElem = document.getElementById(`cboxing${idTo.split('_')[1]}`)
        const fromElem = document.getElementById(`cboxing${idFrom.split('_')[1]}`)
        toElem.dataset.realdata = fromElem.value
        fromElem.dataset.realdata = toElem.value
        this.innerHTML = e.dataTransfer.getData('text/html')
    }

    return false
}

function onItemDragEnter(e) {
    this.classList.add('over')
}

function onItemDragLeave (){
    this.classList.remove('over')
}

function onItemDragStart (e) {
    this.style.opacity = '0.4'
    this.style.fontWeight = 'bold'

    dragSrcEl = this

    e.dataTransfer.effectAllowed = 'move'
    e.dataTransfer.setData('text/html', this.innerHTML)
}

function onItemDragEnd () {
    this.classList.remove('over')
    this.style.opacity = '1'
    this.style.fontWeight = 'normal'
}

function onItemDragOver (e) {
    e.preventDefault()
    return false

}
