import address from "./address";
import { useTokenStore } from "./stores/token";

const tokenStore = useTokenStore();

export async function get_no_data(url) {
	return get(url, {});
}

export async function get(url, data) {
	console.log("get", url, data);
	data.method = "GET";
	data.headers = {
		Authorization: "Bearer " + tokenStore.token,
	};
	const response = await fetch(`${address}${url}`, data);
	if (response.status === 401) {
		tokenStore.logout();
	}
	return response;
}

export async function post_no_data(url) {
	return post(url, {});
}

export async function post(url, json) {
	let data = {};
	data.method = "POST";
	data.headers = {
		"Content-Type": "application/json",
		Authorization: "Bearer " + tokenStore.token,
	};
	data.body = JSON.stringify(json);
	console.log("post", url, data);
	const response = await fetch(`${address}${url}`, data);
	if (response.status === 401) {
		tokenStore.logout();
	}
	return response;
}

export async function put(url, data) {
	data.method = "PUT";
	data.headers = {
		"Content-Type": "application/json",
		Authorization: "Bearer " + tokenStore.token,
	};
	data.Authorization = "Bearer " + tokenStore.token;
	const response = await fetch(`${address}/${url}`, data);
	if (response.status === 401) {
		tokenStore.logout();
	}
}

export async function del(url) {
	data.method = "DELETE";
	data.headers = {
		Authorization: "Bearer " + tokenStore.token,
	};
	const response = await fetch(`${address}/${url}`, data);
	if (response.status === 401) {
		tokenStore.logout();
	}
}
