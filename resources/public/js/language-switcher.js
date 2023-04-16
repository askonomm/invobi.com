function toggleLanguageSwitcherOptions() {
    document.querySelector('.language-switcher .options').classList.toggle('is-visible');
}

window.addEventListener('load', () => {
    window.addEventListener('click', (e) => {
        const optionsNode = document.querySelector('.language-switcher .options');

        if (e.target === optionsNode || e.target.contains(optionsNode)) {
            optionsNode.classList.remove('is-visible');
        }
    })
})