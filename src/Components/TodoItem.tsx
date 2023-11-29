
import { useState } from 'react';
export type TodoItemProps = {
	title: string,
	content: string,
	check: boolean
}

const TodoItem = ({ title, content, check }: TodoItemProps) => {
	const [isCheck, setIsCheck] = useState(check)

	const handleInput = () => {
		setIsCheck(prev => !prev)
	}
	return (
		<div className={`${isCheck ? 'bg-green-500	 ' : 'bg-red-500	 '} w-full flex p-2 items-center gap-4`}>
			<input type="checkbox" name="check" checked={isCheck} onClick={handleInput} />
			<div className="flex-1 justify-start text-start">
				<h1 className={isCheck ? 'line-through' : ''}>{title}</h1>
				<p className={` italic`}>{content}</p>
			</div>
			<div>
				<button>Delete</button>
			</div>
		</div>
	)
}

export default TodoItem