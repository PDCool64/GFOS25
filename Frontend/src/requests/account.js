export async function getAllAccounts() {
    const response = await fetch('http://localhost:8080/Backend/api/accounts/all');
    return await response.json();
}

export async function getAccountById(id) {
    const response = await fetch('http://localhost:8080/Backend/api/accounts/' + id, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    return await response.json();
}