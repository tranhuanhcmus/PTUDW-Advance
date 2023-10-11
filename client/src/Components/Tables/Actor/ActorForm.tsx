import { Box, Button, TextField, Typography } from '@mui/material';
import './styles.scss';
import { useState } from 'react';
import { ActorService } from '../../../services/Actor/ActorService';

export type ActorFieldForm = {
	firstName: string;
	lastName: string;
};

const initialFields: ActorFieldForm = {
	firstName: '',
	lastName: '',
};

const ActorForm = () => {
	const [fields, setFields] = useState(initialFields);
	const [status,setStatus]=useState<string>("")

	const handleSubmit = async (e: React.FormEvent) => {
		e.preventDefault(); // Prevent default form submission behavior

		try {
			setStatus("Pending")
			const res = await ActorService.addActor(fields);
			setStatus(res)
		} catch (err) {
			setStatus(err as string)
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
		<Box component="form" id="ActorForm" onSubmit={handleSubmit}>
			<div className="container">
				<TextField
					required
					name="firstName"
					label="First Name"
					fullWidth
					value={fields.firstName}
					onChange={handleFieldChange}
				/>
				<TextField
					required
					name="lastName"
					label="Last Name"
					fullWidth
					value={fields.lastName}
					onChange={handleFieldChange}
				/>
				<Typography>
					{status}
				</Typography>
				<Button type="submit" variant="contained">
					ADD
				</Button>
			</div>
		</Box>
	);
};

export default ActorForm;
