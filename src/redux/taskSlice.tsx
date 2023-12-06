import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { Task } from "../models";
import { TaskService } from "../services/taskService";

const initialState: Array<Task> = [
	
];
function isTaskEqual(task1: Task, task2: Task): boolean {
	return (
		task1.title === task2.title &&
		task1.content === task2.content &&
		task1.checked === task2.checked
	);
}
const taskSlice = createSlice({
	name: "task",
	initialState,
	reducers: {
		setTasks: (state,action:PayloadAction<Task[]>)=>{
			return state=action.payload
		},
		addTask: (state, action: PayloadAction<Task>) => {
			const task=action.payload
			state.push(task);
		},
		deleteTask: (state, action: PayloadAction<Task>) => {
			const payloadTask = action.payload;
			return state.filter((task: Task) => !isTaskEqual(task, payloadTask));
		},
		checkTask: (state, action: PayloadAction<Task>) => {
			const payloadTask = action.payload;
			state.forEach(task => {
				if (isTaskEqual(task, payloadTask)) {
					task.checked = !action.payload.checked
				}
			});
		},
		editTask: (state, action: PayloadAction<{ oldTask: Task; updatedTask: Task }>) => {
			const { oldTask, updatedTask } = action.payload
			state.forEach(task => {
				if (isTaskEqual(task, oldTask)) {
					task.checked = updatedTask.checked
					task.content = updatedTask.content
					task.title = updatedTask.title
					task.idTask=updatedTask.idTask
				}
			});
		},
	}
});

export const { addTask, deleteTask, editTask, checkTask,setTasks } = taskSlice.actions;
export default taskSlice.reducer;
