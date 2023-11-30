import { fetchBaseQuery, createApi } from "@reduxjs/toolkit/query/react";

import { logout } from "./authSlice";

const baseQuery = fetchBaseQuery({
  baseUrl: "http://localhost:8080/v0/",
  credentials: "include",
});

async function baseQueryWithAuth(args, api, extra) {
  const result = await baseQuery(args, api, extra);

  if (result.error && result.error.status === 401) {
    api.dispatch(logout());
  }
  return result;
}

export const apiSlice = createApi({
  baseQuery: baseQueryWithAuth,
  tagTypes: ["User"],
  endpoints: (builder) => ({}),
});
