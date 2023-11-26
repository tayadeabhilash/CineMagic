import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";
import "./movie.css";

const Movie = () => {
  var navigate = useNavigate();
  const { id } = useParams();
  const [movieData, setMovieData] = useState({
    image:
      "https://m.media-amazon.com/images/M/MV5BMTM1NjUxMDI3OV5BMl5BanBnXkFtZTcwNjg1ODM3OA@@._V1_.jpg",
    title: "Jack Reacher: Never Go Back",
    contentRating: "4.19",
    genres: ["Thriller", "Action"],
    runtimeStr: "1:49",
    plot: "Jack Reacher must uncover the truth behind a major government conspiracy in order to clear his name. On the run as a fugitive from the law, Reacher uncovers a potential secret from his past that could change his life forever.",
  });

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <div className="container-fluid movie">
      <div className="row movie-poster-row">
        <div className="col movie-poster-col">
          <div className="movie-det-section">
            <img
              src={movieData?.image}
              alt="Movie not found"
              className="movie-poster"
            />
          </div>
        </div>
      </div>
      <div className="row movie-det-row">
        <div className="col-lg-4">
          <img
            src={movieData?.image}
            alt="Movie not found"
            className="movie-img"
          />
        </div>
        <div className="col-lg movie-det-col">
          <div className="movie-title">{movieData?.title}</div>
          <div className="general-info">
            <span className="content-rating">{movieData?.contentRating}</span>
            <span className="movie-genres">{movieData?.genres}</span>
            <span className="runtime ">{movieData?.runtimeStr}</span>
          </div>
          {movieData?.plot != null ? (
            <div className="overview">
              <h3>Overview</h3>
              <span>{movieData?.plot}</span>
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
