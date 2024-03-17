import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { IItemsObject } from "../type/Items-list-type";

interface ItemsState {
  items: IItemsObject[];
}

const initialState: ItemsState = {
  items: [],
};
const itemsSlice = createSlice({
  name: "items",
  initialState: initialState,
  reducers: {
    addItems: (state, action: PayloadAction<IItemsObject>) => {
      return {
        items: [...state.items, action.payload],
      };
    },
    deleteItems: (state, action: PayloadAction<IItemsObject>) => {
      return {
        items: state.items.filter((item) => item.id !== action.payload.id),
      };
    },
    deleteCheckedItems: (state) => {
      return {
        items: state.items.filter((item) => !item.checked),
      };
    },
    checkItem: (state, action: PayloadAction<number>) => {
      return {
        items: state.items.map((item) => {
          if (item.id === action.payload) {
            return { ...item, checked: !item.checked };
          }

          return item;
        }),
      };
    },
    editItem: (state, action: PayloadAction<IItemsObject>) => {
      return {
        items: state.items.map((item) => {
          if (item.id === action.payload.id) {
            return { ...item, item: action.payload.item };
          }
          return item;
        }),
      };
    },
  },
});
export const {
  addItems,
  deleteItems,
  checkItem,
  deleteCheckedItems,
  editItem,
} = itemsSlice.actions;
export const itemsReducer = itemsSlice.reducer;
