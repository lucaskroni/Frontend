

setup = () => {
    const ddSelect = document.getElementById("_ddwn_slct")
    ddSelect.addEventListener("selectionchange", () => {
        window.location.reload() // Triggers a reload
    })
}