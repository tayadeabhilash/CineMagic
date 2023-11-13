import React from 'react';
import { NavLink } from 'react-router-dom';
import './navbar.css';

const Navbar = () => {

  return (
    <nav className='navbar navbar-expand-lg navbar-dark '>
      <a className='navbar-brand' href='/'>
        MovieWeb
      </a>
      <button
        className='navbar-toggler'
        type='button'
        data-toggle='collapse'
        data-target='#navbarNavDropdown'
        aria-controls='navbarNavDropdown'
        aria-expanded='false'
        aria-label='Toggle navigation'
      >
        <span className='navbar-toggler-icon navbar-dark'></span>
      </button>
      <div className='collapse navbar-collapse main-nav' id='navbarNavDropdown'>

          <ul className='main-nav navbar-nav'>
            <li className='nav-item'>
              <NavLink to='/'>Home</NavLink>
            </li>
            <li className='nav-item'>
              <NavLink to='/search-movie'>Search Movies</NavLink>
            </li>
          </ul>

          <ul className='navbar-nav ml-auto'>
            <li className='nav-item nav-user-btn'>
              <NavLink to='/login'>
                <button className='btn btn-outline-success px-2 py-0'>
                  Login
                </button>
              </NavLink>
            </li>
            <li className='nav-item nav-user-btn'>
              <NavLink to='/register'>
                <button className='btn btn-outline-success px-2 py-0'>
                  Register
                </button>
              </NavLink>
            </li>
          </ul>
       
      </div>
    </nav>
  );
};

export default Navbar;