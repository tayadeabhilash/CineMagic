import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
import { Card, Button, Checkbox } from "antd";
import "./checkout.css";
import moment from "moment";
import { GetShowById, GetTheaterById } from "../../apicalls/theaters";
import { CreateBooking } from "../../apicalls/user";
import { useSelector } from "react-redux";
import { message } from "antd";

const Checkout = () => {
  const location = useLocation();
  const { userInfo } = useSelector((state) => state.auth);
  const params = new URLSearchParams(location.search);

  const [theaterDetails, setTheaterDetails] = useState({});
  const [showtimeDetails, setShowtimeDetails] = useState({});

  const [useRewards, setUseRewards] = useState(false);
  const [rewardPoints, setRewardPoints] = useState(100);
  const [discount, setDiscount] = useState(0);
  const [price, setPrice] = useState(0);
  const [totalPrice, setTotalPrice] = useState(0);
  const [finalDiscount, setFinalDiscount] = useState(0);
  const [minusDiscount, setMinusDiscount] = useState(0);

  const seats = parseInt(params.get("seats"), 10);
  const moviePosterUrl = params.get("poster");

  const taxPerTicket = 1.5;
  const totalTax = seats * taxPerTicket;

  const fetchTheaterAndShowtimeDetails = async () => {
    const theaterId = params.get("theaterId");
    const showtimeId = params.get("showtimeId");

    if (theaterId) {
      const theaterData = await GetTheaterById(theaterId);
      if (theaterData.status == 200) {
        setTheaterDetails(theaterData.data);
      }
    }

    if (showtimeId) {
      const showtimeData = await GetShowById(showtimeId);

      console.log(showtimeData);
      if (showtimeData.status == 200) {
        setShowtimeDetails(showtimeData.data);
      }
    }
  };

  const handleRewardsChange = (e) => {
    setUseRewards(e.target.checked);
  };

  const handleBooking = async () => {
    const requestData = {
      showtimeId: showtimeDetails.id,
      userId: userInfo.userId,
      seatsBooked: seats,
      paymentMethod: "CREDIT_CARD",
      totalAmount: totalPrice,
      pointsAmount: 0,
      cashAmount: totalPrice,
      onlineServiceFee: totalTax,
    };

    console.log(requestData);

    try {
      const response = await CreateBooking(requestData);
      console.log(response);

      if (response.status == 200) {
        message.success("Tickets Booked!");
      } else {
        message.error();
      }
    } catch (error) {
      console.error("Error making the payment:", error);
    }
  };

  useEffect(() => {
    fetchTheaterAndShowtimeDetails();
  }, []);

  useEffect(() => {
    setPrice(seats * showtimeDetails.price);

    if (showtimeDetails?.discountedPrice) {
      setTotalPrice(showtimeDetails.discountedPrice * seats + totalTax);
    } else {
      setTotalPrice(price + totalTax);
      console.log(totalPrice);
    }

    if (showtimeDetails?.discountedPrice) {
      setFinalDiscount(showtimeDetails.discountedPrice * seats);
      setMinusDiscount(price - finalDiscount);
    }
  }, [showtimeDetails, theaterDetails, price]);

  useEffect(() => {
    if (useRewards) {
      let ticketsFromPoints = Math.floor(rewardPoints / 10);
      let discountValue =
        Math.min(ticketsFromPoints, seats) * showtimeDetails.price;
      setDiscount(discountValue);
    } else {
      setDiscount(0);
    }
  }, [showtimeDetails, useRewards, rewardPoints, seats]);

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
                    <strong>Theater:</strong>
                  </p>
                  <p>{theaterDetails.name || "Theater Name"}</p>
                </div>
                <div className="summary-item">
                  <p>
                    <strong>Address:</strong>
                  </p>
                  <p>{theaterDetails.address || "Theater Address"}</p>
                </div>
                <div className="summary-item">
                  <p>
                    <strong>Show Time:</strong>
                  </p>
                  <p>
                    {moment(showtimeDetails.time).format("h:mm a") ||
                      "Show Time"}
                  </p>
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

                {showtimeDetails.discountedPrice && (
                  <div className="summary-item">
                    <p></p>
                    <p className="discount"> - ${minusDiscount.toFixed(2)}</p>
                  </div>
                )}
                {showtimeDetails.discountedPrice && (
                  <div className="summary-item">
                    <p>
                      <strong>Discounted Price:</strong>
                    </p>
                    <p>${finalDiscount.toFixed(2)}</p>
                  </div>
                )}

                <div className="summary-item">
                  <p>
                    <strong>Service Fee:</strong>
                  </p>
                  <p>${totalTax.toFixed(2)}</p>
                </div>
                <div className="summary-total">
                  <p>
                    <strong>Total Price:</strong>
                  </p>
                  <p>${totalPrice.toFixed(2)}</p>
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
              <Button
                type="primary"
                block
                className="proceed-button"
                onClick={handleBooking}
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

export default Checkout;
