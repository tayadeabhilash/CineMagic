import React, { useState } from "react";
import { Tabs, Card, Button } from "antd";
import "./tickets.css";
const { TabPane } = Tabs;

const ShowCard = ({ show, isUpcoming, cancelShow }) => (
  <Card className="show-card">
    <div className="card-content">
      <h3>{show.movie}</h3>
      <p>Date: {show.date}</p>
      <p>Tickets: {show.tickets}</p>
      <p>Location: {show.location}</p>
    </div>
    {isUpcoming && (
      <div className="card-action">
        <Button type="primary" onClick={() => cancelShow(show.id)}>
          Cancel
        </Button>
      </div>
    )}
  </Card>
);

const Sidebar = ({ user }) => {
  const ticketsAvailable = Math.floor(user.points / 50);
  const pointsExpiring = user.pointsExpiring; // Assume this is calculated elsewhere

  return (
    <div className="sidebar">
      <h3>User Points: {user.points}</h3>
      <p>Status: {user.isPremium ? "Premium" : "Standard"} User</p>
      <p>Tickets Available with Points: {ticketsAvailable}</p>
      <p>Points Expiring Soon: {pointsExpiring}</p>
      {/* Add more user-specific information here */}
    </div>
  );
};

const Shows = () => {
  const [activeTab, setActiveTab] = useState("upcoming");
  const [user, setUser] = useState({
    points: 1200,
    isPremium: true,
    pointsExpiring: 200,
  });

  const [pastShows, setPastShows] = useState([
    {
      id: "1",
      movie: "The Shawshank Redemption",
      date: "2021-12-01",
      tickets: 2,
      location: "Regal Cinema",
    },
    {
      id: "2",
      movie: "The Godfather",
      date: "2021-11-15",
      tickets: 3,
      location: "AMC Theater",
    },
  ]);
  const [upcomingShows, setUpcomingShows] = useState([
    {
      id: "3",
      movie: "Inception",
      date: "2023-12-10",
      tickets: 2,
      location: "Cinemark",
    },
    {
      id: "4",
      movie: "Interstellar",
      date: "2023-12-20",
      tickets: 4,
      location: "IMAX",
    },
  ]);

  const cancelShow = (id) => {
    setUpcomingShows(upcomingShows.filter((show) => show.id !== id));
  };

  return (
    <div className="page-container">
      <Tabs
        defaultActiveKey="upcoming"
        onChange={setActiveTab}
        className="shows-tabs"
      >
        <TabPane tab="Upcoming Shows" key="upcoming">
          <div className="show-cards">
            {upcomingShows.map((show) => (
              <ShowCard
                key={show.id}
                show={show}
                isUpcoming={true}
                cancelShow={cancelShow}
              />
            ))}
          </div>
        </TabPane>
        <TabPane tab="Past Shows" key="past">
          <div className="show-cards">
            {pastShows.map((show) => (
              <ShowCard key={show.id} show={show} isUpcoming={false} />
            ))}
          </div>
        </TabPane>
      </Tabs>
      <Sidebar user={user} />
    </div>
  );
};

export default Shows;
