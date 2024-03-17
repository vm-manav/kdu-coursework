import { createSlice } from "@reduxjs/toolkit";
import { IProductDetails } from "../Types/Item-type";
import { getProduct } from "../Thrunk/GetProduct";

interface ItemsState {
  items: IProductDetails[];
  state: "pending" | "fulfilled" | "error";
  error?: string;
}

const initialState: ItemsState = {
  items: [],
  state: "pending",
};

const itemsSlice = createSlice({
  name: "items",
  initialState: initialState,
  reducers: {},
  extraReducers(builder) {
    builder
      .addCase(getProduct.pending, (state) => {
        state.state = "pending";
      })
      .addCase(getProduct.fulfilled, (state, action) => {
        state.items = action.payload;
        state.state = "fulfilled";
      })
      .addCase(getProduct.rejected, (state, action) => {
        state.error = action.payload as string;
        state.state = "error";
      });
  },
});

export const itemsReducer = itemsSlice.reducer;
