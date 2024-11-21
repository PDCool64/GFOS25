import address from '../address';

export async function login(username, password) {
    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            email: username,
            passwort: password,
        }),
    };
    console.log(username, password);
    console.log(options);

    const response = await fetch(address + '/tokens/login', options);
    if (response.ok) {
        const data = await response.json();
        setTimeout(() => {
            refreshToken();
        }, 300_000);

        return data;
    } else {
        console.log('HTTP-Error: ' + response.status);
        console.log(response);
    }
}

export async function refreshToken() {
    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            token: localStorage.getItem('refreshToken'),
        }),
    };

    const response = await fetch(address + '/tokens/refresh', options);
    if (response.ok) {
        const data = await response.json();
        localStorage.setItem('accessToken', data.accessToken);
        localStorage.setItem('refreshToken', data.refreshToken);
    } else {
        console.log('HTTP-Error: ' + response.status);
        console.log(response);
    }
}
