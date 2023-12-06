import {
	createBrowserRouter
} from "react-router-dom";
import Todo from "../pages/Todo";
import Loading from './../Components/Loading';

const router = createBrowserRouter([
	{
		path: "/",
		element: <Todo/>
	},
	{
		path: "/test",
		element: <Loading/>
	},
]);
export default router