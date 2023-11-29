import axios from "axios";

export const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/",
  headers: {
    "Content-Type": "application/json",
    //    authorization: `Bearer ${localStorage.getItem("token")}`,
  },
});

// curl --location 'https://7d543f24-8086-4f5b-bd2f-c3ab9c8510e2.mock.pstmn.io/screen' \
// --data ''
