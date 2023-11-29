import React from "react";
import "./admin.css";
import { Tabs } from "antd";
import MoviesList from "./movies-list";
import TheatersList from "./theaters-list";

function Admin() {
  return (
    <div className="admin-container">
      <div className="admin-home">
        <h1>Admin</h1>

        <Tabs defaultActiveKey="1">
          <Tabs.TabPane tab="Movies" key="1">
            <MoviesList />
          </Tabs.TabPane>
          <Tabs.TabPane tab="Theaters" key="2">
            <TheatersList />
          </Tabs.TabPane>
        </Tabs>
      </div>
    </div>
  );
}

export default Admin;
