import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { IStockTransaction } from "../TypeFiles/StockTransactionHistoryType";

interface StockHistoryState {
  transactions: IStockTransaction[];
}

const initialState: StockHistoryState = {
  transactions: [],
};

const stockHistorySlice = createSlice({
  name: "stockHistory",
  initialState,
  reducers: {
    addTransaction: (state, action: PayloadAction<IStockTransaction>) => {
      state.transactions.push(action.payload);
    },
    resetTransactionHistory: (state) => {
      state.transactions = [];
    },
  },
});
export const { addTransaction, resetTransactionHistory } =
  stockHistorySlice.actions;
export const stockHistoryReducer = stockHistorySlice.reducer;
