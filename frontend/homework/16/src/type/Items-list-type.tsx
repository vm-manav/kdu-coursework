export interface IItemsObject {
  id: number;
  item: string;
  checked: boolean;
}

export interface ITodoContext {
  itemElement: string;
  setItemElement: React.Dispatch<React.SetStateAction<string>>;
  searchValue: string;
  setSearchValue: React.Dispatch<React.SetStateAction<string>>;
}
