import {
  GroupedTransaction,
  ITransactionData,
} from "../../TypeFiles/PersonalTransactionType";

export function groupSortAndSortNewestFirst(
  transactions: ITransactionData[]
): GroupedTransaction[] {
  const convertTimestampToDate = (timestamp: string): Date =>
    new Date(timestamp);

  const transactionsWithDates = transactions.map((transaction) => ({
    ...transaction,
    date: convertTimestampToDate(transaction.timestamp),
  }));

  const groupedTransactions: Record<string, ITransactionData[]> = {};
  transactionsWithDates.forEach((transaction) => {
    const dateString = transaction.date.toLocaleDateString();
    if (!groupedTransactions[dateString]) {
      groupedTransactions[dateString] = [];
    }
    groupedTransactions[dateString].push(transaction);
  });

  Object.values(groupedTransactions).forEach((group) => {
    group.sort((a, b) => b.date.getTime() - a.date.getTime());
  });

  const result: GroupedTransaction[] = Object.entries(groupedTransactions).map(
    ([date, transactions]) => ({
      date,
      transactions,
    })
  );
  result.sort(
    (a, b) =>
      b.transactions[0].date.getTime() - a.transactions[0].date.getTime()
  );
  return result;
}

export function getUniqueStockNames(
  transactions: ITransactionData[]
): string[] {
  const uniqueStockNames: string[] = [];

  transactions.forEach((transaction) => {
    if (!uniqueStockNames.includes(transaction.stock_name)) {
      uniqueStockNames.push(transaction.stock_name);
    }
  });

  return uniqueStockNames;
}
