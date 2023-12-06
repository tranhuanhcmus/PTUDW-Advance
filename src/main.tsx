import ReactDOM from 'react-dom/client'
import './index.css'
import './App.css'

import { Provider } from 'react-redux'
import { persistor, store } from './redux/store.tsx'
import { PersistGate } from 'redux-persist/integration/react'
import { RouterProvider } from 'react-router-dom'
import router from './routes/index';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <Provider store={store}>
    <PersistGate loading={null} persistor={persistor}>
      <RouterProvider router={router}/>
    </PersistGate>
  </Provider>

)
