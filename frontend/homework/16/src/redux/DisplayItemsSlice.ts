import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { IItemsObject } from "../type/Items-list-type";

interface ItemsState {
  displayItems: IItemsObject[];
}

interface IPayloadPropData {
  items: IItemsObject[];
  searchValue: string;
}
const initialState: ItemsState = {
  displayItems: [],
};
const displayItemsSlice = createSlice({
  name: "displayItems",
  initialState: initialState,
  reducers: {
    filterDisplayItems: (state, action: PayloadAction<IPayloadPropData>) => {
      const lowerCaseSearchValue = action.payload.searchValue.toLowerCase();

      return {
        displayItems: action.payload.items.filter((itemelement) => {
          const lowerCaseItem = itemelement.item.toLowerCase();
          return lowerCaseItem.includes(lowerCaseSearchValue);
        }),
      };
    },
  },
});
export const { filterDisplayItems } = displayItemsSlice.actions;
export const displayItemsReducer = displayItemsSlice.reducer;
