import { useState, useEffect } from 'react'
import TodoItem from './TodoItem'
import './styles.scss'
import TodoForm from './TodoForm';
import { useSelector, useDispatch } from 'react-redux';
import { AppState } from '../redux/store';
import { Task } from '../models';
import { TaskService } from '../services/taskService';
import { setLoading } from '../redux/statusSlice';
import { setTasks } from '../redux/taskSlice';

type Props = {

}

const TodoList = (props: Props) => {
	const [showModal, setShowModal] = useState(false)
	const [existData, setExistData] = useState<Task>();

	const tasks = useSelector((state: AppState) => state.tasks)

	const dispatch = useDispatch()

	const handleClick = () => {
		setShowModal(prev => !prev)
		setExistData(prev => undefined)
	}
	const handleClickEdit = (key: number) => {
		const editTask = tasks[key]
		setShowModal(prev => !prev)
		setExistData(prev => editTask)
	}

	const fetchData = async () => {
		dispatch(setLoading(true))
		const res = await TaskService.getTasks()
		
		if (res !== undefined) {
			dispatch(setTasks(res))
		}	
		else{
			dispatch(setTasks([]))
		}
		dispatch(setLoading(false))
	}

	useEffect(() => {
		fetchData()
	}, [])


	return <>
		<div>
			<h1 className='uppercase'>
				Todo List
			</h1>
			<div className='py-1'>
				<button onClick={handleClick}>ADD</button>
			</div>
		</div>
		<div className='w-[1024px] rounded-md overflow-hidden'>
			{tasks.length > 0 ? tasks.map((task, index) => (
				<TodoItem key={index} id={index} data={task}
					handleEdit={() => handleClickEdit(index)}
				/>
			)) : <p>
				No Task</p>}
		</div>
		<TodoForm isShow={showModal} switchModal={handleClick} existData={existData} />
	</>

}

export default TodoList