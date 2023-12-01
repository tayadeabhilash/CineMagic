import axios from "axios";

export const axiosInstance = axios.create({
  baseURL:
    "https://bee5-2601-646-a100-cbf0-e164-ed22-95cd-68aa.ngrok-free.app/",
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true,
});
