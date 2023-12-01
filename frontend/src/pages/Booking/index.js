import React, { useState, useEffect } from "react";
import { Modal, Button, Select, Row, Col } from "antd";
import { useNavigate } from "react-router-dom";
import "./booking.css";
import {
  GetAllTheaters,
  GetShowsByMovie,
  GetShowsByTheater,
  GetShowsByTheaterAndMovie,
  GetTheaterById,
} from "../../apicalls/theaters";
import { message } from "antd";
import { GetAllMovies, GetMovieById } from "../../apicalls/movies";

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
  const [movie, setMovie] = useState(null);
  const [theater, setTheater] = useState(null);

  const [showtimes, setShowtimes] = useState([]);

  const fetchTheaterData = async (id) => {
    let response = null;
    try {
      if (id) response = await GetTheaterById(id);
      else response = await GetAllTheaters();
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
    let response = null;
    try {
      if (id) response = await GetMovieById(id);
      else response = await GetAllMovies();
      if (response.status == 200) {
        setMovie(response.data);
      } else {
        message.error(response.data);
      }
    } catch (error) {
      message.error(error);
    }
  };

  const fetchShowtimes = async (theaterId, movieId) => {
    try {
      let response = null;
      if (movieId && !theaterId) response = await GetShowsByMovie(movieId);
      else if (!movieId && theaterId)
        response = await GetShowsByTheater(theaterId);
      else response = await GetShowsByTheaterAndMovie(theaterId, movieId);

      if (response.status == 200) {
        const formattedShowTimes = response.data.map((showtime) => ({
          ...showtime,
          id: showtime.id,
          movieID: showtime.movieId,
          theaterID: showtime.theaterScreenId,
          time: showtime.time,
        }));
        const showtimesByDate = formattedShowTimes.reduce((acc, showtime) => {
          const date = formatDate(showtime.time);
          const showtimeDate = new Date(showtime.time);
          if (showtimeDate >= new Date()) {
            acc[date] = acc[date] || [];
            acc[date].push(showtime);
          }
          return acc;
        }, {});
        setShowtimes(showtimesByDate);
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
    }).format(new Date(date));
  };

  const formatTime = (date) => {
    return new Intl.DateTimeFormat("en-US", {
      hour: "numeric",
      minute: "numeric",
      timeZone: "UTC",
    }).format(new Date(date));
  };

  useEffect(() => {
    fetchMovieData(movieId);
    fetchTheaterData(theaterId);
    fetchShowtimes(theaterId, movieId);
  }, []);

  useEffect(() => {
    
    if (movieId) setSelectedMovie(movie);
    if (theaterId) setSelectedTheater(theater);
    setFilteredShowtimes(showtimes);
  }, [showtimes]);

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
            <h1>{selectedMovie.movieName}</h1>
            <p>Duration: {selectedMovie.runningTime}</p>
            <p>Release Date: {formatDate(selectedMovie.releaseDate)}</p>
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
          <h1>{selectedMovie.movieName}</h1>
          <p>Duration: {selectedMovie.runningTime}</p>
          <p>Release Date: {formatDate(selectedMovie.releaseDate)}</p>
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
    if (theaterId && !movieId) {
      // Show movies and their showtimes if only theater is known
      return movie?.map((movie, index) => {
        const movieShowtimes = Object.entries(showtimes)
          .filter(([date, times]) =>
            times.some((showtime) => showtime.movieID === movie.movieId)
          )
          .reduce((acc, [date, times]) => {
            acc[date] = times.filter(
              (showtime) => showtime.movieID === movie.movieId
            );
            return acc;
          }, {});
        if (movieShowtimes.length === 0) return null;
        console.log(movieShowtimes);

        return (
          Object.keys(movieShowtimes).length > 0 && (
          <div key={1} className="showtimes mb-3 p-3 shadow-sm rounded">
            <h3>{movie.movieName}</h3>
            {Object.entries(movieShowtimes).map(([date, times], idx) => (
              <Row key={idx} gutter={8}>
                <Col span={24}>
                  <h6>{date}</h6>
                </Col>
                {times.map((showtime, timeIdx) => (
                  <Col key={timeIdx} span={4}>
                    <Button
                      className="time-button"
                      type="primary"
                      onClick={() => selectShow(selectedTheater, showtime.time)}
                    >
                      {formatTime(showtime.time)}
                    </Button>
                  </Col>
                ))}
              </Row>
            ))}
          </div>
        ));
      });
    } else if (!theaterId && movieId) {
      // Show theaters and their showtimes if only movie is known
      console.log(theater);
      return theater?.map((theater, index) => {
        const theaterShowtimes = Object.entries(showtimes)
          .filter(([date, times]) =>
            times.some((showtime) => showtime.theaterID === theater.id)
          )
          .reduce((acc, [date, times]) => {
            acc[date] = times.filter(
              (showtime) => showtime.theaterID === theater.id
            );
            return acc;
          }, {});
        if (theaterShowtimes.length === 0) return null;
        console.log(theaterShowtimes);

        return (
          Object.keys(theaterShowtimes).length > 0 && (
          <div key={index} className="theater mb-3 p-3 shadow-sm rounded">
            <h3>{theater.name}</h3>
            <p>{theater.address}</p>
            {Object.entries(theaterShowtimes).map(([date, times], idx) => (
              <Row key={idx} gutter={8}>
                <Col span={24}>
                  <h6>{date}</h6>
                </Col>
                {times.map((showtime, timeIdx) => (
                  <Col key={timeIdx} span={4}>
                    <Button
                      className="time-button"
                      type="primary"
                      onClick={() => selectShow(selectedTheater, showtime.time)}
                    >
                      {formatTime(showtime.time)}
                    </Button>
                  </Col>
                ))}
              </Row>
            ))}
          </div>
        ));
      });
    } else if (theaterId && movieId) {
      // Show only showtimes if both movie and theater are known
      return (
        <div className="showtimes mb-3 p-3 shadow-sm rounded">
          {Object.entries(showtimes).map(([date, times], idx) => (
            <Row key={idx} gutter={8}>
              <Col span={24}>
                <h6>{date}</h6>
              </Col>
              {times.map((showtime, timeIdx) => (
                <Col key={timeIdx} span={4}>
                  <Button
                    className="time-button"
                    type="primary"
                    onClick={() => selectShow(selectedTheater, showtime.time)}
                  >
                    {formatTime(showtime.time)}
                  </Button>
                </Col>
              ))}
            </Row>
          ))}
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
          <strong>Movie:</strong> {selectedMovie?.movieName}
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
