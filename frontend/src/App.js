import React from "react";
import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import Register from "./pages/Register";
import Home from "./pages/Home";
import Sorry from "./pages/Sorry";
import Admin from "./pages/Admin-Home";

const App = () => {
  const Menu = () => {
    return (
      <Routes>
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Register />} />
        <Route path="*" element={<Sorry />} />
        <Route path="/" element={<Home />} />
        <Route path="/admin" element={<Admin />} />
      </Routes>
    );
  };

  return (
    <div className="main-app">
      <BrowserRouter>
        <Navbar />
        <Menu />
        <Footer />
      </BrowserRouter>
    </div>
  );
};

export default App;
