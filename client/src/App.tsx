import { Button } from '@mui/material'
import AuthForm from './Components/Auth/AuthForm'
import ActorTable from './Components/Tables/Actor/ActorTable'
import { useState } from 'react';
import FilmTable from './Components/Tables/Film/FilmTable';

function App() {

  const [showActors, setShowActors] = useState(false)
  const [showFilms, setShowFilms] = useState(false)
  const [showLogin, setShowLogin] = useState(false)

  const handleClickActors = () => {
    setShowActors(prev => !prev)
    setShowFilms(false)

  }
  const handleClickFilms = () => {
    setShowFilms(prev => !prev)
    setShowActors(false)
  }
  const handleLogin = () => {
    setShowLogin(prev => !prev)
    setShowFilms(false)
    setShowActors(false)

  }

  return (
    <>
      <div id='Controller'>
        <Button onClick={handleLogin} variant={showLogin ? 'contained' : 'outlined'}>
          Login
        </Button>
        <Button onClick={handleClickActors} variant={showActors ? 'contained' : 'outlined'}>
          Actors Table
        </Button>
        <Button onClick={handleClickFilms} variant={showFilms ? 'contained' : 'outlined'}>
          Films Table
        </Button>
      </div>
      <div id='Tables'>
        {showActors && <ActorTable />}
        {showFilms && <FilmTable />}
        {showLogin && <AuthForm />}
      </div>

    </>
  )
}

export default App
