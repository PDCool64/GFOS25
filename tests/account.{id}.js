
let id = null;
if (process.argv.length > 2) {
    id = process.argv[2];
    console.log('id:', id);
}

// fetch the document from the server
const url = "http://localhost:8080/Backend/api/accounts/" + (id ? id : '1');

const hasJson = false;
const json = {};
const method = 'GET';


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
}).catch((error) => {
    console.error('There has been a problem with your fetch operation:', error);
});
