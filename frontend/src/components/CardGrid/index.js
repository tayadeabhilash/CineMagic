import React from "react";
import { Card as AntCard } from "antd";

const CardGrid = ({ title, description, image }) => {
  return (
    <AntCard
      hoverable
      style={{ width: "240px", margin: "10px" }}
      cover={<img alt={title} src={image} />}
    >
      <AntCard.Meta title={title} description={description} />
    </AntCard>
  );
};

export default CardGrid;
