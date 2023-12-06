
import { useState, useEffect } from 'react';
import { Task } from '../models';
import { useDispatch, useSelector } from 'react-redux';
import { checkTask, deleteTask } from '../redux/taskSlice';
import { TaskService } from './../services/taskService/index';
import { setLoading } from '../redux/statusSlice';
type TodoItemProps = {
	data: Task;
	id: number;
	handleEdit: () => void;
}

const TodoItem = ({ data, id, handleEdit }: TodoItemProps) => {
	const [taskData, setTaskData] = useState<Task>()

	const dispatch = useDispatch()

	const handleCheck = async () => {
		if(taskData){
			dispatch(setLoading(true))
			const res=await TaskService.editTask({...taskData,checked:!taskData.checked})
			dispatch(checkTask(taskData))
			dispatch(setLoading(false))
		}
	}
	const handleDelete = async () => {
		
		if(taskData){
			dispatch(setLoading(true))
			const res=await TaskService.deleteTask(taskData)
			if(res!=undefined){
				alert(res)
			}
			dispatch(deleteTask(taskData))
			dispatch(setLoading(false))
		}
	}

	useEffect(() => {
		setTaskData(data)
	}, [data])


	return (
		<>
			{
				taskData && <div className={`${taskData.checked ? 'bg-green-500	 ' : 'bg-red-500	 '} w-full flex p-2 items-center gap-4`}>
					<input type="checkbox" name="checked" checked={taskData.checked} onChange={handleCheck} />
					<div className="flex-1 justify-start text-start">
						<h1 className={taskData.checked ? 'line-through' : ''}>{taskData.title}</h1>
						<p className={` italic`}>{taskData.content}</p>
					</div>
					<div>
						<button onClick={handleDelete}>Delete</button>
						<button onClick={handleEdit}>Edit</button>
					</div>
				</div>
			}
		</>
	)
}

export default TodoItem