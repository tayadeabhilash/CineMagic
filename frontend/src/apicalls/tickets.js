const { axiosInstance } = require(".");

//user
export const BookTickets = async (payload) => {
  try {
    const response = await axiosInstance.post("booking", payload);
    return response;
  } catch (error) {
    return error.response;
  }
};

//user?
export const GetTicketById = async (id) => {
  try {
    const response = await axiosInstance.get(`booking/${id}`);
    return response;
  } catch (error) {
    return error.response;
  }
};

//user
export const CancelTickets = async (id) => {
  try {
    const response = await axiosInstance.delete(`booking/${id}`);
    return response;
  } catch (error) {
    return error.response;
  }
};

//user
export const GetTicketsByUserId = async (id) => {
  try {
    const response = await axiosInstance.get(`booking/user/${id}`);
    return response;
  } catch (error) {
    return error.response;
  }
};
