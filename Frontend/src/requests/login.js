import address from '../address';

export async function login(email, password) {
    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            email: email,
            passwort: password,
        }),
    };
    console.log(email, password);
    console.log(options);

    const response = await fetch(address + '/tokens/login', options);
    if (response.ok) {
        const data = await response.json();
        return data;
    } else {
        console.log('HTTP-Error: ' + response.status);
        console.log(response);
    }
}
