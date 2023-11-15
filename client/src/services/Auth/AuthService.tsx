import { AuthFieldForm } from "../../Components/Auth/AuthForm";
import { serverA } from './../APIS/index';
import { API } from './../APIS/constants';


export class AuthService {

	static login = async (data: AuthFieldForm) => {
		try {
			const response = await serverA.post(API.AUTH, data,
				{
					headers: {
						'Content-Type': 'application/json'
					}
				});
			return response.data;
		} catch (error) {
			throw error
		}
	}

}

