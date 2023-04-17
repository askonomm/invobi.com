function initLineOptions() {
    document.querySelectorAll('.invoice-line-options').forEach((node) => {
        node.querySelector('.toggle-button').addEventListener('click', () => {
            node.querySelector('.options').classList.toggle('is-visible');
        });
    });

    window.addEventListener('click', (e) => {
        const lineOptionsNode = document.querySelector('.invoice-line-options');

        if (e.target === lineOptionsNode || e.target.contains(lineOptionsNode)) {
            lineOptionsNode.querySelector('.options').classList.remove('is-visible');
        }
    });
}

window.addEventListener('load', () => {
    initLineOptions();
})