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
import Movie from "./pages/Movie";
import Booking from "./pages/Booking";
import Checkout from "./pages/Checkout";
import MembershipPage from "./pages/Membership";
import Tickets from "./pages/Tickets";
import AnalyticsDashboard from "./pages/Admin-Stats";

const App = () => {
  const Menu = () => {
    return (
      <Routes>
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Register />} />
        <Route path="*" element={<Sorry />} />
        <Route path="/" element={<Home />} />
        <Route path="/admin" element={<Admin />} />
        <Route path="/analytics" element={<AnalyticsDashboard />} />
        <Route path="/movie/:id" element={<Movie />} />
        <Route path="/booking/:id" element={<Booking />} />
        <Route path="/checkout" element={<Checkout />} />
        <Route path="/buy-membership" element={<MembershipPage />} />
        <Route path="/tickets" element={<Tickets />} />
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
