import address from '../address';
export async function getAllAufgaben() {
    const response = await fetch(address + '/aufgaben/all');
    return await response.json();
}

export async function getAufgabeById(id) {
    const response = await fetch(address + '/aufgaben/' + id, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    return await response.json();
}
