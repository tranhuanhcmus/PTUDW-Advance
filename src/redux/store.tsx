import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { Task } from "../Components/TodoList";
import taskSlice from "./taskSlice";

export interface AppState {
	tasks: Array<Task> | undefined;
}

const rootReducer = combineReducers({
	tasks: taskSlice,
});




const store = configureStore({
	//   reducer: {
	//     tasks: persistedReducer,
	//   },
	reducer: rootReducer
});


export { store };
