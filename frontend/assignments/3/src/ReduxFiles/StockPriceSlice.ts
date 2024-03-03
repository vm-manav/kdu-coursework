import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { IStockPrice } from "../TypeFiles/StockPriceType";

interface StockPriceState {
  prices: IStockPrice[];
}

const initialState: StockPriceState = {
  prices: [],
};

const stockPriceSlice = createSlice({
  name: "stockPrice",
  initialState,
  reducers: {
    addStockPrice: (state, action: PayloadAction<IStockPrice>) => {
      state.prices = [...state.prices, action.payload];
    },
    resetStockPrices: (state) => {
      state.prices = [];
    },
  },
});

export const { addStockPrice, resetStockPrices } = stockPriceSlice.actions;
export const stockPriceReducer = stockPriceSlice.reducer;
