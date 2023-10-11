import { ActorFieldForm } from "../../Components/Tables/Actor/ActorForm";
import { api } from "../APIS"
import { API } from "../APIS/constants"

export class ActorService {
	static getAll = async () => {
		try {
			const response = await api.get(API.ACTOR.ALL);
			return response.data;
		} catch (error) {
			throw error; // You can throw the error for better error handling in the component
		}
	}
	static addActor = async (data: ActorFieldForm) => {
		try {
			const response = await api.post(API.ACTOR.ADD, data)
			return response.data;
		} catch (error) {
			throw error
		}
	}
}

