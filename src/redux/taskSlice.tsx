import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { Task } from "../Components/TodoList";

const initialState: Array<Task> = [
	
];
function isTaskEqual(task1: Task, task2: Task): boolean {
	return (
		task1.title === task2.title &&
		task1.content === task2.content &&
		task1.check === task2.check
	);
}
const taskSlice = createSlice({
	name: "task",
	initialState,
	reducers: {
		addTask: (state, action: PayloadAction<Task>) => {
			state.push(action.payload);
		},
		deleteTask: (state, action: PayloadAction<Task>) => {
			const payloadTask = action.payload;
			return state.filter((task: Task) => !isTaskEqual(task, payloadTask));
		},
		checkTask: (state, action: PayloadAction<Task>) => {
			const payloadTask = action.payload;

			state.forEach(task => {
				if (isTaskEqual(task, payloadTask)) {
					task.check = !action.payload.check
				}
			});
		},
		editTask: (state, action: PayloadAction<{ oldTask: Task; updatedTask: Task }>) => {

			const { oldTask, updatedTask } = action.payload
			state.forEach(task => {
				if (isTaskEqual(task, oldTask)) {
					task.check = updatedTask.check
					task.content = updatedTask.content
					task.title = updatedTask.title
				}
			});
		},
	}
});

export const { addTask, deleteTask, editTask, checkTask } = taskSlice.actions;
export default taskSlice.reducer;
