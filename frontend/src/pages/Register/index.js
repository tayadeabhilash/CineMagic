import React, { useState, useEffect } from "react";
import "./register.css";
import { Form, Button, Row, Col } from "react-bootstrap";
import popcornMovie from "../../assets/popcorn-movie.png";
import { useDispatch, useSelector } from "react-redux";
import { useRegisterMutation } from "../../redux/userApiSlice";
import { setCredentials } from "../../redux/authSlice";
import { message } from "antd";
import { Link, useLocation, useNavigate } from "react-router-dom";

const Register = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");

  const [register] = useRegisterMutation();

  const { userInfo } = useSelector((state) => state.auth);

  const submitHandler = async (e) => {
    e.preventDefault();
    try {
      const res = await register({ firstName, lastName, email, password });
      console.log(res);

      if (res.data) {
        dispatch(setCredentials({ ...res }));
        navigate("/");
        message.success("User Registered Succesfully!");
      } else {
        message.error(res?.errorMessage);
      }
    } catch (err) {
      message.error({ error: err?.data?.message });
    }
  };

  useEffect(() => {
    if (userInfo) {
      navigate("/");
    }
  }, [userInfo]);

  return (
    <div className="container-fluid register">
      <div className="row register-row">
        <div className="col-lg-6 register-form">
          <h2>Register with us</h2>
          <hr />
          <Form onSubmit={submitHandler}>
            <Form.Group className="mb-3" controlId="firstName">
              <Form.Label>First Name</Form.Label>
              <Form.Control
                required
                type="text"
                id="firstName"
                placeholder="First Name"
                autoComplete="true"
                value={firstName}
                onChange={(e) => setFirstName(e.target.value)}
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
                value={lastName}
                onChange={(e) => setLastName(e.target.value)}
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
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
            </Form.Group>
            <Form.Group className="mb-3" controlId="password">
              <Form.Label>Password</Form.Label>
              <Form.Control
                required
                type="password"
                id="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </Form.Group>
            <Button variant="primary" type="submit" className="register-button">
              Submit
            </Button>
          </Form>
          <Row className="py-3">
            <Col>
              Already have an account? <Link to="/login">Login</Link>
            </Col>
          </Row>
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
