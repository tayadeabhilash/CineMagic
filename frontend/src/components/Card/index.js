import React from "react";
import "./card.css";

const Card = (props) => {
  return (
    <div className="container-fluid main-card">
      <div className="row main-card-row">
        <img className="movieImg" src={props.imgUrl} alt={props.title} />
        <div className="movieDesc">
          <div className="movieTitle">{props.title}</div>
          <div className="genres text-muted"></div>
        </div>
      </div>
    </div>
  );
};

export default Card;
