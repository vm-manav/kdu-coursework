import { configureStore } from "@reduxjs/toolkit";
import { displayItemsReducer } from "./DisplayItemsSlice";
import { itemsReducer } from "./ItemsSlice";

export const store = configureStore({
  reducer: {
    displayItems: displayItemsReducer,
    items: itemsReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
