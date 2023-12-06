import { useSelector } from 'react-redux';
import { AppState } from '../redux/store';
import { isLogin } from '../redux/statusSlice';
import { Navigate, Route } from 'react-router-dom';

type Props = {
	element:React.ReactNode
}

const PrivateRoute = ({element}: Props) => {

	const state=useSelector((state:AppState)=>state)
	const isAuthenticated=isLogin(state)
	
	return isAuthenticated ? (
		element
	  ) : (
		<Navigate to="/login" replace />
	  );

	
}

export default PrivateRoute