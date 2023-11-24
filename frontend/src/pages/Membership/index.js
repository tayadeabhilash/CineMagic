import React from "react";
import { Card, Button, Row, Col } from "antd";
import { Container } from "react-bootstrap";
import "./membership.css";

const MembershipPage = () => {
  return (
    <div className="membership-page">
      <Container className="mt-5">
        <h1 className="text-center mb-4">
          Unlock Exclusive Benefits with Our Premium Membership!
        </h1>
        <Row gutter={16}>
          <Col xs={24} md={12}>
            <Card title="Standard Membership Benefits" bordered={false}>
              <p>Book movie tickets online</p>
              <p>Access to saved past purchases</p>
              <p>Earn 1 point for every dollar spent</p>
              <p>Standard customer support</p>
              {/* Add more standard perks here */}
            </Card>
          </Col>
          <Col xs={24} md={12}>
            <Card title="Premium Membership - Only $15/Year" bordered={false}>
              <p>All the benefits of a normal membership</p>
              <p>Earn 2 points for every dollar spent</p>
              <p>Ability to book more tickets per transaction</p>
              <p>Priority customer support</p>
              <p>Early access to special screenings and events</p>
              <p>Exclusive discounts on selected items</p>
              {/* Add more premium perks here */}
              <Button type="primary" className="mt-3">
                Upgrade to Premium
              </Button>
            </Card>
          </Col>
        </Row>
      </Container>
    </div>
  );
};

export default MembershipPage;
