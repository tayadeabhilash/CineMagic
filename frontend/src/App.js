import React from 'react';
import './App.css';
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import Login from './pages/Login';
import Navbar from './components/Navbar';
import Footer from './components/Footer';


const App = () => {

  const Menu = () => {
    return (
      <Routes>
        <Route path='login' element={<Login />} />
      </Routes>
    )
  }

  return (
          <div className='main-app'>
            <BrowserRouter>
            <Navbar />
            <Menu />
            <Footer />
            </BrowserRouter>
          </div>
        
  );
};

export default App;