import { createContext, useState } from "react";
import { IProductDetails, IContextData } from "../Types/Item-type";

export const ItemContext = createContext<IContextData>({
  items: [],
  setItems: () => {},
  displayItems: [],
  setDisplayItems: () => {},
});

export function ItemContextProvider({
  children,
}: Readonly<{ children: React.ReactNode }>) {
  const [items, setItems] = useState<IProductDetails[]>([]);
  const [displayItems, setDisplayItems] = useState<IProductDetails[]>([]);

  return (
    <ItemContext.Provider
      value={{ items, setItems, displayItems, setDisplayItems }}
    >
      {children}
    </ItemContext.Provider>
  );
}
