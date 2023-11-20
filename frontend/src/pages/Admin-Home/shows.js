import { Col, Form, Modal, Row, Table } from "antd";
import React from "react";
import Button from "../../components/Button";
import "./admin.css";

function Shows({ openShowsModal, setOpenShowsModal, theatre }) {
  const [view, setView] = React.useState("table");
  const [shows, setShows] = React.useState([
    {
      name: "3D",
      date: "19-05-2000",
      time: "1:00 pm",
      movie: "Riders of Justice",
      ticketPrice: "$10",
      totalSeats: 100,
      availableSeats: 30,
    },
  ]);
  const [movies, setMovies] = React.useState([]);

  const columns = [
    {
      title: "Show Name",
      dataIndex: "name",
    },
    {
      title: "Date",
      dataIndex: "date",
    },
    {
      title: "Time",
      dataIndex: "time",
    },
    {
      title: "Movie",
      dataIndex: "movie",
    },
    {
      title: "Ticket Price",
      dataIndex: "ticketPrice",
    },
    {
      title: "Total Seats",
      dataIndex: "totalSeats",
    },
    {
      title: "Available Seats",
      dataIndex: "availableSeats",
    },
    {
      title: "Action",
      dataIndex: "action",
      render: (text, record) => {
        return <i className="ri-delete-bin-line"></i>;
      },
    },
  ];

  return (
    <Modal
      title=""
      open={openShowsModal}
      onCancel={() => setOpenShowsModal(false)}
      width={1400}
      footer={null}
    >
      <h1 className="shows-heading">Theatre : {theatre.name}</h1>
      <hr />

      <div className="shows-subheading">
        <h1>{view === "table" ? "Shows" : "Add Show"}</h1>
        {view === "table" && (
          <Button
            variant="outlined"
            title="Add Show"
            onClick={() => {
              setView("form");
            }}
          />
        )}
      </div>

      {view === "table" && <Table columns={columns} dataSource={shows} />}

      {view === "form" && (
        <Form layout="vertical">
          <Row gutter={[16, 16]}>
            <Col span={8}>
              <Form.Item
                label="Show Name"
                name="name"
                rules={[{ required: true, message: "Please input show name!" }]}
              >
                <input />
              </Form.Item>
            </Col>

            <Col span={8}>
              <Form.Item
                label="Date"
                name="date"
                rules={[{ required: true, message: "Please input show date!" }]}
              >
                <input type="date" />
              </Form.Item>
            </Col>

            <Col span={8}>
              <Form.Item
                label="Time"
                name="time"
                rules={[{ required: true, message: "Please input show time!" }]}
              >
                <input type="time" />
              </Form.Item>
            </Col>

            <Col span={8}>
              <Form.Item
                label="Movie"
                name="movie"
                rules={[{ required: true, message: "Please select movie!" }]}
              >
                <select>
                  <option value="">Select Movie</option>
                  {movies.map((movie) => (
                    <option value={movie._id}>{movie.title}</option>
                  ))}{" "}
                </select>
              </Form.Item>
            </Col>

            <Col span={8}>
              <Form.Item
                label="Ticket Price"
                name="ticketPrice"
                rules={[
                  { required: true, message: "Please input ticket price!" },
                ]}
              >
                <input type="number" />
              </Form.Item>
            </Col>

            <Col span={8}>
              <Form.Item
                label="Total Seats"
                name="totalSeats"
                rules={[
                  { required: true, message: "Please input total seats!" },
                ]}
              >
                <input type="number" />
              </Form.Item>
            </Col>
          </Row>
          <div className="shows-button">
            <Button
              variant="outlined"
              title="Cancel"
              onClick={() => {
                setView("table");
              }}
            />

            <Button variant="contained" title="SAVE" type="submit" />
          </div>
        </Form>
      )}
    </Modal>
  );
}

export default Shows;
