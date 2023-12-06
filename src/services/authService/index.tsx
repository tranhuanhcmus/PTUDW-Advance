import React from 'react'
import { User } from '../../models'
import axios from 'axios'
import { APIs } from '../constants';

type Props = {}

export class AuthService{
	static login=async (user:User) => {
		try {
			const response = await axios.post(APIs.LOGIN,user)
			return response.data;
		  } catch (error:any) {
			return undefined
		  }
	}
}

export default AuthService