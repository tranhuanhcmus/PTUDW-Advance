import { Box, Button, TextField, Typography } from '@mui/material';
import './styles.scss';
import { useState } from 'react';
import { AuthService } from '../../services/Auth/AuthService';
import { AxiosError } from 'axios';
import { useSelector } from 'react-redux';
import { AppState } from './../../redux/store';

export type AuthFieldForm = {
	userName: string;
	password: string;
};

const initialFields: AuthFieldForm = {
	userName: '',
	password: '',
};

const AuthForm = () => {
	const [fields, setFields] = useState(initialFields);
	const [status, setStatus] = useState<string>("")

	const user = useSelector((state: AppState) => state.user)


	const handleFieldChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		const { name, value } = e.target;
		setFields({
			...fields,
			[name]: value,
		});
	};

	const handleLogin = async (e: React.FormEvent) => {
		e.preventDefault();

		console.log(fields);
		const res = await AuthService.login(fields);
		console.log(res);

	}

	return (
		<Box component="form" id="AuthForm" onSubmit={handleLogin}>
			<div className="container">
				<TextField
					required
					name="userName"
					label="UserName"
					fullWidth
					value={fields.userName}
					onChange={handleFieldChange}
				/>
				<TextField
					required
					name="password"
					label="Password"
					fullWidth
					value={fields.password}
					onChange={handleFieldChange}
				/>
				<Typography>
					{status}
				</Typography>
				<Button type="submit" variant="contained">
					LOGIN
				</Button>
			</div>
		</Box>
	);
};

export default AuthForm;
