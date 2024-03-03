import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { IStockData } from "../TypeFiles/StockDataType";
import { getStocks } from "../ThunkFiles/GetStocks";

interface IStockDataState {
  stocks: IStockData[];
  state: "pending" | "fulfilled" | "error";
  error?: string;
}

const initialState: IStockDataState = {
  stocks: [],
  state: "pending",
};

const stockSlice = createSlice({
  name: "stock",
  initialState: initialState,
  reducers: {
    markAsWishlisted: (state, action: PayloadAction<IStockData>) => {
      const stockToMark = state.stocks.find(
        (stock) => stock.stock_symbol === action.payload.stock_symbol
      );
      if (stockToMark) {
        stockToMark.wishlisted = true;
      }
    },

    markAsNotWishlisted: (state, action: PayloadAction<IStockData>) => {
      const stockToMark = state.stocks.find(
        (stock) => stock.stock_symbol === action.payload.stock_symbol
      );
      if (stockToMark) {
        stockToMark.wishlisted = false;
      }
    },
  },

  extraReducers(builder) {
    builder
      .addCase(getStocks.pending, (state) => {
        state.state = "pending";
      })
      .addCase(getStocks.fulfilled, (state, action) => {
        const stockArray = action.payload;
        const sortedArray = stockArray
          .slice()
          .sort((a, b) => a.stock_name.localeCompare(b.stock_name));
        state.stocks = sortedArray.map((stock) => ({
          ...stock,
          wishlisted: false,
        }));

        state.state = "fulfilled";
      })
      .addCase(getStocks.rejected, (state, action) => {
        state.error = action.payload as string;
        state.state = "error";
      });
  },
});

export const { markAsWishlisted, markAsNotWishlisted } = stockSlice.actions;
export const stockReducer = stockSlice.reducer;
