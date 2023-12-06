import { combineReducers, configureStore } from "@reduxjs/toolkit";
import taskSlice from "./taskSlice";
import { Task } from "../Components/TodoList";
import { persistStore, persistReducer } from 'redux-persist'
import storage from 'redux-persist/lib/storage'
import statusSlice, { AppStatus } from "./statusSlice";

export type AppState = {
	tasks: Array<Task>,
	appStatus:AppStatus
}

const persistConfig = {
	key: 'root',
	storage,
}

const rootReducer = combineReducers({
	tasks: taskSlice,
	appStatus:statusSlice
});

const persistedReducer = persistReducer(persistConfig, rootReducer)

const store = configureStore({
	reducer: persistedReducer,
	middleware: getDefaultMiddleware =>
    getDefaultMiddleware({
      serializableCheck: false,
    }),
});

const persistor = persistStore(store)

export { store, persistor };
