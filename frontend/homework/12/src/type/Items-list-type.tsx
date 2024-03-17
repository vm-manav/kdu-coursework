export interface IItemsObject {
  id: number;
  item: string;
}

export interface ITodoContext {
  itemElement: string;
  setItemElement: React.Dispatch<React.SetStateAction<string>>;
  items: IItemsObject[];
  setItems: React.Dispatch<React.SetStateAction<IItemsObject[]>>;
  searchValue: string;
  setSearchValue: React.Dispatch<React.SetStateAction<string>>;
  searchResult: IItemsObject[];
  setSearchResult: React.Dispatch<React.SetStateAction<IItemsObject[]>>;
}
