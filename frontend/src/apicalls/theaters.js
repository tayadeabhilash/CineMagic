import { axiosInstance } from ".";

//admin
export const AddTheater = async (payload) => {
  try {
    const response = await axiosInstance.post("screen", payload);
    return response;
  } catch (error) {
    return error.response;
  }
};

//admin and user
export const GetAllTheaters = async () => {
  try {
    const response = await axiosInstance.get("screen");
    return response;
  } catch (error) {
    return error.response;
  }
};

//admin and user
export const GetTheaterById = async (id) => {
  try {
    const response = await axiosInstance.get(`screen/${id}`);
    return response;
  } catch (error) {
    return error.response;
  }
};

//admin
export const UpdateTheater = async (id, payload) => {
  try {
    const response = await axiosInstance.put(`screen/${id}`, payload);
    return response;
  } catch (error) {
    return error.response;
  }
};

//admin
export const DeleteTheater = async (id) => {
  try {
    const response = await axiosInstance.delete(`screen/${id}`);
    return response;
  } catch (error) {
    return error.response;
  }
};

//admin
export const AddShow = async (payload) => {
  try {
    const response = await axiosInstance.post("showtime", payload);
    return response;
  } catch (error) {
    return error.response;
  }
};

//admin
export const UpdateShow = async (payload) => {
  try {
    const response = await axiosInstance.put("showtime", payload);
    return response;
  } catch (error) {
    return error.response;
  }
};

//??
export const GetShowById = async (id) => {
  try {
    const response = await axiosInstance.get(`showtime/${id}`);
    return response;
  } catch (error) {
    return error.response;
  }
};

//admin
export const DeleteShow = async (id) => {
  try {
    const response = await axiosInstance.delete(`showtime/${id}`);
    return response;
  } catch (error) {
    return error.response;
  }
};

//user
export const GetShowsByMovie = async (id) => {
  try {
    const response = await axiosInstance.get(`showtime/movie/${id}`);
    return response;
  } catch (error) {
    return error.response;
  }
};

//user and admin
export const GetShowsByTheater = async (id) => {
  try {
    const response = await axiosInstance.get(`showtime/screen/${id}`);
    return response;
  } catch (error) {
    return error.response;
  }
};

//user
export const GetShowsByLocation = async (name) => {
  try {
    const response = await axiosInstance.get(`showtime/location/${name}`);
    return response;
  } catch (error) {
    return error.response;
  }
};
