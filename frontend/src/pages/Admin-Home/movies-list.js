import React from "react";
import { Table } from "antd";
import "./admin.css";

function MoviesList() {
  const [movies, setMovies] = React.useState([]);

  const columns = [
    {
      title: "Poster",
      dataIndex: "poster",
    },
    {
      title: "Name",
      dataIndex: "title",
    },
    {
      title: "Description",
      dataIndex: "description",
    },
    {
      title: "Duration",
      dataIndex: "duration",
    },
    {
      title: "Genre",
      dataIndex: "genre",
    },
    {
      title: "Language",
      dataIndex: "language",
    },
  ];

  return (
    <div>
      <Table columns={columns} dataSource={movies} />
    </div>
  );
}

export default MoviesList;
