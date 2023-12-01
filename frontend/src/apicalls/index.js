import axios from "axios";

export const axiosInstance = axios.create({
  baseURL: "http://localhost:8080/",
  credentials: "include",
  headers: {
    "Content-Type": "application/json",
    //    authorization: `Bearer ${localStorage.getItem("token")}`,
  },
});
