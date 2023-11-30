import { useState, useEffect } from 'react'
import TodoItem from './TodoItem'
import './styles.scss'
import TodoForm, { FormMode } from './TodoForm';

type Props = {

}

export type Task = {
	title: string,
	content: string,
	check: boolean
}

const tasksList: Array<Task> = [
	{
		title: `Task1`,
		check: false,
		content: `This is task 1`,
	},
	{
		title: `Task2`,
		check: true,
		content: `This is task 2`,
	},
	{
		title: `Task3`,
		check: false,
		content: `This is task 3`,
	}
]

export const updateLocalStorage = (tasks: Task[]) => {
	localStorage.setItem('tasks', JSON.stringify(tasks));
};
const TodoList = (props: Props) => {
	const [showModal, setShowModal] = useState(false)
	const [tasks, setTasks] = useState<Array<Task>>([])
	const [index, setIndex] = useState<number | undefined>(undefined)
	const [existData, setExistData] = useState<Task>();

	const handleClick = () => {
		setShowModal(prev => !prev)
		setExistData(prev => undefined)
		setIndex(undefined)
	}
	const handleClickEdit = (key: number) => {
		const editTask = tasks[key]
		setShowModal(prev => !prev)
		setExistData(prev => editTask)
		setIndex(key)
	}

	const handleSubmit = (title: string, content: string) => {
		var newList: Array<Task> = []
		if (
			index != undefined
		) {
			newList = [...tasks]
			newList[index].content = content
			newList[index].title = title
		}
		else {
			newList = [...tasks, { title, content, check: false }]
		}

		setTasks(prev => newList)
		updateLocalStorage(newList);
		handleClick()
	}
	const handleDelete = (key: number) => {
		const newList = tasks.filter((task, index) => index != key)
		setTasks(prev => newList)
		updateLocalStorage(newList);

	}
	const handleCheck = (index: number) => {
		var newList: Array<Task> = [...tasks]
		newList[index].check = !newList[index].check
		setTasks(prev => newList)
		updateLocalStorage(newList);
	}

	useEffect(() => {
		const storedTasks = localStorage.getItem('tasks');

		const initialTasks = storedTasks ? JSON.parse(storedTasks) : tasksList;

		setTasks(initialTasks);
	}, []);


	return <>
		<div>
			<h1 className='uppercase'>
				Todo List
			</h1>
			<div>
				<button onClick={handleClick}>ADD</button>
			</div>
		</div>
		<div className='w-[1024px] rounded-md overflow-hidden'>
			{tasks.length > 0 ? tasks.map((task, index) => (
				<TodoItem key={index} data={task}
					handleDelete={() => handleDelete(index)}
					handleEdit={() => handleClickEdit(index)}
					handleCheck={() => handleCheck(index)} />
			)) : <p>
				No Task</p>}

		</div>
		<TodoForm isShow={showModal} handleClick={handleClick} handleSubmit={handleSubmit} existData={existData} />

	</>

}

export default TodoList