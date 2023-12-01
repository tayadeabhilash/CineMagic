import { apiSlice } from "./apiSlice";

export const userApiSlice = apiSlice.injectEndpoints({
  endpoints: (builder) => ({
    login: builder.mutation({
      query: (data) => ({
        url: `iam/login`,
        method: "POST",
        body: data,
      }),
    }),

    register: builder.mutation({
      query: (data) => ({
        url: `iam/signup`,
        method: "POST",
        body: data,
      }),
    }),

    logout: builder.mutation({
      query: () => ({
        url: `iam/logout`,
        method: "POST",
      }),
    }),

    // getUserDetails: builder.query({
    //   query: (id) => ({
    //     url: `${USERS_URL}/${id}`,
    //   }),
    //   keepUnusedDataFor: 5,
    // }),

    // updateUser: builder.mutation({
    //   query: (data) => ({
    //     url: `${USERS_URL}/${data.userId}`,
    //     method: "PUT",
    //     body: data,
    //   }),
    //   invalidatesTags: ["User"],
    // }),
  }),
});

export const { useLoginMutation, useLogoutMutation, useRegisterMutation } =
  userApiSlice;
