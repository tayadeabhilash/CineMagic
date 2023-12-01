import React, { useEffect, useState } from "react";
import { Tabs, Card, Button } from "antd";
import "./tickets.css";
import { GetUpcomingBookings, GetPastBookings } from "../../apicalls/user";
import { useSelector } from "react-redux";
import { message } from "antd";

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

const Shows = () => {
  const [activeTab, setActiveTab] = useState("upcoming");
  const [user, setUser] = useState({
    points: 1200,
    isPremium: true,
    pointsExpiring: 200,
  });

  const { userInfo } = useSelector((state) => state.auth);

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

  const getUpcomingBookings = async () => {
    try {
      const response = await GetUpcomingBookings(userInfo.userId);

      if (response.status === 200) {
        setUpcomingShows(response.data);
      } else {
        message.error(response?.data);
      }
    } catch (error) {
      message.error(error?.message);
    }
  };

  const getPastBookings = async () => {
    try {
      const response = await GetPastBookings(userInfo.userId);

      if (response.status === 200) {
        setPastShows(response.data);
      } else {
        message.error(response?.data);
      }
    } catch (error) {
      message.error(error?.message);
    }
  };

  const Sidebar = () => {
    return (
      <div className="sidebar">
        <h3>User Points: {userInfo.points ? userInfo.points : 0}</h3>
        <p>
          Status:{" "}
          <strong>
            {userInfo.memberType === "PREMIUM" ? "Premium" : "Regular"} Member
          </strong>
        </p>
        <p>
          Equivalent Cash:{" "}
          <strong>
            $
            {!userInfo.points || userInfo.points === 0
              ? 0
              : userInfo.points / 10}
          </strong>
        </p>
      </div>
    );
  };

  const cancelShow = (id) => {
    setUpcomingShows(upcomingShows.filter((show) => show.id !== id));
  };

  useEffect(() => {
    getPastBookings();
    getUpcomingBookings();
  }, []);

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
