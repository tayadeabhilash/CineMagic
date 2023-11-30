import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";
import "./movie.css";
import { GetMovieById } from "../../apicalls/movies";
import { message } from "antd";
import moment from "moment";

const Movie = () => {
  var navigate = useNavigate();
  const { id } = useParams();

  const [movieData, setMovieData] = useState({
    movieId: 1,
    poster:
      "https://m.media-amazon.com/images/M/MV5BMTM1NjUxMDI3OV5BMl5BanBnXkFtZTcwNjg1ODM3OA@@._V1_.jpg",
    movieName: "Jack Reacher: Never Go Back",
    contentRating: "4.19",
    genres: ["Thriller", "Action"],
    runningTime: 143,
    synopsis:
      "Jack Reacher must uncover the truth behind a major government conspiracy in order to clear his name. On the run as a fugitive from the law, Reacher uncovers a potential secret from his past that could change his life forever.",
  });

  const fetchMovieData = async () => {
    try {
      const response = await GetMovieById(id);
      console.log(response);

      if (response.status == 200) {
        setMovieData(response.data);
      } else {
        message.error(response.response.data);
      }
    } catch (error) {
      message.error(error);
      console.log(error);
    }
  };

  useEffect(() => {
    window.scrollTo(0, 0);
    fetchMovieData();
  }, []);

  return (
    <div className="container-fluid movie">
      <div className="row movie-poster-row">
        <div className="col movie-poster-col">
          <div className="movie-det-section">
            <img
              src="https://m.media-amazon.com/images/M/MV5BMTM1NjUxMDI3OV5BMl5BanBnXkFtZTcwNjg1ODM3OA@@._V1_.jpg"
              alt="Movie not found"
              className="movie-poster"
            />
          </div>
        </div>
      </div>
      <div className="row movie-det-row">
        <div className="col-lg-4">
          <img
            src="https://m.media-amazon.com/images/M/MV5BMTM1NjUxMDI3OV5BMl5BanBnXkFtZTcwNjg1ODM3OA@@._V1_.jpg"
            alt="Movie not found"
            className="movie-img"
          />
        </div>
        <div className="col-lg movie-det-col">
          <div className="movie-title">{movieData?.movieName}</div>
          <div className="general-info">
            <span className="content-rating">{movieData?.contentRating}</span>
            <span className="movie-genres">{movieData?.genres}</span>
            <span className="runtime ">
              {movieData?.runningTime &&
                `${moment.duration(movieData.runningTime, "minutes").hours()}h 
     ${moment.duration(movieData.runningTime, "minutes").minutes()}m`}
            </span>
          </div>
          {movieData?.synopsis != null ? (
            <div className="overview">
              <h3>Overview</h3>
              <span>{movieData?.synopsis}</span>
            </div>
          ) : (
            ""
          )}

          <div className="book-ticket-btn">
            <Button
              variant="primary"
              type="submit"
              className="book-tkt-btn"
              onClick={() => navigate(`/booking?movie=${id}`)}
            >
              Book Tickets
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Movie;
