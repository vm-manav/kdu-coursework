import { createSlice } from "@reduxjs/toolkit";
import { getRoom } from "../Thunk/GetRoomType";
import { RoomType } from "../Type/RoomType";

interface ItemsState {
  room: RoomType[];
  state: "pending" | "fulfilled" | "error";
  error?: string;
}

const initialState: ItemsState = {
  room: [],
  state: "pending",
};

const roomSlice = createSlice({
  name: "room",
  initialState: initialState,
  reducers: {},

  extraReducers(builder) {
    builder
      .addCase(getRoom.pending, (state) => {
        state.state = "pending";
      })
      .addCase(getRoom.fulfilled, (state, action) => {
        state.room = action.payload;
        state.state = "fulfilled";
      })
      .addCase(getRoom.rejected, (state, action) => {
        state.error = action.payload as string;
        state.state = "error";
      });
  },
});
export const roomReducer = roomSlice.reducer;
