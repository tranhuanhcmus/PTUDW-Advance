
import { useState, useEffect } from 'react';
import { Task } from './TodoList';
import { useDispatch, useSelector } from 'react-redux';
import { checkTask, deleteTask } from '../redux/taskSlice';
import { AppState } from '../redux/store';
type TodoItemProps = {
	data: Task;
	id: number;
	handleEdit: () => void;
}

const TodoItem = ({ data, id, handleEdit }: TodoItemProps) => {
	const [taskData, setTaskData] = useState<Task>()

	const dispatch = useDispatch()

	const handleCheck = () => {
		taskData && dispatch(checkTask(taskData))
	}
	const handleDelete = () => {
		taskData && dispatch(deleteTask(taskData))
	}

	useEffect(() => {
		setTaskData(data)
	}, [data])


	return (
		<>
			{
				taskData && <div className={`${taskData.check ? 'bg-green-500	 ' : 'bg-red-500	 '} w-full flex p-2 items-center gap-4`}>
					<input type="checkbox" name="check" checked={taskData.check} onChange={handleCheck} />
					<div className="flex-1 justify-start text-start">
						<h1 className={taskData.check ? 'line-through' : ''}>{taskData.title}</h1>
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