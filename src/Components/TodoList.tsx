import React from 'react'
import TodoItem, { TodoItemProps } from './TodoItem'
import './styles.scss'
import TodoForm from './TodoForm';

type Props = {
	handleClick:()=>void
}

const tasksList: Array<TodoItemProps> = [
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

const TodoList = ({handleClick}: Props) => {
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
			{tasksList.map((task, index) => (
				<TodoItem key={index} {...task} />
			))}

		</div>

	</>

}

export default TodoList