import { BrowserRouter, Route, Routes } from 'react-router-dom';
import routes from './routes/index';
import { useSelector } from 'react-redux';
import { AppState } from './redux/store';
import Loading from './Components/Loading';

type Props = {}

const App = (props: Props) => {
	const isLoading = useSelector((state: AppState) => state.appStatus.loading)

	return <>
		{isLoading && <Loading />}
		<BrowserRouter>
			<Routes>
				{routes.map(route => <Route key={route.path} path={route.path} element={route.element} />)}
			</Routes>
		</BrowserRouter>
	</>

}

export default App