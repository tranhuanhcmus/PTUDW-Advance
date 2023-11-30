import './App.css'
import TodoList, { Task } from './Components/TodoList'
import { useDispatch, useSelector } from 'react-redux'
import { AppState } from './redux/store';
import { addTask } from './redux/taskSlice';
import { useEffect } from 'react';
function App() {
  const tasks = useSelector((state: AppState) => state.tasks)


  const dispatch = useDispatch()
  console.log(tasks);

  useEffect(() => {
    const newTask: Task = {
      content: "test",
      title: "title",
      check: true
    }
    dispatch(addTask(newTask))

  }, [])


  return (
    <>
      <TodoList />
    </>
  )
}

export default App
