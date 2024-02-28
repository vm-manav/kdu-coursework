import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { IProductDetails } from "../Types/Item-type";

interface ItemsState {
  displayItems: IProductDetails[];
}

const initialState: ItemsState = {
  displayItems: [],
};

const displayItemsSlice = createSlice({
  name: "displayItems",
  initialState: initialState,
  reducers: {
    setDisplayItems: (state, action: PayloadAction<IProductDetails[]>) => {
      return {
        displayItems: [...action.payload],
      };
    },
  },
});
export const { setDisplayItems } = displayItemsSlice.actions;
export const displayItemsReducer = displayItemsSlice.reducer;
