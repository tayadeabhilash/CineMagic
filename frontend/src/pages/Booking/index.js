import React, { useState, useEffect } from "react";
import { Modal, Button, Select, Row, Col } from "antd";
import { useNavigate } from "react-router-dom";
import "./booking.css";
import {
  GetShowsByMovie,
  GetShowsByTheaterAndMovie,
  GetTheaterById,
} from "../../apicalls/theaters";
import { message } from "antd";
import { GetMovieById } from "../../apicalls/movies";

const TICKET_PRICE = 10;

const MovieSelectionPage = () => {
  const navigate = useNavigate();
  const params = new URLSearchParams(window.location.search);
  const movieId = params.get("movie");
  const theaterId = params.get("theater");
  const [selectedMovie, setSelectedMovie] = useState(null);
  const [selectedTheater, setSelectedTheater] = useState(null);
  const [filteredShowtimes, setFilteredShowtimes] = useState([]);
  const [selectedShow, setSelectedShow] = useState(null);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [selectedSeats, setSelectedSeats] = useState(1);
  const [movie, setMovie] = useState([
    {
      id: "1",
      title: "Movie A",
      duration: "120 mins",
      releaseDate: "Jan 1st 2023",
      genre: "Action",
    },
  ]);
  const [theater, setTheater] = useState([
    { id: "1", name: "Theater One", address: "123 Main St, Anytown" },
  ]);

  const [showtimes, setShowtimes] = useState([
    { movieID: "1", theaterID: "1", time: "12:00 PM" },
    { movieID: "1", theaterID: "1", time: "03:00 PM" },
    { movieID: "1", theaterID: "1", time: "06:00 PM" },
    { movieID: "1", theaterID: "3", time: "12:00 PM" },
    { movieID: "1", theaterID: "3", time: "03:00 PM" },
    { movieID: "1", theaterID: "3", time: "06:00 PM" },
    { movieID: "2", theaterID: "1", time: "12:30 PM" },
    { movieID: "2", theaterID: "1", time: "03:30 PM" },
    { movieID: "2", theaterID: "1", time: "06:30 PM" },
    { movieID: "2", theaterID: "2", time: "12:30 PM" },
    { movieID: "2", theaterID: "2", time: "03:30 PM" },
    { movieID: "2", theaterID: "2", time: "06:30 PM" },
    { movieID: "1", theaterID: "1", time: "09:00 PM" },
    { movieID: "1", theaterID: "2", time: "09:00 PM" },
    { movieID: "2", theaterID: "1", time: "09:30 PM" },
    { movieID: "2", theaterID: "2", time: "09:30 PM" },
    { movieID: "1", theaterID: "1", time: "10:00 PM" },
    { movieID: "1", theaterID: "2", time: "10:00 PM" },
    { movieID: "2", theaterID: "1", time: "10:30 PM" },
    { movieID: "2", theaterID: "2", time: "10:30 PM" },
  ]);

  const fetchTheaterData = async (id) => {
    try {
      const response = await GetTheaterById(id);

      if (response.status == 200) {
        setTheater(response.data);
      } else {
        message.error(response.data);
      }
    } catch (error) {
      message.error(error);
    }
  };

  const fetchMovieData = async (id) => {
    try {
      const response = await GetMovieById(id);

      if (response.status == 200) {
        const formattedMovie = {
          id: response.data.movieId,
          title: response.data.movieName,
          description: response.data.synopsis,
          duration: response.data.runningTime,
          releaseDate: formatDate(response.data.releaseDate),
          genre: response.data.genre,
        };
        setMovie(formattedMovie);
      } else {
        message.error(response.data);
      }
    } catch (error) {
      message.error(error);
    }
  };

  const fetchShowtimes = async (theaterId, movieId) => {
    try {
      const response = await GetShowsByTheaterAndMovie(theaterId, movieId);

      if (response.status == 200) {
        const formattedShowTimes = response.data.map((showtime) => ({
          ...showtime,
          id: showtime.id,
          movieID: showtime.movieId,
          theaterID: showtime.theaterScreenId,
          time: formatTime(showtime.time)
        }));
        setShowtimes(formattedShowTimes);
      } else {
        message.error(response.data);
      }
    } catch (error) {
      message.error(error);
    }
  };

  const formatDate = (date) => {
    return new Intl.DateTimeFormat("en-US", {
      year: "numeric",
      month: "long",
      day: "numeric",
      timeZone: "UTC", // Adjust the time zone as needed
    }).format(date);
  };

  const formatTime = (date) => {
    return new Intl.DateTimeFormat("en-US", {
      hour: "numeric",
      minute: "numeric",
      timeZone: "UTC",
    }).format(date);
  };

  useEffect(() => {
    fetchMovieData(movieId);
    fetchTheaterData(theaterId);
    fetchShowtimes(theaterId, movieId)

    
  }, []);

  useEffect(() => {
    setSelectedMovie(movie);
    setSelectedTheater(theater);
    setFilteredShowtimes(showtimes);
  }, [movie, theater, showtimes]);

  const selectShow = (theater, time) => {
    setSelectedShow({ ...theater, selectedTime: time });
    setIsModalVisible(true);
  };

  const handleModalClose = () => {
    setIsModalVisible(false);
  };

  const handleSeatChange = (value) => {
    setSelectedSeats(value);
  };

  const handleCheckout = () => {
    const query = new URLSearchParams();
    query.set("theater", encodeURIComponent(selectedShow?.name || ""));
    query.set("address", encodeURIComponent(selectedShow?.address || ""));
    query.set("time", encodeURIComponent(selectedShow?.selectedTime || ""));
    query.set("seats", selectedSeats);
    query.set("total", calculateTotal());

    navigate(`/checkout?${query.toString()}`);
    handleModalClose();
  };

  const calculateTotal = () => {
    return selectedSeats * TICKET_PRICE;
  };

  const renderDetails = () => {
    // Show both movie and theater details if both are known
    if (selectedMovie && selectedTheater) {
      return (
        <>
          <div className="movie-details mb-3">
            <h1>{selectedMovie.title}</h1>
            <p>Duration: {selectedMovie.duration}</p>
            <p>Release Date: {selectedMovie.releaseDate}</p>
            <p>Genre: {selectedMovie.genre}</p>
          </div>
          <div className="theater-details mb-5">
            <h1>{selectedTheater.name}</h1>
            <p>Address: {selectedTheater.address}</p>
          </div>
        </>
      );
    } else if (selectedMovie) {
      // Show only movie details if only movie is known
      return (
        <div className="movie-details mb-5">
          <h1>{selectedMovie.title}</h1>
          <p>Duration: {selectedMovie.duration}</p>
          <p>Release Date: {selectedMovie.releaseDate}</p>
          <p>Genre: {selectedMovie.genre}</p>
        </div>
      );
    } else if (selectedTheater) {
      // Show only theater details if only theater is known
      return (
        <div className="theater-details mb-5">
          <h1>{selectedTheater.name}</h1>
          <p>Address: {selectedTheater.address}</p>
        </div>
      );
    }
    return null;
  };

  const renderContent = () => {
    if (selectedTheater && !selectedMovie) {
      // Show movies and their showtimes if only theater is known
      return () => {
        const movieShowtimes = filteredShowtimes.filter(
          (showtime) =>
            showtime.movieID === movie.id &&
            showtime.theaterID === selectedTheater.id
        );
        if (movieShowtimes.length === 0) return null;

        return (
          <div key={1} className="movie mb-3 p-3 shadow-sm rounded">
            <h3>{movie.title}</h3>
            <Row gutter={8}>
              {movieShowtimes.map((showtime, idx) => (
                <Col key={idx} span={4}>
                  <Button
                    className="time-button"
                    type="primary"
                    onClick={() => selectShow(selectedTheater, showtime.time)}
                  >
                    {showtime.time}
                  </Button>
                </Col>
              ))}
            </Row>
          </div>
        );
      };
    } else if (!selectedTheater && selectedMovie) {
      // Show theaters and their showtimes if only movie is known
      return () => {
        const theaterShowtimes = filteredShowtimes.filter(
          (showtime) =>
            showtime.theaterID === theater.id &&
            showtime.movieID === selectedMovie.id
        );
        if (theaterShowtimes.length === 0) return null;

        return (
          <div key={1} className="theater mb-3 p-3 shadow-sm rounded">
            <h3>{theater.name}</h3>
            <p>{theater.address}</p>
            <Row gutter={8}>
              {theaterShowtimes.map((showtime, idx) => (
                <Col key={idx} span={4}>
                  <Button
                    className="time-button"
                    type="primary"
                    onClick={() => selectShow(theater, showtime.time)}
                  >
                    {showtime.time}
                  </Button>
                </Col>
              ))}
            </Row>
          </div>
        );
      };
    } else if (selectedTheater && selectedMovie) {
      // Show only showtimes if both movie and theater are known
      return (
        <div className="showtimes mb-3 p-3 shadow-sm rounded">
          <Row gutter={8}>
            {filteredShowtimes.map((showtime, idx) => (
              <Col key={idx} span={4}>
                <Button
                  className="time-button"
                  type="primary"
                  onClick={() => selectShow(selectedTheater, showtime.time)}
                >
                  {showtime.time}
                </Button>
              </Col>
            ))}
          </Row>
        </div>
      );
    }
    return null;
  };

  return (
    <div className="container mt-5">
      {renderDetails()}

      <h2>Showtimes</h2>
      {renderContent()}

      {/* Modal for Seat Selection and Checkout */}
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
          <strong>Show Time:</strong> {selectedShow?.selectedTime}
        </p>
        <p>
          <strong>Theater:</strong> {selectedShow?.name}
        </p>
        <p>
          <strong>Movie:</strong> {selectedMovie?.title}
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
