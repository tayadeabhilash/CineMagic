import React, { useState, useEffect } from "react";
import { Carousel, message } from "antd";
import CardGrid from "../../components/CardGrid";
import { useNavigate } from "react-router-dom";
import "./home.css";
import { GetAllMovies } from "../../apicalls/movies";
import { GetAllTheaters } from "../../apicalls/theaters";
import Loader from "../../components/Loader/loader";
import moviePlaceholder from "../../assets/movie-placeholder.png";
import theaterPlaceholder from "../../assets/theater-placeholder.png";

const HomePage = () => {
  const navigate = useNavigate();

  const [isLoading, setIsLoading] = useState(false);
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
      id: 1,
      title: "Romance in Rome",
      description: "A romantic tale",
      image: "https://via.placeholder.com/150",
    },
  ]);

  const [theaters, setTheaters] = useState([
    {
      id: 1,
      title: "Grand Cinema",
      description: "The best movie experience in town",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "Starlight Theater",
      description: "Enjoy movies under the stars",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "Downtown Cineplex",
      description: "The heart of the city's movie scene",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "Grand Cinema",
      description: "The best movie experience in town",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "Starlight Theater",
      description: "Enjoy movies under the stars",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "Downtown Cineplex",
      description: "The heart of the city's movie scene",
      image: "https://via.placeholder.com/150",
    },
  ]);

  const locations = [
    {
      id: 1,
      title: "City Center",
      description: "Located in the heart of the city",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "Suburban Spot",
      description: "Easy parking and great shops",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "Riverside",
      description: "Scenic views and top-notch facilities",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "City Center",
      description: "Located in the heart of the city",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "Suburban Spot",
      description: "Easy parking and great shops",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "Riverside",
      description: "Scenic views and top-notch facilities",
      image: "https://via.placeholder.com/150",
    },
  ];

  const upcomingMovies = [
    {
      id: 1,
      title: "Future World",
      description: "A glimpse into the future",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "The Great Heist",
      description: "An action-packed thriller",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "Comedy Nights",
      description: "Laughs and more laughs",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "Future World",
      description: "A glimpse into the future",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "The Great Heist",
      description: "An action-packed thriller",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 1,
      title: "Comedy Nights",
      description: "Laughs and more laughs",
      image: "https://via.placeholder.com/150",
    },
  ];

  const getMoviesData = async () => {
    try {
      setIsLoading(true);
      const response = await GetAllMovies();

      if (response.status === 200) {
        const formattedMovies = response.data.map((movie) => ({
          ...movie,
          id: movie.movieId,
          title: movie.movieName,
          description: movie.synopsis,
          image: movie.posterUrl ? movie.posterUrl : moviePlaceholder,
        }));
        setMovies(formattedMovies);
      } else {
        message.error(response.data);
      }
      setIsLoading(false);
    } catch (error) {
      setIsLoading(false);
      message.error(error);
    }
  };

  const getTheatersData = async () => {
    try {
      setIsLoading(true);
      const response = await GetAllTheaters();

      if (response.status === 200) {
        const formattedTheaters = response.data.map((theater) => ({
          ...theater,
          title: theater.name,
          image: theater.posterUrl ? theater.posterUrl : theaterPlaceholder,
        }));
        setTheaters(formattedTheaters);
      } else {
        message.error(response.data);
      }
      setIsLoading(false);
    } catch (error) {
      setIsLoading(false);
      message.error(error.message);
    }
  };

  const CustomNextArrow = ({ onClick }) => (
    <div className="custom-next-arrow" onClick={onClick}>
      →
    </div>
  );

  const CustomPrevArrow = ({ onClick }) => (
    <div className="custom-prev-arrow" onClick={onClick}>
      ←
    </div>
  );

  const handleCardClick = (type, id) => {
    navigate(`/${type}/${id}`);
  };

  const renderCarousel = (data, type) => {
    return (
      <Carousel
        slidesToShow={4}
        dots={false}
        swipeToSlide={true}
        draggable={true}
        infinite={true}
        arrows={true}
        nextArrow={<CustomNextArrow />}
        prevArrow={<CustomPrevArrow />}
      >
        {data.map((item) => (
          <div className="card-container">
            <CardGrid
              key={item.id}
              de
              title={item.title}
              description={item.description}
              image={item.image}
              onClick={() => handleCardClick(type, item.id)}
            />
          </div>
        ))}
      </Carousel>
    );
  };

  useEffect(() => {
    getMoviesData();
    getTheatersData();
  }, []);

  if (isLoading) {
    return <Loader />;
  }

  return (
    <div className="homepage-container">
      <h2>Theaters</h2>
      {renderCarousel(theaters, "theater")}

      <h2>Locations</h2>
      {renderCarousel(locations, "location")}

      <h2>Currently Palying</h2>
      {renderCarousel(movies, "movie")}

      <h2>Upcoming Movies</h2>
      {renderCarousel(upcomingMovies, "movie")}
    </div>
  );
};

export default HomePage;
