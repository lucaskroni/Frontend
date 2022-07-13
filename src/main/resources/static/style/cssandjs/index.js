
//Constants
let prevBodyHeight //I know its a let but yeah for me its a constant :(

appeare = () => { //Makes stuff appeare :)
    const cvs = document.getElementById('line-canvas')
    const cvs_cntx = cvs.getContext('2d')
    const circle = document.getElementById('circle')
    const addY = 0
    const addX = 0


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

setListeners = () => {
    const circle = document.getElementById("noTime")
    circle.addEventListener("animationend", appeare)
}

setObservers = () => {
    const resizeObs = new ResizeObserver(resizeBody)
    resizeObs.observe(document.getElementById("line-canvas"))
}

setup = () => {
    prevBodyHeight = document.getElementById('line-canvas').clientHeight
    document.getElementById('timeOut').className = "" //Prevent startup Animations
    setListeners()
    setObservers()
}
setTimeout(setup, 500)