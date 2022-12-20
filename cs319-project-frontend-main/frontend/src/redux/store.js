import { configureStore } from '@reduxjs/toolkit'
import { apiSlice } from './api/apiSlice'

export default configureStore({
  reducer: {
    [apiSlice.reducerPath]: apiSlice.reducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(apiSlice.middleware),
})