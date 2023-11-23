import React, { useState } from "react";
import { Modal, Button, Select, Row, Col } from "antd";
import { useParams, useNavigate } from "react-router-dom";

const initialMovieDetails = {
  title: "EXAMPLE MOVIE (ENGLISH)",
  duration: "120 mins",
  releaseDate: "Jan 1st 2023",
  genre: "Drama",
};

const SHOW_TIMES = [
  {
    theatre: "THEATRE ONE",
    address: "123 Main St, Anytown",
    times: ["12:00 PM", "03:00 PM"],
  },
  {
    theatre: "THEATRE TWO",
    address: "456 Elm St, Othertown",
    times: ["02:00 PM", "06:00 PM"],
  },
];

const TICKET_PRICE = 10;

const MovieSelectionPage = () => {
  var navigate = useNavigate();
  const { id } = useParams();
  const [movieDetails, setMovieDetails] = useState(initialMovieDetails);
  const [selectedShow, setSelectedShow] = useState(null);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [selectedSeats, setSelectedSeats] = useState(1);

  const selectShow = (show) => {
    setSelectedShow(show);
    setIsModalVisible(true);
  };

  const handleModalClose = () => {
    setIsModalVisible(false);
  };

  const handleSeatChange = (value) => {
    setSelectedSeats(value);
  };

  const handleCheckout = () => {
    const queryParams = new URLSearchParams({
      theatre: selectedShow.theatre,
      address: selectedShow.address,
      time: selectedShow.selectedTime,
      seats: selectedSeats,
      total: calculateTotal(),
    }).toString();

    navigate(`/checkout?${queryParams}`);
    handleModalClose();
  };

  const calculateTotal = () => {
    return selectedSeats * TICKET_PRICE;
  };

  return (
    <div className="container mt-5">
      <div className="movie-details mb-5">
        <h1>{movieDetails.title}</h1>
        <p>
          <strong>Duration:</strong> {movieDetails.duration}
        </p>
        <p>
          <strong>Release Date:</strong> {movieDetails.releaseDate}
        </p>
        <p>
          <strong>Genre:</strong> {movieDetails.genre}
        </p>
      </div>
      <h2>THEATRES</h2>
      {SHOW_TIMES.map((theatre, index) => (
        <div key={index} className="theatre mb-3 p-3 shadow-sm rounded">
          <h3>{theatre.theatre}</h3>
          <p>{theatre.address}</p>
          <Row gutter={8}>
            {theatre.times.map((time, idx) => (
              <Col key={idx} span={4}>
                <Button
                  className="time-button"
                  type="primary"
                  onClick={() => selectShow({ ...theatre, selectedTime: time })}
                >
                  {time}
                </Button>
              </Col>
            ))}
          </Row>
        </div>
      ))}

      <Modal
        title="Select Seats and Checkout"
        visible={isModalVisible}
        onCancel={handleModalClose}
        footer={[
          <Button key="back" onClick={handleModalClose}>
            Return
          </Button>,
          <Button key="submit" type="primary" onClick={handleCheckout}>
            Checkout
          </Button>,
        ]}
      >
        <p>
          <strong>Theatre:</strong> {selectedShow?.theatre}
        </p>
        <p>
          <strong>Address:</strong> {selectedShow?.address}
        </p>
        <p>
          <strong>Show Time:</strong> {selectedShow?.selectedTime}
        </p>
        <p>
          <strong>Seats:</strong>
        </p>
        <Select
          defaultValue={1}
          style={{ width: 120 }}
          onChange={handleSeatChange}
        >
          {[...Array(10).keys()].map((num) => (
            <Select.Option key={num} value={num + 1}>
              {num + 1}
            </Select.Option>
          ))}
        </Select>
        <p className="mt-3">
          <strong>Total Price:</strong> ${calculateTotal()}
        </p>
      </Modal>
    </div>
  );
};

export default MovieSelectionPage;
