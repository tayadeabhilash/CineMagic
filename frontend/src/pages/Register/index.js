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
            <Form.Group className="mb-3" controlId="name">
              <Form.Label>Username</Form.Label>
              <Form.Control
                required
                type="text"
                id="name"
                placeholder="Enter Username"
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="userEmail">
              <Form.Label>Email address</Form.Label>
              <Form.Control
                required
                type="email"
                id="userEmail"
                placeholder="Enter email"
              />
              <Form.Text className="text-muted">
                We'll never share your email with anyone else.
              </Form.Text>
            </Form.Group>

            <Form.Group className="mb-3" controlId="userPassword">
              <Form.Label>Password</Form.Label>
              <Form.Control
                required
                type="password"
                id="userPassword"
                placeholder="Password"
                autoComplete="true"
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="phoneNumber">
              <Form.Label>Phone Number</Form.Label>
              <Form.Control
                required
                type="number"
                id="phoneNumber"
                placeholder="Enter Phone Number"
              />
            </Form.Group>
            <Button variant="primary" type="submit">
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
