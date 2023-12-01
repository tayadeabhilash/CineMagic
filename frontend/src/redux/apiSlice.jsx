import { fetchBaseQuery, createApi } from "@reduxjs/toolkit/query/react";

import { logout } from "./authSlice";

const baseQuery = fetchBaseQuery({
  baseUrl:
    "https://bee5-2601-646-a100-cbf0-e164-ed22-95cd-68aa.ngrok-free.app/v0/",
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
