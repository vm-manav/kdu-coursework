import { createAsyncThunk } from "@reduxjs/toolkit";

export const getRoom = createAsyncThunk("getRoom", async () => {
  const response = await fetch(
    "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/assessment-3.json"
  );
  const data = await response.json();
  return Object.values(data)[0];
});
