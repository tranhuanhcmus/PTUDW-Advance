import axios from "axios";
import { Task } from "../../models";
import { APIs } from "../constants";

export class TaskService {
	static addTask=async (task:Task)=>{
		try {
			const response=await axios.post(APIs.TASKs,task)
			
			return "add success"
		} catch (error) {
			return undefined
		}
	}
	static deleteTask=async (task:Task)=>{
		try {
			const response=await axios.delete(APIs.TASKs+`${task.idTask}`)
			
			return "delete success"
		} catch (error) {
			return "delete failed"
		}
	}
	static getTasks=async ()=>{
		try {
			const response=await axios.get(APIs.TASKs)
			
			return response.data
		} catch (error) {
			return undefined
		}
	}
	static editTask=async (task:Task)=>{
		try {
			const response=await axios.put(APIs.TASKs,task)
			
			return "update success"
		} catch (error) {
			return "update failed"
		}
	}
	
}