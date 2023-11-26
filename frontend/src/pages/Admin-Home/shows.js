import { Col, Form, Modal, Row, Table } from "antd";
import React, { useState } from "react";
import Button from "../../components/Button";
import "./admin.css";

function Shows({ openShowsModal, setOpenShowsModal, theater }) {
  const [view, setView] = useState("table");
  const [shows, setShows] = useState([
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
  const [movies, setMovies] = useState([]);
  const [editingShow, setEditingShow] = useState(null);
  const [isDiscounted, setIsDiscounted] = useState(false);
  const [ticketPrice, setTicketPrice] = useState(20);
  const [priceOptions, setPriceOptions] = useState({
    originalPrice: 10,
    discountedPrice: 7.5,
  });

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
      render: (text, record) => (
        <div className="theatre-icons">
          <i className="ri-delete-bin-line"></i>
          <i className="ri-pencil-line" onClick={() => editShow(record)}></i>
        </div>
      ),
    },
  ];

  const editShow = (record) => {
    setEditingShow(record);
    setView("form");
  };

  const handlePriceSet = (isDiscounted) => {
    const price = isDiscounted
      ? priceOptions.discountedPrice
      : priceOptions.originalPrice;
    setTicketPrice(price);
    setIsDiscounted(isDiscounted);
  };

  const handleSave = (formValues) => {
    if (editingShow) {
      setShows(
        shows.map((show) =>
          show.name === editingShow.name
            ? { ...editingShow, ...formValues }
            : show
        )
      );
    } else {
      setShows([...shows, formValues]);
    }
    setView("table");
    setEditingShow(null);
  };

  return (
    <Modal
      title=""
      open={openShowsModal}
      onCancel={() => setOpenShowsModal(false)}
      width={1400}
      footer={null}
    >
      <h1 className="shows-heading">Theater : {theater?.name}</h1>
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
        <Form
          layout="vertical"
          onFinish={handleSave}
          initialValues={editingShow ? { ...editingShow } : {}}
        >
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
                  ))}
                </select>
              </Form.Item>
            </Col>

            <Col span={8}>
              <div className="ticket-price-buttons">
                <label>Ticket Price</label>
                <button
                  type="button"
                  className={`price-button original-price ${
                    !isDiscounted ? "selected-price" : ""
                  }`}
                  onClick={() => handlePriceSet(false)}
                >
                  Original Price (${priceOptions.originalPrice})
                </button>
                <button
                  type="button"
                  className={`price-button discounted-price ${
                    isDiscounted ? "selected-price" : ""
                  }`}
                  onClick={() => handlePriceSet(true)}
                >
                  Discounted Price (${priceOptions.discountedPrice})
                </button>
              </div>
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
                setEditingShow(null);
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
