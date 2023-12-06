import {
	createBrowserRouter
} from "react-router-dom";
import Todo from "../pages/Todo";
import Loading from './../Components/Loading';
import Login from "../pages/Login";

const router = createBrowserRouter([
	{
		path: "/",
		element: <Todo/>
	},
	{
		path: "/test",
		element: <Login/>
	},
]);
export default router