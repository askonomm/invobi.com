function toggleQtyTypeOptions() {
    document.querySelector('.qty-type .options').classList.toggle('is-visible');
}

window.addEventListener('load', () => {
    window.addEventListener('click', (e) => {
        const optionsNode = document.querySelector('.qty-type .options');

        if (e.target === optionsNode || e.target.contains(optionsNode)) {
            optionsNode.classList.remove('is-visible');
        }
    })
})
