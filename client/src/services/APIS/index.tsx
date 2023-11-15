import axios from "axios";

export const serverA = axios.create({
	baseURL: 'http://localhost:8080/api/v1',
	timeout: 5000,
});
export const serverB = axios.create({
	baseURL: 'http://localhost:8081/api/v1',
	timeout: 5000,
});

serverA.defaults.withCredentials = true;	