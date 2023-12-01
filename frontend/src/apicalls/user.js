import { axiosInstance } from ".";

export const Login = async (payload) => {
  try {
    const response = await axiosInstance.post("v0/iam/login", payload);
    return response;
  } catch (error) {
    return error.response;
  }
};

export const SignUp = async (payload) => {
  try {
    const response = await axiosInstance.post("v0/iam/signup", payload);
    return response;
  } catch (error) {
    return error.response;
  }
};

export const Logout = async () => {
  try {
    const response = await axiosInstance.post("v0/iam/logout");
    return response;
  } catch (error) {
    return error.response;
  }
};

export const UpgradeMembership = async () => {
  try {
    const response = await axiosInstance.post("v0/users/me/upgradeMembership");
    return response;
  } catch (error) {
    return error.response;
  }
};
