import { Form, Modal, message } from "antd";
import React from "react";
import Button from "../../components/Button";
import { AddTheater, UpdateTheater } from "../../apicalls/theaters";

function TheaterForm({
  showTheaterFormModal,
  setShowTheaterFormModal,
  formType,
  selectedTheater,
  setSelectedTheater,
  getData,
}) {
  const onFinish = async (data) => {
    const finalData = {
      name: data.name,
      seatingCapacity: data.seatingCapacity,
      multiplexId: 1,
    };
    try {
      let response = null;
      if (formType === "add") {
        response = await AddTheater(finalData);
      } else {
        response = await UpdateTheater(selectedTheater?.id, data);
      }

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
