import { Form, Modal, message } from "antd";
import React, { useEffect, useState } from "react";
import Button from "../../components/Button";
import { AddTheater, UpdateTheater } from "../../apicalls/theaters";
import { GetAllLocations } from "../../apicalls/theaters";

function TheaterForm({
  showTheaterFormModal,
  setShowTheaterFormModal,
  formType,
  selectedTheater,
  setSelectedTheater,
  getData,
}) {
  const [locations, setLocations] = useState([]);

  const getLocations = async () => {
    try {
      const res = await GetAllLocations();
      console.log(res);

      if (res.status == 200) {
        setLocations(res.data);
      } else {
        console.error(res?.errorMessage);
      }
    } catch (error) {
      console.error("Error Fetching Locations");
    }
  };

  const onFinish = async (data) => {
    try {
      let response = null;
      if (formType === "add") {
        response = await AddTheater(data);
      } else {
        response = await UpdateTheater(selectedTheater?.id, data);
      }

      console.log(response);
      if (response.status == 200) {
        //message.success(response.data);  works on add but not on edit
        setShowTheaterFormModal(false);
        setSelectedTheater(null);
        getData();
      } else {
        message.error(response.message);
      }
    } catch (error) {
      message.error(error.message);
    }
  };

  useEffect(() => {
    getLocations();
  }, []);

  return (
    <Modal
      title={formType === "add" ? "Add Theater" : "Edit Theater"}
      open={showTheaterFormModal}
      onCancel={() => {
        setShowTheaterFormModal(false);
        setSelectedTheater(null);
      }}
      footer={null}
    >
      <Form
        layout="vertical"
        initialValues={selectedTheater}
        onFinish={onFinish}
      >
        <Form.Item
          label="Name"
          name="name"
          rules={[{ required: true, message: "Please input theater name!" }]}
        >
          <input type="text" />
        </Form.Item>

        <Form.Item
          label="Address"
          name="address"
          rules={[{ message: "Please input theater address!" }]}
        >
          <textarea type="text" />
        </Form.Item>

        <Form.Item
          label="Phone Number"
          name="phone"
          rules={[{ message: "Please input theater phone number!" }]}
        >
          <input type="text" />
        </Form.Item>

        <Form.Item
          label="Email"
          name="email"
          rules={[{ message: "Please input theater email!" }]}
        >
          <input type="text" />
        </Form.Item>

        <Form.Item
          label="Location"
          name="locationId"
          rules={[{ required: true, message: "Please select location!" }]}
        >
          <select>
            <option value="">Select Location</option>
            {locations?.map((location) => (
              <option value={location.id}>{location.name}</option>
            ))}
          </select>
        </Form.Item>

        <Form.Item
          label="Seating Capacity"
          name="seatingCapacity"
          rules={[
            { required: true, message: "Please input the setaing capacity!" },
          ]}
        >
          <input type="number" />
        </Form.Item>
        <div className="flex justify-end gap-1">
          <Button
            title="Cancel"
            variant="outlined"
            type="button"
            onClick={() => {
              setShowTheaterFormModal(false);
              setSelectedTheater(null);
            }}
          />
          <Button title="Save" type="submit" />
        </div>
      </Form>
    </Modal>
  );
}

export default TheaterForm;
