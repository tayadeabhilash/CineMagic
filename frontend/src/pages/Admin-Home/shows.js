import { Col, Form, Modal, Row, Table, message } from "antd";
import React, { useEffect, useState } from "react";
import Button from "../../components/Button";
import "./admin.css";
import { GetAllMovies, GetMovieById } from "../../apicalls/movies";
import {
  AddShow,
  DeleteShow,
  GetShowsByTheater,
  UpdateShow,
} from "../../apicalls/theaters";
import moment from "moment";

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

  const fetchMovieNameById = async (movieId) => {
    try {
      const response = await GetMovieById(movieId);
      return response.status == 200 ? response.data.movieName : "Unknown";
    } catch (error) {
      console.error("Error fetching movie details:", error);
      return "Unknown";
    }
  };

  const getData = async () => {
    try {
      const moviesResponse = await GetAllMovies();

      if (moviesResponse.status == 200) {
        setMovies(moviesResponse.data);
      } else {
        message.error(moviesResponse.message);
      }

      const showsResponse = await GetShowsByTheater(theater.id);
      if (showsResponse.status == 200) {
        const showsWithMoviesAndDateTime = await Promise.all(
          showsResponse.data.map(async (show) => {
            const movieName = await fetchMovieNameById(show.movieId);

            const showDateTime = moment(show.time);
            const date = showDateTime.format("YYYY-MM-DD");
            const time = showDateTime.format("HH:mm:ss");

            return {
              id: show.id,
              price: show.price,
              movie: movieName,
              date,
              time,
              movieId: show.movieId,
            };
          })
        );

        setShows(showsWithMoviesAndDateTime);
        //console.log(showsWithMoviesAndDateTime);
      } else {
        message.error(showsResponse.message);
      }
    } catch (error) {
      message.error(error.message);
    }
  };

  const handleAddShow = async (data) => {
    const dateTime = `${data.date} ${data.time}`;
    const formattedDateTime = moment(
      dateTime,
      "YYYY-MM-DD HH:mm"
    ).toISOString();

    const finalData = {
      movieId: data.movieId,
      theaterScreenId: theater.id,
      price: data.price,
      time: formattedDateTime,
    };

    try {
      const response = await AddShow(finalData);

      if (response.status == 200) {
        message.success("Show Added Successfully!");
        getData();
        setView("table");
      } else {
        message.error(response?.message);
      }
    } catch (error) {
      message.error(error.message);
    }
  };

  const handleDelete = async (id) => {
    try {
      const response = await DeleteShow(id);

      if (response.status == 200) {
        message.success("Show Deleted Successfully!");
        getData();
      } else {
        message.error(response?.message);
      }
    } catch (error) {
      message.error(error.message);
    }
  };

  const editShow = (record) => {
    setEditingShow(record);
    console.log(record);
    setView("form");
  };

  const onEdit = async (formValues) => {
    // Format the date and time to the required format
    const dateTime = `${formValues.date} ${formValues.time}`;
    const formattedDateTime =
      formValues.date + "T" + formValues.time + ".000+00:00";

    const updatedData = {
      id: editingShow.id,
      movieId: Number(formValues.movieId),
      theaterScreenId: theater.id,
      price: Number(formValues.price),
      time: formattedDateTime,
    };

    try {
      const response = await UpdateShow(updatedData);

      if (response.status == 200) {
        message.success("Show Updated Successfully!");
        getData();
      } else {
        message.error(response?.message);
      }
    } catch (error) {
      message.error("Error updating show: " + error.message);
    }

    setView("table");
    setEditingShow(null);
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

  // const onFinish = (values) => {
  //   handleSave(values);
  //   handleAddShow(values);
  // };

  const onFinish = (values) => {
    if (editingShow) {
      onEdit(values);
    } else {
      handleAddShow(values);
    }
  };

  const columns = [
    // {
    //   title: "Show Name",
    //   dataIndex: "name",
    // },
    {
      title: "Date",
      dataIndex: "date",
      render: (text, record) => {
        return moment(text).format("MMM Do YYYY");
      },
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
      dataIndex: "price",
    },
    {
      title: "Total Seats",
      dataIndex: "totalSeats",
    },
    // {
    //   title: "Available Seats",
    //   dataIndex: "availableSeats",
    // },
    {
      title: "Action",
      dataIndex: "action",
      render: (text, record) => (
        <div className="theater-icons">
          <i
            className="ri-delete-bin-line"
            onClick={() => {
              handleDelete(record.id);
            }}
          ></i>
          <i className="ri-pencil-line" onClick={() => editShow(record)}></i>
        </div>
      ),
    },
  ];

  useEffect(() => {
    getData();
  }, []);

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
          onFinish={onFinish}
          initialValues={editingShow ? { ...editingShow } : {}}
        >
          <Row gutter={[16, 16]}>
            <Col span={8}>
              <Form.Item
                label="Show Name"
                name="name"
                rules={[{ message: "Please input show name!" }]}
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
                <input
                  type="date"
                  min={new Date().toISOString().split("T")[0]}
                />
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
                name="movieId"
                rules={[{ required: true, message: "Please select movie!" }]}
              >
                <select>
                  <option value="">Select Movie</option>
                  {movies.map((movie) => (
                    <option value={movie.movieId}>{movie.movieName}</option>
                  ))}
                </select>
              </Form.Item>
            </Col>

            <Col span={8}>
              {/* <div className="ticket-price-buttons">
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
              </div> */}
              <Form.Item
                label="Price"
                name="price"
                rules={[
                  { required: true, message: "Please input total seats!" },
                ]}
              >
                <input type="number" />
              </Form.Item>
            </Col>

            <Col span={8}>
              <Form.Item
                label="Total Seats"
                name="totalSeats"
                rules={[{ message: "Please input total seats!" }]}
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
