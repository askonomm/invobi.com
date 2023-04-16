function setTextareaHeight(textarea) {
    textarea.style.height = '0px';
    textarea.style.height = `${textarea.scrollHeight}px`;
}

function listenToTextareaHeightChanges() {
    document.querySelectorAll('.textarea').forEach(textarea => {
        setTextareaHeight(textarea);

        textarea.addEventListener('input', (e) => {
            setTextareaHeight(textarea);
        })
    });
}

window.addEventListener('load', () => {
    listenToTextareaHeightChanges();
})

