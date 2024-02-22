import React, { createContext, useState } from "react";
import { IItemsObject, ITodoContext } from "../type/Items-list-type";

interface ITodoProvider {
  children: React.ReactNode;
}

export const TodoContext = createContext<ITodoContext>({
  itemElement: "",
  setItemElement: () => {},
  items: [],
  setItems: () => {},
  searchValue: "",
  setSearchValue: () => {},
  searchResult: [],
  setSearchResult: () => {},
});

export function TodoProvider({ children }: Readonly<ITodoProvider>) {
  const [itemElement, setItemElement] = useState<string>("");
  const [items, setItems] = useState<IItemsObject[]>([]);
  const [searchValue, setSearchValue] = useState<string>("");
  const [searchResult, setSearchResult] = useState<IItemsObject[]>([]);

  return (
    <TodoContext.Provider
      value={{
        itemElement,
        setItemElement,
        items,
        setItems,
        searchValue,
        setSearchValue,
        searchResult,
        setSearchResult,
      }}
    >
      {children}
    </TodoContext.Provider>
  );
}
