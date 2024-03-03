import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { IStockData } from "../TypeFiles/StockDataType";

interface IWishListState {
  wishlist: IStockData[];
}

const initialState: IWishListState = {
  wishlist: [],
};

const WishListSlice = createSlice({
  name: "wishlist",
  initialState: initialState,
  reducers: {
    addItem: (state, action: PayloadAction<IStockData>) => {
      const newItem = { ...action.payload, wishlisted: true };
      return { wishlist: [...state.wishlist, newItem] };
    },
    removeItem: (state, action: PayloadAction<IStockData>) => {
      const stockToRemove = action.payload;
      state.wishlist = state.wishlist.filter(
        (stock) => stock.stock_symbol !== stockToRemove.stock_symbol
      );
      return state;
    },
  },
});

export const { addItem, removeItem } = WishListSlice.actions;
export const wishListReducer = WishListSlice.reducer;
