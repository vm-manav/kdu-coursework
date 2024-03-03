import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { ITransactionData } from "../TypeFiles/PersonalTransactionType";
import { getTransactions } from "../ThunkFiles/GetTransactions";

interface ITransactionDataState {
  transactions: ITransactionData[];
  state: "pending" | "fulfilled" | "error";
  error?: string;
}

const initialState: ITransactionDataState = {
  transactions: [],
  state: "pending",
};

const transactionSlice = createSlice({
  name: "transactions",
  initialState: initialState,
  reducers: {
    addToTransactions: (state, action: PayloadAction<ITransactionData>) => {
      state.transactions.push(action.payload);
    },
  },

  extraReducers(builder) {
    builder
      .addCase(getTransactions.pending, (state) => {
        state.state = "pending";
      })
      .addCase(getTransactions.fulfilled, (state, action) => {
        state.transactions = action.payload;
        state.state = "fulfilled";
      })
      .addCase(getTransactions.rejected, (state, action) => {
        state.error = action.payload as string;
        state.state = "error";
      });
  },
});
export const { addToTransactions } = transactionSlice.actions;
export const transactionReducer = transactionSlice.reducer;
