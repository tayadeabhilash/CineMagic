import { Form, Modal } from "antd";
import React from "react";
import Button from "../../components/Button";

function TheatreForm({
  showTheatreFormModal,
  setShowTheatreFormModal,
  formType,
  selectedTheatre,
  setSelectedTheatre,
}) {
  return (
    <Modal
      title={formType === "add" ? "Add Theatre" : "Edit Theatre"}
      open={showTheatreFormModal}
      onCancel={() => {
        setShowTheatreFormModal(false);
        setSelectedTheatre(null);
      }}
      footer={null}
    >
      <Form layout="vertical" initialValues={selectedTheatre}>
        <Form.Item
          label="Name"
          name="name"
          rules={[{ required: true, message: "Please input your username!" }]}
        >
          <input type="text" />
        </Form.Item>

        <Form.Item
          label="Address"
          name="address"
          rules={[{ required: true, message: "Please input theatre address!" }]}
        >
          <textarea type="text" />
        </Form.Item>

        <Form.Item
          label="Phone Number"
          name="phone"
          rules={[
            { required: true, message: "Please input theatre phone number!" },
          ]}
        >
          <input type="text" />
        </Form.Item>

        <Form.Item
          label="Email"
          name="email"
          rules={[{ required: true, message: "Please input theatre email!" }]}
        >
          <input type="text" />
        </Form.Item>
        <div className="flex justify-end gap-1">
          <Button
            title="Cancel"
            variant="outlined"
            type="button"
            onClick={() => {
              setShowTheatreFormModal(false);
              setSelectedTheatre(null);
            }}
          />
          <Button title="Save" type="submit" />
        </div>
      </Form>
    </Modal>
  );
}

export default TheatreForm;
