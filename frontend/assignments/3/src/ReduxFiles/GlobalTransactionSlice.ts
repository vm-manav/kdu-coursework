import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { IGlobalTransaction } from "../TypeFiles/GlobalTransactionType";

interface IGlobalTransactionData {
  data: IGlobalTransaction[];
}

const initialState: IGlobalTransactionData = {
  data: [],
};

const globalTransactionSlice = createSlice({
  name: "globalTransaction",
  initialState,
  reducers: {
    addGlobalTransaction: (
      state,
      action: PayloadAction<IGlobalTransaction>
    ) => {
      state.data.push(action.payload);
    },
    resetGlobalTransaction: (state) => {
      state.data = [];
    },
  },
});

export const { addGlobalTransaction, resetGlobalTransaction } =
  globalTransactionSlice.actions;
export const globalTransactionReducer = globalTransactionSlice.reducer;
