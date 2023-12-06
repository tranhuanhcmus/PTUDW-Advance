import { useState, useEffect } from 'react'
import TodoItem from './TodoItem'
import './styles.scss'
import TodoForm from './TodoForm';
import { useSelector } from 'react-redux';
import { AppState } from '../redux/store';

type Props = {

}

export type Task = {
	title: string,
	content: string,
	check: boolean
}

const TodoList = (props: Props) => {
	const [showModal, setShowModal] = useState(false)
	const [existData, setExistData] = useState<Task>();

	const tasks = useSelector((state:AppState) => state.tasks)

	const handleClick = () => {
		setShowModal(prev => !prev)
		setExistData(prev => undefined)
	}
	const handleClickEdit = (key: number) => {
		const editTask = tasks[key]
		setShowModal(prev => !prev)
		setExistData(prev => editTask)
	}

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