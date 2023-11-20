import React, { useState } from "react";
import { Table } from "antd";
import "./admin.css";
import Button from "../../components/Button";
import TheatreForm from "./theatre-form";
import Shows from "./shows";

function TheatresList() {
  const [showTheatreFormModal, setShowTheatreFormModal] = useState(false);
  const [selectedTheatre, setSelectedTheatre] = useState(null);
  const [formType, setFormType] = useState("add");
  const [theatres, setTheatres] = useState([
    {
      name: "Screen 2",
      address: "#342",
      phone: 123456,
      email: "asdafdsf",
      isActive: true,
    },
  ]);
  const [openShowsModal, setOpenShowsModal] = useState(false);

  const columns = [
    {
      title: "Name",
      dataIndex: "name",
    },
    {
      title: "Address",
      dataIndex: "address",
    },
    {
      title: "Phone",
      dataIndex: "phone",
    },
    {
      title: "Email",
      dataIndex: "email",
    },
    {
      title: "Status",
      dataIndex: "isActive",
      render: (text, record) => {
        if (text) {
          return "Approved";
        } else {
          return "Pending/ Blocked";
        }
      },
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
                setFormType("edit");
                setSelectedTheatre(record);
                setShowTheatreFormModal(true);
              }}
            ></i>
            <span
              className="underline"
              onClick={() => {
                setSelectedTheatre(record);
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

  return (
    <div>
      <div className="admin-movies-list">
        <Button
          title="Add Theatre"
          variant="outlined"
          onClick={() => {
            setFormType("add");
            setShowTheatreFormModal(true);
          }}
        />
      </div>

      <Table columns={columns} dataSource={theatres} />

      {showTheatreFormModal && (
        <TheatreForm
          showTheatreFormModal={showTheatreFormModal}
          setShowTheatreFormModal={setShowTheatreFormModal}
          formType={formType}
          selectedTheatre={selectedTheatre}
          setSelectedTheatre={setSelectedTheatre}
        />
      )}

      {openShowsModal && (
        <Shows
          openShowsModal={openShowsModal}
          setOpenShowsModal={setOpenShowsModal}
          theatre={selectedTheatre}
        />
      )}
    </div>
  );
}

export default TheatresList;
