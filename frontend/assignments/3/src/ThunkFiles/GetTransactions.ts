import { createAsyncThunk } from "@reduxjs/toolkit";

export const getTransactions = createAsyncThunk("getTransactions", async () => {
  const response = await fetch(
    "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/portfolio-transactions.json"
  );
  const data = await response.json();
  return data;
});
