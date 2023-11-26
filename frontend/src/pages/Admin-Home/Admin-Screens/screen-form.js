import { Form, Modal } from "antd";
import React from "react";
import Button from "../../../components/Button";

function ScreenForm({
  showScreenFormModal,
  setShowScreenFormModal,
  formType,
  selectedScreen,
  setSelectedScreen,
}) {
  return (
    <Modal
      title={formType === "add" ? "Add Screen" : "Edit Screen"}
      open={showScreenFormModal}
      onCancel={() => {
        setShowScreenFormModal(false);
        setSelectedScreen(null);
      }}
      footer={null}
    >
      <Form layout="vertical" initialValues={selectedScreen}>
        <Form.Item
          label="Name"
          name="name"
          rules={[{ required: true, message: "Please input screen name!" }]}
        >
          <input type="text" />
        </Form.Item>

        <Form.Item
          label="Seats"
          name="seats"
          rules={[
            { required: true, message: "Please input the number of seats!" },
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
              setShowScreenFormModal(false);
              setSelectedScreen(null);
            }}
          />
          <Button title="Save" type="submit" />
        </div>
      </Form>
    </Modal>
  );
}

export default ScreenForm;
