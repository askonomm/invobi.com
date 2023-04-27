function onToggleButtonClick(e) {
  if (e.target.parentElement.classList.contains('invoice-line-options')) {
    e.target.parentElement.querySelector('.options').classList.toggle('is-visible');
  }

  if (e.target.parentElement.classList.contains('toggle-button')) {
    e.target.parentElement.parentElement.querySelector('.options').classList.toggle('is-visible');
  }
}


function initLineOptions() {
  document.querySelectorAll('.invoice-line-options').forEach((node) => {
    node.querySelector('.toggle-button').addEventListener('click', onToggleButtonClick);
  });

  window.addEventListener('click', (e) => {
    const isInvoiceLineOptions = e.target.classList.contains('invoice-line-options');
    const isToggleButton = e.target.classList.contains('toggle-button');
    const isInsideToggleButton = e.target.parentElement.classList.contains('toggle-button');

    if (!isInvoiceLineOptions && !isToggleButton && !isInsideToggleButton) {
      document.querySelectorAll('.invoice-line-options').forEach(node => {
	node.querySelector('.options').classList.remove('is-visible');
      });
    }
  });
}

window.addEventListener('load', () => {
  initLineOptions();
})
