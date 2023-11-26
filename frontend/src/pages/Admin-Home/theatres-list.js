import React, { useState } from "react";
import { Table } from "antd";
import "./admin.css";
import Button from "../../components/Button";
import TheatreForm from "./theatre-form";
import { useNavigate } from "react-router-dom";

function TheatresList() {
  const navigate = useNavigate();

  const [showTheatreFormModal, setShowTheatreFormModal] = useState(false);
  const [selectedTheatre, setSelectedTheatre] = useState(null);
  const [formType, setFormType] = useState("add");
  const [theatres, setTheatres] = useState([
    {
      key: 1,
      name: "XYZ Theater",
      address: "#342",
      phone: 123456,
      email: "asdafdsf",
      isActive: true,
    },
  ]);

  const handleRowClick = (record) => {
    navigate(`/theater/${record.key}`, { state: { theater: record } });
  };

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
            <i
              className="ri-delete-bin-line"
              onClick={(e) => {
                e.stopPropagation();
              }}
            ></i>
            <i
              className="ri-pencil-line"
              onClick={(e) => {
                e.stopPropagation();
                setFormType("edit");
                setSelectedTheatre(record);
                setShowTheatreFormModal(true);
              }}
            ></i>
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
      <Table
        onRow={(record, rowIndex) => {
          return {
            onClick: (event) => {
              handleRowClick(record);
            },
          };
        }}
        columns={columns}
        dataSource={theatres}
      />
      {showTheatreFormModal && (
        <TheatreForm
          showTheatreFormModal={showTheatreFormModal}
          setShowTheatreFormModal={setShowTheatreFormModal}
          formType={formType}
          selectedTheatre={selectedTheatre}
          setSelectedTheatre={setSelectedTheatre}
        />
      )}
    </div>
  );
}

export default TheatresList;
