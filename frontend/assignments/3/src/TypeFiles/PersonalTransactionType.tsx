export interface ITransactionData {
  stock_name: string;
  stock_symbol: string;
  transaction_price: number;
  timestamp: string;
  status: Status;
}

export enum Status {
  Failed = "Failed",
  Passed = "Passed",
}
export interface GroupedTransaction {
  date: string;
  transactions: ITransactionData[];
}
