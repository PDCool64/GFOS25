// fetch the document from the server
const url = "http://localhost:8080/Backend/api/aufgaben/all";
const hasJson = false;
const json = {};
const method = 'GET';


// request

const response = fetch(url, {
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
