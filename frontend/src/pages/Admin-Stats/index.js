import React, { useState } from "react";
import { Bar } from "react-chartjs-2";
import { Select } from "antd";
import "./admin-stats.css";

import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend
);

const AnalyticsDashboard = () => {
  const [selectedPeriod, setSelectedPeriod] = useState(30);

  const occupancyData = [
    { location: "Theater 1", occupancy: 75, movie: "Movie A" },
    { location: "Theater 2", occupancy: 50, movie: "Movie B" },
    { location: "Theater 3", occupancy: 60, movie: "Movie C" },
  ];

  const dataByLocation = {
    labels: occupancyData.map((item) => item.location),
    datasets: [
      {
        label: "Occupancy by Location",
        data: occupancyData.map((item) => item.occupancy),
        backgroundColor: "rgba(255, 99, 132, 0.5)",
      },
    ],
  };

  const dataByMovie = {
    labels: [...new Set(occupancyData.map((item) => item.movie))],
    datasets: [
      {
        label: "Occupancy by Movie",
        data: [...new Set(occupancyData.map((item) => item.movie))].map(
          (movie) =>
            occupancyData
              .filter((item) => item.movie === movie)
              .reduce((acc, cur) => acc + cur.occupancy, 0)
        ),
        backgroundColor: "rgba(54, 162, 235, 0.5)",
      },
    ],
  };

  return (
    <div className="stats-container">
      <h1>Theater Occupancy Analytics</h1>
      <Select
        defaultValue={30}
        style={{ width: 120 }}
        onChange={(value) => setSelectedPeriod(value)}
      >
        <Select.Option value={30}>Last 30 Days</Select.Option>
        <Select.Option value={60}>Last 60 Days</Select.Option>
        <Select.Option value={90}>Last 90 Days</Select.Option>
      </Select>

      <div className="chart-container">
        <h2>Occupancy by Location</h2>
        <Bar data={dataByLocation} />
      </div>

      <div className="chart-container">
        <h2>Occupancy by Movie</h2>
        <Bar data={dataByMovie} />
      </div>
    </div>
  );
};

export default AnalyticsDashboard;
