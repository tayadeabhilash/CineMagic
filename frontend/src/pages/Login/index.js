import React from "react";
import "./login.css";
import { Form, Button } from "react-bootstrap";
import movieGIF from "../../assets/movie.gif";

const Login = () => {
  return (
    <div className="container-fluid login">
      <div className="row login-row">
        <div className="col-lg-6 login-image-container">
          <img
            src={movieGIF}
            alt="Img not found"
            className="login-img"
            draggable="false"
          />
        </div>
        <div className="col-lg-6 login-form">
          <h2>Login</h2>
          <hr />
          <Form>
            <Form.Group className="mb-3" controlId="userEmail">
              <Form.Label>Email address</Form.Label>
              <Form.Control required type="email" placeholder="Enter email" />
              <Form.Text className="text-muted">
                We'll never share your email with anyone else.
              </Form.Text>
            </Form.Group>

            <Form.Group className="mb-3" controlId="userPassword">
              <Form.Label>Password</Form.Label>
              <Form.Control
                required
                type="password"
                placeholder="Password"
                autoComplete="true"
              />
            </Form.Group>

            <Button variant="primary" type="submit">
              Submit
            </Button>
          </Form>
        </div>
      </div>
    </div>
  );
};

export default Login;
