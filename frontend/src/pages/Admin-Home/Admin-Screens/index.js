import React, { useState } from "react";
import { Table, Button } from "antd";
import { useParams, useLocation } from "react-router-dom";
import "../admin.css";
import Shows from "./shows.js";
import ScreenForm from "./screen-form.js";

function Screens() {
  let { id } = useParams();
  let location = useLocation();

  let theaterDetails = location.state?.theater;

  const [screens, setScreens] = useState([
    {
      id: 1,
      name: "Screen 1",
      seats: 100,
    },
  ]);
  const [selectedScreen, setSelectedScreen] = useState(null);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [modalType, setModalType] = useState("add");
  const [openShowsModal, setOpenShowsModal] = useState(false);

  const columns = [
    {
      title: "Screen Name",
      dataIndex: "name",
    },
    {
      title: "Seats",
      dataIndex: "seats",
    },
    {
      title: "Action",
      dataIndex: "action",
      render: (text, record) => {
        return (
          <div className="theatre-icons">
            <i className="ri-delete-bin-line"></i>
            <i
              className="ri-pencil-line"
              onClick={() => {
                setModalType("edit");
                setSelectedScreen(record);
                setIsModalVisible(true);
              }}
            ></i>
            <span
              className="underline"
              onClick={() => {
                setSelectedScreen(record);
                setOpenShowsModal(true);
              }}
            >
              Shows
            </span>
          </div>
        );
      },
    },
  ];

  const handleAdd = () => {
    setModalType("add");
    setIsModalVisible(true);
  };

  return (
    <div>
      <h1>{theaterDetails?.name}</h1>
      <Button type="primary" onClick={handleAdd}>
        Add Screen
      </Button>
      <Table dataSource={screens} columns={columns} rowKey="id" />

      {isModalVisible && (
        <ScreenForm
          showScreenFormModal={isModalVisible}
          setShowScreenFormModal={setIsModalVisible}
          formType={modalType}
          selectedScreen={selectedScreen}
          setSelectedScreen={setSelectedScreen}
        />
      )}

      {openShowsModal && (
        <Shows
          openShowsModal={openShowsModal}
          setOpenShowsModal={setOpenShowsModal}
          screen={selectedScreen}
        />
      )}
    </div>
  );
}

export default Screens;
