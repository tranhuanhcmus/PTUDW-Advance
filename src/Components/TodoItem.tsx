
import { useState, useEffect } from 'react';
import { Task } from './TodoList';
type TodoItemProps = {
	data: Task;
	key:number;
	handleDelete: () => void;
	handleEdit:() => void;
	handleCheck:() => void;
}

const TodoItem = ({ data,key, handleDelete,handleEdit,handleCheck }: TodoItemProps) => {
	const [taskData, setTaskData] = useState<Task>()

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