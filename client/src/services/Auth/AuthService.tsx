import { AuthFieldForm } from "../../Components/Auth/AuthForm";
import { api } from "../APIS"
import { API } from "../APIS/constants"

export class AuthService {
	
	static login = async (data: AuthFieldForm) => {
		try {
			const response = await api.post(API.AUTH,data)
			return response.data;
		} catch (error) {
			throw error
		}
	}
}

