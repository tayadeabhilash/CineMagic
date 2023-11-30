import { fetchBaseQuery, createApi } from "@reduxjs/toolkit/query/react";

import { logout } from "./authSlice";

const baseQuery = fetchBaseQuery({
  baseUrl: "https://8eb9-96-74-127-99.ngrok-free.app/v0/",
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
