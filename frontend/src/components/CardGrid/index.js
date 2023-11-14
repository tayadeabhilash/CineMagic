import React from "react";
import Card from "../Card";

const movie = {
  id: "1",
  title: "Title",
  genres: "Genre",
  imgUrl: "#",
  key: "123",
};

const CardGrid = () => {
  return (
    <div className="container-fluid card-grid">
      <div className="rows cards">
        <div className="col card-items">
          <div className="card-grid-container">
            <a to="#" className="movie-links">
              {/*NavLink*/}
              <div className="card-grid-item">
                <Card
                  id={movie.id}
                  title={movie.title}
                  genres={movie.genres}
                  imgUrl={movie.image}
                  key={movie.id}
                />
              </div>
            </a>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CardGrid;
