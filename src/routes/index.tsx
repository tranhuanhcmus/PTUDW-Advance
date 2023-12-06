import Login from "../pages/Login";
import PrivateRoute from "./PrivateRoute";
import TodoList from "../Components/TodoList";
import { PATH } from "./constants";

const routes = [
	{
		path:PATH.LOGIN,
		element:<Login/>
	},
	{
		path:PATH.HOME,
		element:<PrivateRoute element={<TodoList/>}/>
	},
	
];
export default routes