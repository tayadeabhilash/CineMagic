import React from "react";
import { NavLink } from "react-router-dom";
import "./footer.css";

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-content">
        <nav className="footer-nav">
          <NavLink className="nav-link" to="/">
            Home
          </NavLink>
          <NavLink className="nav-link" to="/search-movie">
            Search Movies
          </NavLink>
          {/* NavLink for About page can be uncommented if needed */}
          {/* <NavLink className="nav-link" to="/about">
            About
          </NavLink> */}
        </nav>
        <div className="footer-text">
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua.
          </p>
        </div>
        <div className="social-links">
          <a href="/" className="social-link">
            <i className="fab fa-facebook-f"></i>
          </a>
          <a href="/" className="social-link">
            <i className="fab fa-twitter"></i>
          </a>
          <a href="/" className="social-link">
            <i className="fab fa-google"></i>
          </a>
          <a href="/" className="social-link">
            <i className="fab fa-instagram"></i>
          </a>
          <a href="/" className="social-link">
            <i className="fab fa-linkedin"></i>
          </a>
          <a href="/" className="social-link">
            <i className="fab fa-github"></i>
          </a>
        </div>
        <div className="footer-copy">
          © 2023 Copyright:{" "}
          <a href="/" className="footer-brand">
            Scrum&Coke
          </a>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
