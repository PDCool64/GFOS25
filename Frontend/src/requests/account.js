import address from "../address";

export async function getAllAccounts() {
    const response = await fetch(address + '/accounts/all');
    return await response.json();
}

export async function getAccountById(id) {
    const response = await fetch(address + '/accounts/' + id, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    return await response.json();
}
