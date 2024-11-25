import address from "../adress";

export default {
	async getMessages() {
		return await fetch(`${address}/messages`)
			.then((response) => response.json())
			.then((data) => data)
			.catch((error) => console.log(error));
	},

	async getMessage(id) {
		return await fetch(`${address}/messages/${id}`)
			.then((response) => response.json())
			.then((data) => data)
			.catch((error) => console.log(error));
	},

	async postMessage(message) {
		return await fetch(`${address}/messages`, {
			method: "POST",
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify(message),
		})
			.then((response) => response.json())
			.then((data) => data)
			.catch((error) => console.log(error));
	},
};
