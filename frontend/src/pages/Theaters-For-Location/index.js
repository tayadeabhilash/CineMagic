import React, { useEffect } from "react";
import CardGrid from "../../components/CardGrid";
import { useNavigate } from "react-router-dom";
import "./theaters.css";

const TheatersForLocation = () => {
  const navigate = useNavigate();

  const locationInfo = {
    name: "Downtown",
  };

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
      id: 2,
      title: "Downtown Cineplex",
      description: "The heart of the city's movie scene",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 2,
      title: "Downtown Cineplex",
      description: "The heart of the city's movie scene",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 2,
      title: "Downtown Cineplex",
      description: "The heart of the city's movie scene",
      image: "https://via.placeholder.com/150",
    },

    {
      id: 2,
      title: "Downtown Cineplex",
      description: "The heart of the city's movie scene",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 2,
      title: "Downtown Cineplex",
      description: "The heart of the city's movie scene",
      image: "https://via.placeholder.com/150",
    },
    {
      id: 2,
      title: "Downtown Cineplex",
      description: "The heart of the city's movie scene",
      image: "https://via.placeholder.com/150",
    },
  ];

  const handleCardClick = (theaterId) => {
    navigate(`/booking?theater=${theaterId}`);
  };

  useEffect(() => {
    window.scrollTo(0, 0);
  }, []);

  return (
    <div className="homepage-container">
      <div className="theater-info">
        <h1>{locationInfo.name}</h1>
      </div>
      <h2>Theaters</h2>
      <div className="card-grid-container">
        {theaters.map((item, index) => (
          <div onClick={() => handleCardClick(item.id)} key={index}>
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

export default TheatersForLocation;
