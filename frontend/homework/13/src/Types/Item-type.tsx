export interface IProductDetails {
  id: number;
  title: string;
  price: number;
  description: string;
  category: string;
  image: string;
  rating: IRating;
}

export interface IRating {
  rate: number;
  count: number;
}

export interface IContextData {
  items: IProductDetails[];
  setItems: React.Dispatch<React.SetStateAction<IProductDetails[]>>;
  displayItems: IProductDetails[];
  setDisplayItems: React.Dispatch<React.SetStateAction<IProductDetails[]>>;
}
