import { useState } from 'react'
import './App.css'
import TodoList from './Components/TodoList'
import TodoForm from './Components/TodoForm';

function App() {
  const [showModal, setShowModal] = useState(false)

  const handleClickAdd=()=>{
    setShowModal(prev=>!prev)
  }
  return (
    <>
      <TodoList handleClick={handleClickAdd}/>
      <TodoForm isShow={showModal} handleClick={handleClickAdd}/>
    </>
  )
}

export default App
