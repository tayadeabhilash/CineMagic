import { configureStore } from "@reduxjs/toolkit";
import authSliceReducer from "./authSlice";
import { apiSlice } from "./apiSlice";

const store = configureStore({
  reducer: {
    auth: authSliceReducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(apiSlice.middleware),
});

export default store;
