import { configureStore } from "@reduxjs/toolkit";
import { itemsReducer } from "./ItemsSlice";
import { displayItemsReducer } from "./DisplayItems";

export const store = configureStore({
  reducer: {
    items: itemsReducer,
    displayItems: displayItemsReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
