import React from "react";
import { Carousel } from "antd";
import CardGrid from "../../components/CardGrid";
import { useNavigate } from "react-router-dom";
import "./home.css";

const HomePage = () => {
  const navigate = useNavigate();

  // Sample data for theaters
  const theaters = [
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
  ];

  // Sample data for locations
  const locations = [
    {
      id: 1,
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

  // Sample data for current movies
  const currentMovies = [
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
  ];

  // Sample data for upcoming movies
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
        {data.map((item, index) => (
          <CardGrid
            key={index}
            title={item.title}
            description={item.description}
            image={item.image}
            onClick={() => handleCardClick(type, item.id)}
          />
        ))}
      </Carousel>
    );
  };

  return (
    <div className="homepage-container">
      <h2>Theaters</h2>
      {renderCarousel(theaters, "theater")}

      <h2>Locations</h2>
      {renderCarousel(locations, "location")}

      <h2>Current Movies</h2>
      {renderCarousel(currentMovies, "movie")}

      <h2>Upcoming Movies</h2>
      {renderCarousel(upcomingMovies, "movie")}
    </div>
  );
};

export default HomePage;
