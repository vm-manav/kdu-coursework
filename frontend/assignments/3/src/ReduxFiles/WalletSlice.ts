import { createSlice, PayloadAction } from "@reduxjs/toolkit";

interface WalletState {
  balance: number;
}

const initialState: WalletState = {
  balance: 100000,
};

export const walletSlice = createSlice({
  name: "wallet",
  initialState,
  reducers: {
    updateBalance: (state, action: PayloadAction<number>) => {
      state.balance += action.payload;
    },
    resetWalletBalance: (state) => {
      state.balance = 100000;
    },
  },
});

export const { updateBalance, resetWalletBalance } = walletSlice.actions;
export const walletReducer = walletSlice.reducer;
