import ReactDOM from 'react-dom/client'
import './index.css'
import './App.css'

import { Provider } from 'react-redux'
import { persistor, store } from './redux/store.tsx'
import { PersistGate } from 'redux-persist/integration/react'
import Loading from './Components/Loading.tsx'
import App from './App.tsx'

ReactDOM.createRoot(document.getElementById('root')!).render(
  <Provider store={store}>
    <PersistGate loading={<Loading />} persistor={persistor}>
      <App/>
    </PersistGate>
  </Provider>

)
