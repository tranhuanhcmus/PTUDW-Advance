import { Box, Button, TextField, Typography } from '@mui/material';
import './styles.scss';
import { useState } from 'react';
import { AuthService } from '../../services/Auth/AuthService';
import { AxiosError } from 'axios';

export type AuthFieldForm = {
	username: string;
	password: string;
};

const initialFields: AuthFieldForm = {
	username: '',
	password: '',
};

const AuthForm = () => {
	const [fields, setFields] = useState(initialFields);
	const [status,setStatus]=useState<string>("")

	const handleSubmit = async (e: React.FormEvent) => {
		e.preventDefault(); // Prevent default form submission behavior

		try {
			setStatus("Pending")
			const res:AuthFieldForm = await AuthService.login(fields);
			setStatus("Hello "+res.username)
			
		} catch (error) {
			if (error instanceof AxiosError) {
				console.log(error);
				
				setStatus(error.code as string)
			}
		  }
		finally{
			setFields(initialFields)
		}
	};

	const handleFieldChange = (e: React.ChangeEvent<HTMLInputElement>) => {
		const { name, value } = e.target;
		setFields({
			...fields,
			[name]: value,
		});
	};

	return (
		<Box component="form" id="AuthForm" onSubmit={handleSubmit}>
			<div className="container">
				<TextField
					required
					name="username"
					label="username"
					fullWidth
					value={fields.username}
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
