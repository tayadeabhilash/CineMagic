import React, { useEffect, useState } from "react";
import CardGrid from "../../components/CardGrid";
import { useNavigate, useParams } from "react-router-dom";
import "./movies.css";
import { GetMoviesByTheater, GetTheaterById } from "../../apicalls/theaters";
import { message } from "antd";

const MoviesForTheater = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [theater, setTheater] = useState({
    name: "Cinema Central",
    location: "Downtown",
  });

  const [movies, setMovies] = useState([
    {
      id: 1,
      title: "Space Adventure",
      description: "A journey to the stars",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "The Mystery House",
      description: "A thrilling mystery",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "Romance in Rome",
      description: "A romantic tale",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "Space Adventure",
      description: "A journey to the stars",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "The Mystery House",
      description: "A thrilling mystery",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 2,
      title: "Romance in Rome",
      description: "A romantic tale",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 2,
      title: "Romance in Rome",
      description: "A romantic tale",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 2,
      title: "Romance in Rome",
      description: "A romantic tale",
      image: "https://via.placeholder.com/150",
    },

    {
      id: 2,
      title: "Romance in Rome",
      description: "A romantic tale",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 2,
      title: "Romance in Rome",
      description: "A romantic tale",
      image: "https://via.placeholder.com/150",
    },
  ]);

  const fetchTheaterData = async () => {
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

  const fetchMovies = async () => {
    try {
      const response = await GetMoviesByTheater(id);

      if (response.status == 200) {
        const formattedMovies = response.data.map((movie) => ({
          ...movie,
          id: movie.movieId,
          title: movie.movieName,
          description: movie.synopsis,
        }));
        setMovies(formattedMovies);
      } else {
        message.error(response.data);
      }
    } catch (error) {
      message.error(error);
    }
  };

  const handleCardClick = (theaterId, movieId) => {
    navigate(`/booking?theater=${theaterId}&movie=${movieId}`);
  };

  useEffect(() => {
    window.scrollTo(0, 0);
    fetchTheaterData();
    fetchMovies();
  }, []);

  return (
    <div className="homepage-container">
      <div className="theater-info">
        <h1>{theater?.name}</h1>
        <p>{theater?.location}</p>
        {/* Other theater information */}
      </div>
      <h2>Now Showing</h2>
      <div className="card-grid-container">
        {movies.map((item, index) => (
          <div
            onClick={() => handleCardClick(id, item.id)} // Assuming theater ID is 1 for now
            key={index}
          >
            <CardGrid
              title={item.title}
              description={item.description}
              image={item.image}
            />
          </div>
        ))}
      </div>
    </div>
  );
};

export default MoviesForTheater;
