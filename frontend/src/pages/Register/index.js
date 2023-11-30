import React from "react";
import "./register.css";
import { Form, Button } from "react-bootstrap";
import popcornMovie from "../../assets/popcorn-movie.png";

const Register = () => {
  return (
    <div className="container-fluid register">
      <div className="row register-row">
        <div className="col-lg-6 register-form">
          <h2>Register with us</h2>
          <hr />
          <Form>
            <Form.Group className="mb-3" controlId="firstName">
              <Form.Label>First Name</Form.Label>
              <Form.Control
                required
                type="text"
                id="firstName"
                placeholder="First Name"
                autoComplete="true"
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="lastName">
              <Form.Label>First Name</Form.Label>
              <Form.Control
                required
                type="text"
                id="lasttName"
                placeholder="Last Name"
                autoComplete="true"
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="email">
              <Form.Label>Email address</Form.Label>
              <Form.Control
                required
                type="email"
                id="email"
                placeholder="Email"
                autoComplete="true"
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="password">
              <Form.Label>Password</Form.Label>
              <Form.Control
                required
                type="password"
                id="password"
                placeholder="Password"
              />
            </Form.Group>
            <Button variant="primary" type="submit" className="register-button">
              Submit
            </Button>
          </Form>
        </div>
        <div className="col-lg-6 register-image-container">
          <img
            src={popcornMovie}
            alt="Img not found"
            className="signup-img"
            draggable="false"
          />
        </div>
      </div>
    </div>
  );
};

export default Register;
