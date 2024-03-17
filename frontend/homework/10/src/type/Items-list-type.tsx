export interface IItemsObject{
    id : number;
    item : string;
}

export interface IItemsState{
    items : IItemsObject[];
    setItems : React.Dispatch<React.SetStateAction<IItemsObject[]>>;
}

export interface IListItemState{
    listItem : IItemsObject;
    items : IItemsObject[];
    setItems : React.Dispatch<React.SetStateAction<IItemsObject[]>>;
}

export interface IAllStateData{
    items : IItemsObject[];
    setItems : React.Dispatch<React.SetStateAction<IItemsObject[]>>;
    searchValue : string;
    setSearchValue: React.Dispatch<React.SetStateAction<string>>;
    searchResult: IItemsObject[];
    setSearchResult: React.Dispatch<React.SetStateAction<IItemsObject[]>>;
}