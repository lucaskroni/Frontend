
//Constants
let prevBodyHeight //I know its a let but yeah for me its a constant :(
let lastScroll = 0

clearSessionStorage = () =>{
    sessionStorage.clear()
    console.log("clear")
}

appeare = () => { //Makes stuff appeare :)
    const cvs = document.getElementById('line-canvas')
    const cvs_cntx = cvs.getContext('2d')
    const circle = document.getElementById('noTime')
    const bttnDiv = document.getElementById('bttn-div')
    bttnDiv.style.transform = 'translateX(79px), translateY(78px)'

    const fromY = (document.body.getBoundingClientRect().top - circle.getBoundingClientRect().top)
    const fromX = (circle.getBoundingClientRect().left - document.body.getBoundingClientRect().left)
    const toY = 400

    let start, prevTS, reqId
    let prevY = fromY
    let done = false

    function drawLine(timestamp){
        if(start === undefined){
            start = timestamp
        }
        const goneBy = timestamp - start

        if(prevTS !== timestamp){
            const count = Math.min(0.3 * goneBy, toY) // Changing the "0.3" Values makes it go fastus
            animate(cvs_cntx, fromX, prevY, (prevY + count))
            if(count === toY){
                done = true
            }
        }

        if(goneBy < 2000){
            prevTS = timestamp
            if(!done){
                reqId = window.requestAnimationFrame(drawLine)
            }else{
                window.cancelAnimationFrame(reqId)
            }
        }
    }

    //Start animation
    reqId = window.requestAnimationFrame(drawLine)
}

animate = (contx, fromX, fromY,  toY) => {
    contx.beginPath()
    contx.lineWidth = 3
    contx.moveTo(fromX,fromY)
    contx.lineTo(fromX, toY)
    contx.stroke()
}

resizeBody = () => {
    const canvas = document.getElementById('line-canvas')
    canvas.setAttribute('transform', `scaleY(${document.getElementById('line-canvas').clientHeight - prevBodyHeight})`)
    prevBodyHeight = document.getElementById('line-canvas').clientHeight
}

const callbackNormMain = (main) => {
    main.className = "normal"
    main.removeEventListener("animationend", callbackNormMain(main))
}

const callbackNormOptional = (optional) => {
    optional.className = "normal"
    main.removeEventListener("animationend", callbackNormOptional(optional))
}

const callbackNormFinish = (finish, cont_ftr) => {
    finish.className = "normal"
    cont_ftr.className = "normal"
    finish.removeEventListener("animationend", callbackNormFinish(finish, cont_ftr))
}

const callbackBackMain = (main) => {
    main.className = "mains"
    main.removeEventListener("animationend")
}

const callbackBackOptional = (optional) => {
    optional.className = "optionals"
    optional.removeEventListener("animationend")
}

const callbackBackFinish = (finish, cont_ftr) => {
    finish.className = "finish"
    cont_ftr.className = "cont_ftr"
    finish.removeEventListener("animationend")
}

setClassOfNextToReveal = () => {
    const main = document.getElementById("mains")
    const optionals = document.getElementById("optionals")
    const finish = document.getElementById("finish")
    const cont_ftr = document.getElementById("cont_ftr")
    console.log(window.getComputedStyle(main).opacity)
    if(window.getComputedStyle(main).opacity  === "0"){
        main.className = "mainsAnim"
        main.addEventListener("animationend", callbackNormMain(main))
    }else if(window.getComputedStyle(optionals).opacity  === "0"){
        optionals.className = "optionalsAnim"
        optionals.addEventListener("animationend", callbackNormOptional(optionals))
    }else if(window.getComputedStyle(finish).opacity  === "0"){
        finish.className = "finishAnim"
        cont_ftr.className = "finishAnim"
        finish.addEventListener("animationend", callbackNormFinish(finish, cont_ftr))
    }
}

const setClassOfNextToUnReveal = () => {
    const main = document.getElementById("mains")
    const optionals = document.getElementById("optionals")
    const finish = document.getElementById("finish")
    const cont_ftr = document.getElementById("cont_ftr")
    if(window.getComputedStyle(main).opacity === "1"){
        main.className = "mainsAnimBack"
        main.addEventListener("animationend", callbackBackMain(main))
    }else if(window.getComputedStyle(optionals).opacity  === "1"){
        optionals.className = "optionalsAnimBack"
        optionals.addEventListener("animationend", callbackBackOptional(optionals))
    }else if(window.getComputedStyle(finish).opacity  === "1"){
        finish.className = "finishAnimBack"
        cont_ftr.className = "finishAnimBack"
        finish.addEventListener("animationend", callbackBackFinish(finish, cont_ftr))
    }
}

reactOnScroll = () => {
    if((lastScroll + 150) < document.scrollingElement.scrollTop){
        setClassOfNextToReveal()
        console.log("down")
        lastScroll = document.scrollingElement.scrollTop
    }else if((lastScroll - 150) > document.scrollingElement.scrollTop){
        setClassOfNextToUnReveal()
        console.log("up")
        lastScroll = document.scrollingElement.scrollTop
    }
}

setListeners = () => {
    const circle = document.getElementById("noTime")
    if(circle.className !== "NoListen"){
        circle.addEventListener("animationend", appeare)
    }
    addDraggingListeners(document.getElementById('deletefield'))
    /*document.addEventListener("scroll", () => {
        reactOnScroll();
    })*/
}

function addDraggingListeners(item){
    item.addEventListener('dragstart', onItemDragStart)
    item.addEventListener('dragover', onItemDragOver)
    item.addEventListener('dragenter', onItemDragEnter)
    item.addEventListener('drop', dropDelete)
    item.addEventListener('dragend', onItemDragEnd)
    item.addEventListener('dragleave', onItemDragLeave)
}

const dropDelete = (e) => {
    e.stopPropagation()
    console.log(dragSrcEl)
    console.log(dragSrcEl.innerHTML)
    if(dragSrcEl !== this && dragSrcEl.classList.contains('phase')){
        if(dragSrcEl.querySelectorAll('.phaseList'))
        dragSrcEl.remove()
    }
    return false
}

setObservers = () => {
    const resizeObs = new ResizeObserver(resizeBody)
    resizeObs.observe(document.getElementById("line-canvas"))
}

deactivateActions = () => {
    //I Deactivate the default submit event on the form
    let inputs = document.getElementsByTagName("input")
    for (let i = 0; i < inputs.length; i++) {
        inputs[i].addEventListener("keypress", (e) => {
            let code = e.key
            if (code === "Enter") {
                e.preventDefault()
                return false
            }
        })
    }
}

const setupMain = () => {
    prevBodyHeight = document.getElementById('line-canvas').clientHeight
    document.getElementById('timeOut').className = "" //Prevent startup Animations
    setListeners()
    setObservers()
    deactivateActions()
}
setTimeout(setupMain, 500)

window.addEventListener("load", clearSessionStorage)

