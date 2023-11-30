import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { Task } from "../Components/TodoList";

interface TaskState {
	tasks: Array<Task>;
}

const initialState: TaskState = {
	tasks: [{
		title: "at",
		content: "asd",
		check: false
	}],
}

const taskSlice = createSlice({
	name: "task",
	initialState,
	reducers: {
		addTask: (state, action: PayloadAction<Task>) => {
			state.tasks=[...state.tasks,action.payload]
		}
	}
})

export const { addTask } = taskSlice.actions
export default taskSlice.reducer