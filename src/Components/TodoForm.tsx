import { useEffect, useState } from "react";
import { Task } from "./TodoList";
import { useDispatch, useSelector } from 'react-redux';
import { addTask, editTask } from "../redux/taskSlice";

type Props = {
	isShow: boolean,
	switchModal: () => void,
	existData?: Task | undefined
}

export enum FormMode {
	ADD,
	EDIT
}

const TodoForm = ({ isShow, switchModal, existData }: Props) => {

	const [title, setTitle] = useState<string>("")
	const [content, setContent] = useState<string>("")

	const dispatch = useDispatch();

	const reset = () => {
		setContent("")
		setTitle("")
	}

	const handleForm = (e: any) => {
		e.preventDefault()
		const task = {
			title, content, check: false
		}

		if (!existData) {
			dispatch(addTask(task))
		}
		else{
			dispatch(editTask({oldTask:existData,updatedTask:task}))
		}
		reset()
		switchModal()
	}


	useEffect(() => {
		if (
			existData != undefined
		) {
			setContent(existData.content)
			setTitle(existData.title)
		}

	}, [existData])

	return (
		<>
			{isShow && (
				<form onSubmit={handleForm} className="fixed inset-0">
					<div className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" />
					<div className="fixed inset-0 z-10 w-screen overflow-y-auto">
						<div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
							<div className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all sm:my-8 sm:w-full sm:max-w-lg">
								<div className="bg-white px-4 pb-4 pt-5 sm:p-6 sm:pb-4">
									<div className={`rounded-md ring-gray-400 bg-slate-500 w-full flex p-2 items-center gap-4`}>
										<div className="flex-1 flex gap-2 flex-col justify-start text-start">
											<input
												value={title}
												onChange={(e) => setTitle(e.target.value)}
												className="block w-full rounded-sm p-2 bg-transparent text-black"
												type="text"
												name="title"
												placeholder="Input Title"
												required
											/>
											<input
												value={content}
												onChange={(e) => setContent(e.target.value)}
												className="block w-full rounded-sm p-2 bg-transparent text-black"
												type="text"
												name="content"
												placeholder="Input Content"
												required
											/>
										</div>
									</div>
								</div>
								<div className="bg-gray-50 px-4 py-3 sm:flex sm:flex-row-reverse sm:px-6 gap-4">
									<button type={"submit"}>Submit</button>
									<button onClick={() => {
										switchModal()
										reset()
									}}>Cancel</button>
								</div>
							</div>
						</div>
					</div>
				</form>
			)}
		</>
	);
}

export default TodoForm