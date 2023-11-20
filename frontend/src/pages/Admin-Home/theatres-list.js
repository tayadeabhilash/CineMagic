import React, { useState } from "react";
import { Table } from "antd";
import "./admin.css";
import Button from "../../components/Button";
import TheatreForm from "./theatre-form";

function TheatresList() {
  const [showTheatreFormModal, setShowTheatreFormModal] = useState(false);
  const [selectedTheatre, setSelectedTheatre] = useState(null);
  const [formType = "add", setFormType] = useState("add");
  const [theatres = [], setTheatres] = useState([]);

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
    </div>
  );
}

export default TheatresList;
