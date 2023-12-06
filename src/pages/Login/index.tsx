import { User } from '../../models'
import { useDispatch, useSelector } from 'react-redux';
import { isLogin, setLoading, setUser } from '../../redux/statusSlice';
import { useNavigate } from 'react-router-dom';
import { AppState } from '../../redux/store';

import { useForm, SubmitHandler } from "react-hook-form"
import yup from './../../yup/index';
import { yupResolver } from '@hookform/resolvers/yup';
import AuthService from '../../services/authService';
import { PATH } from '../../routes/constants';

type Inputs = {
	account: string
	password: string
}

type Props = {}

const Login = (props: Props) => {
	const schema = yup.object().shape({
		account: yup.string().required("this field is required"),
		password: yup.string().required("this field is required")
	})

	const {
		register,
		handleSubmit,
		formState: { errors },
	} = useForm<Inputs>({
		mode: 'onChange',
		resolver: yupResolver(schema)
	})

	const dispatch = useDispatch()

	const navigate = useNavigate()

	const state = useSelector((state: AppState) => state)



	const handleSubmitForm: SubmitHandler<Inputs> = async (data) => {
		const user: User = { ...data }
			dispatch(setLoading(true))
			const res:User= await AuthService.login(user)
			if(res==undefined){
				alert("account not exist")
			}
			dispatch(setUser(res))
			dispatch(setLoading(false))
			navigate(PATH.HOME)
	}

	const handleLogout = () => {
		dispatch(setUser(undefined))
	}

	return <>
		{isLogin(state) ? <button onClick={handleLogout}>Log out</button> : <section >
			<div className="flex flex-col items-center justify-center px-6 py-8 md:h-screen lg:py-0">
				<div className="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700">
					<div className="p-6 space-y-4 md:space-y-6 sm:p-8">
						<h1 className="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
							Sign in to your account
						</h1>
						<form className="space-y-4 md:space-y-6" onSubmit={handleSubmit(handleSubmitForm)}>
							<div>
								<label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Account</label>
								<input type="text" className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" placeholder="name@company.com"  {...register("account")} />
								{errors.account && <span className='text-white'>This field is required</span>}
							</div>
							<div>
								<label className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</label>
								<input type="password" placeholder="••••••••" className="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"  {...register("password")} />
								{errors.password && <span className='text-white'>This field is required</span>}
							</div>


							<button type="submit" className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800">Sign in</button>
							<p className="text-sm font-light text-gray-500 dark:text-gray-400">
								Don’t have an account yet? <a href="#" className="font-medium text-primary-600 hover:underline dark:text-primary-500">Sign up</a>
							</p>
						</form>
					</div>
				</div>
			</div>
		</section>}
	</>


}

export default Login