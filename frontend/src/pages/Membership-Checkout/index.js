import React, { useState } from "react";
import { Card, Button, message } from "antd";
import "./checkout.css";
import { UpgradeMembership } from "../../apicalls/user";

const MembershipCheckout = () => {
  const membershipPrice = 15;

  const handleUpgrade = async () => {
    try {
      const response = await UpgradeMembership();
      console.log(response);
    } catch (error) {
      message.error(error);
    }
  };

  return (
    <div className="checkout-page-container">
      <div className="checkout-container mt-5">
        <h1>Membership Checkout</h1>
        <div className="row justify-content-center">
          <div className="col-md-6">
            <Card title="Membership Details" bordered={false} className="card">
              <div className="order-summary">
                <div className="summary-item">
                  <p>
                    <strong>Membership Price:</strong>
                  </p>
                  <p>${membershipPrice.toFixed(2)}</p>
                </div>
              </div>
              <Button
                type="primary"
                block
                className="proceed-button"
                onClick={handleUpgrade}
              >
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

export default MembershipCheckout;
