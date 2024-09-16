export interface TopLevel {
  roomTypes: RoomType[];
}

export interface RoomType {
  id: number;
  name: string;
  costPerNight: string;
  currency: Currency;
  addOns: AddOn[];
}

export interface AddOn {
  name: string;
  cost: string;
  currency: Currency;
}

export enum Currency {
  Inr = "INR",
}
