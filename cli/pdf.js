'use strict';

const puppeteer = require('puppeteer');
const commander = require('commander');

commander
    .version('1', '-v')
    .option('-u, --url <value>', '')
    .option('-n, --name <value>', '')
    .parse(process.argv);

const options = commander.opts();
const path = options.url.includes('localhost') ? './' : '/apps/invo/pdfs/';

(async () => {
    const browser = await puppeteer.launch({
        headless: true,
        args: ['--no-sandbox']
    });
    const page = await browser.newPage();
    await page.goto(options.url, {
        waitUntil: 'networkidle0',
    });
    // page.pdf() is currently supported only in headless mode.
    // @see https://bugs.chromium.org/p/chromium/issues/detail?id=753118
    await page.pdf({
        path: `${path}${options.name}.pdf`,
        format: 'A4',
    });

    await browser.close();
})();