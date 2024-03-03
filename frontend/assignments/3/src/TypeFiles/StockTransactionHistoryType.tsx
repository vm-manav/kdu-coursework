export interface IStockTransaction {
  amount: number;
  time: string;
  type: "Buy" | "Sell";
  stockName: string;
}
