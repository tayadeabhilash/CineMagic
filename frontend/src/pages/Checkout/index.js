import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import { Card, Button, Checkbox } from "antd";
import "./checkout.css";

const Checkout = () => {
  const location = useLocation();
  const params = new URLSearchParams(location.search);

  const theatre = params.get("theatre");
  const address = params.get("address");
  const time = params.get("time");
  const seats = parseInt(params.get("seats"), 10);
  const price = parseFloat(params.get("total"));
  const taxPerTicket = 1.5;
  const totalTax = seats * taxPerTicket;
  const total = price + totalTax;
  const moviePosterUrl = params.get("poster");
  const ticketPrice = price / seats;

  const [useRewards, setUseRewards] = useState(false);
  const [rewardPoints, setRewardPoints] = useState(100);
  const [discount, setDiscount] = useState(0);

  useEffect(() => {
    if (useRewards) {
      let ticketsFromPoints = Math.floor(rewardPoints / 50);
      let discountValue = Math.min(ticketsFromPoints, seats) * ticketPrice;
      setDiscount(discountValue);
    } else {
      setDiscount(0);
    }
  }, [useRewards, rewardPoints, seats, ticketPrice]);

  const handleRewardsChange = (e) => {
    setUseRewards(e.target.checked);
  };

  const finalTotal = total - discount;

  return (
    <div className="checkout-page-container">
      <div className="checkout-container mt-5">
        <h1>Checkout</h1>
        <div className="row">
          <div className="col-md-6">
            <img
              src={moviePosterUrl}
              alt="Movie Poster"
              className="img-fluid"
            />
          </div>
          <div className="col-md-6">
            <Card title="Ticket Details" bordered={false} className="card">
              <div className="order-summary">
                <div className="summary-item">
                  <p>
                    <strong>Theatre:</strong>
                  </p>
                  <p>{theatre}</p>
                </div>
                <div className="summary-item">
                  <p>
                    <strong>Address:</strong>
                  </p>
                  <p>{address}</p>
                </div>
                <div className="summary-item">
                  <p>
                    <strong>Show Time:</strong>
                  </p>
                  <p>{time}</p>
                </div>
                <div className="summary-item">
                  <p>
                    <strong>Number of Seats:</strong>
                  </p>
                  <p>{seats}</p>
                </div>
                <div className="summary-item">
                  <p>
                    <strong>Price:</strong>
                  </p>
                  <p>${price.toFixed(2)}</p>
                </div>
                <div className="summary-item">
                  <p>
                    <strong>Tax:</strong>
                  </p>
                  <p>${totalTax.toFixed(2)}</p>
                </div>
                <div className="summary-total">
                  <p>
                    <strong>Total Price:</strong>
                  </p>
                  <p>${finalTotal.toFixed(2)}</p>
                </div>
                <div className="summary-item">
                  <p>
                    <strong>Your Reward Points:</strong>
                  </p>
                  <p>{rewardPoints}</p>
                </div>
                <div className="reward-points">
                  <Checkbox onChange={handleRewardsChange}>
                    Use Reward Points
                  </Checkbox>
                </div>
                {useRewards && (
                  <div className="summary-item">
                    <p>
                      <strong>Discount:</strong>
                    </p>
                    <p>-${discount.toFixed(2)}</p>
                  </div>
                )}
              </div>
              <Button type="primary" block className="proceed-button">
                Proceed to Payment
              </Button>
              <div className="security-privacy">
                <i className="icon fas fa-lock"></i>
                <p>
                  Secure transaction. Your personal information is kept private.
                </p>
              </div>
            </Card>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Checkout;
