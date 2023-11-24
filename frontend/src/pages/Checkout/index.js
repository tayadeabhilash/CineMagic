import React from "react";
import { useLocation } from "react-router-dom";
import { Card, Button } from "antd";

const Checkout = () => {
  const location = useLocation();
  const params = new URLSearchParams(location.search);

  const theatre = params.get("theatre");
  const address = params.get("address");
  const time = params.get("time");
  const seats = parseInt(params.get("seats"), 10); // Convert seats to a number
  const price = parseFloat(params.get("total")); // Assuming total is the price before tax
  const taxPerTicket = 1.5;
  const totalTax = seats * taxPerTicket;
  const total = price + totalTax;
  const moviePosterUrl = params.get("poster"); // Assuming you pass the URL of the movie poster as a parameter

  return (
    <div className="container mt-5">
      <h1>Checkout</h1>
      <div className="row">
        <div className="col-md-6">
          <img src={moviePosterUrl} alt="Movie Poster" className="img-fluid" />
        </div>
        <div className="col-md-6">
          <Card title="Ticket Details" bordered={false} style={{ width: 300 }}>
            <p>
              <strong>Theatre:</strong> {theatre}
            </p>
            <p>
              <strong>Address:</strong> {address}
            </p>
            <p>
              <strong>Show Time:</strong> {time}
            </p>
            <p>
              <strong>Number of Seats:</strong> {seats}
            </p>
            <p>
              <strong>Price:</strong> ${price.toFixed(2)}
            </p>
            <p>
              <strong>Tax:</strong> ${totalTax.toFixed(2)}
            </p>
            <p>
              <strong>Total Price:</strong> ${total.toFixed(2)}
            </p>
            <Button type="primary" block>
              Proceed to Payment
            </Button>
          </Card>
        </div>
      </div>
    </div>
  );
};

export default Checkout;
