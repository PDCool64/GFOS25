// fetch the document from the server
const url = "http://localhost:8080/Backend/api/tokens/refresh";

const hasJson = true;
const json = {
    "email": "test@test.de",
    "refresh_token": "d199640a0f98a689fd9475b2cf9ab720a5e8df0cb77b2cc9829953ea48caa6d2"
};
const method = 'POST';

// request

const response =  fetch(url, {
    method: method,
    body: hasJson ? JSON.stringify(json) : null,
    headers: hasJson ? {
        'Content-Type': 'application/json',
    } : {},
});


// response handling
const data = response.then((response) => {
    if (!response.ok) {
        throw new Error('HTTP error, status = ' + response.status);
    }
    return response.json();
}).then((data) => {
    console.log(data);

    const fs = require('fs');
    const path = require('path');

    const outputPath = path.join(__dirname, 'output', path.basename(__filename, '.js')) + '.json';
    fs.writeFileSync(outputPath, JSON.stringify(data, null, 2));

}).catch((error) => {
    console.error('There has been a problem with your fetch operation:', error);
});
