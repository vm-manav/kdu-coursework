import React, { createContext, useState } from "react";
import { ITodoContext } from "../type/Items-list-type";

interface ITodoProvider {
  children: React.ReactNode;
}

export const TodoContext = createContext<ITodoContext>({
  itemElement: "",
  setItemElement: () => {},
  searchValue: "",
  setSearchValue: () => {},
});

export function TodoProvider({ children }: Readonly<ITodoProvider>) {
  const [itemElement, setItemElement] = useState<string>("");
  const [searchValue, setSearchValue] = useState<string>("");

  return (
    <TodoContext.Provider
      value={{
        itemElement,
        setItemElement,
        searchValue,
        setSearchValue,
      }}
    >
      {children}
    </TodoContext.Provider>
  );
}
