import {
  CheckBoxContainer,
  DateContainer,
  Details,
  FilterDateContainer,
  FilterHeaderContainer,
  FilterSearchContainer,
  FilterSection,
  FilterSectionContainer,
  FilterStockContainer,
  FilterTransactionTypeContainer,
  PortfolioMainSection,
  SearchBox,
  TimeStampAndStatus,
  TransactionItemData,
  TransactionSection,
  TransactionTypeContainer,
} from "./PortfolioStyles";
import search from "../Images/search.png";
import { DemoContainer } from "@mui/x-date-pickers/internals/demo";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { RootState } from "../../ReduxFiles/Store";
import { useSelector } from "react-redux";
import { useEffect, useState } from "react";
import {
  getUniqueStockNames,
  groupSortAndSortNewestFirst,
} from "../Utills/SortTransactions";
import { GroupedTransaction } from "../../TypeFiles/PersonalTransactionType";
import LoadingSpinner from "../Spinner/LoadingSpinner";

export function Portfolio() {
  const transactions = useSelector(
    (state: RootState) => state.transaction.transactions
  );
  const transactionsState = useSelector(
    (state: RootState) => state.transaction.state
  );
  const [sortedTransactions, setSortedTransactions] = useState<
    GroupedTransaction[]
  >([]);
  const [uniqueStockNames, setUniqueStockNames] = useState<string[]>([]);
  const [filterResults, setFilterResults] = useState<GroupedTransaction[]>([]);
  const [searchTerm, setSearchTerm] = useState<string>("");
  const [startDate, setStartDate] = useState<Date | null>(null);
  const [endDate, setEndDate] = useState<Date | null>(null);
  const [showPassed, setShowPassed] = useState(false);
  const [showFailed, setShowFailed] = useState(false);
  const [selectedStocks, setSelectedStocks] = useState<string[]>([]);
  const [loading, setLoading] = useState<boolean>(false);

  useEffect(() => {
    setLoading(true);
    const sortedData = groupSortAndSortNewestFirst(transactions);
    setSortedTransactions(sortedData);
    const uniqueSymbols = getUniqueStockNames(transactions);
    setUniqueStockNames(uniqueSymbols);
    setLoading(false);
  }, [transactions]);

  useEffect(() => {
    setLoading(true);
    const filteredResults = sortedTransactions
      .filter((group) =>
        group.transactions.some((transaction) =>
          transaction.stock_name
            .toLowerCase()
            .includes(searchTerm.toLowerCase())
        )
      )
      .filter((group) => {
        if (startDate && endDate) {
          const groupDate = new Date(group.date);
          return groupDate >= startDate && groupDate <= endDate;
        }
        return true;
      })
      .filter((group) => {
        if (!showPassed && !showFailed) {
          return false;
        }

        return group.transactions.some((transaction) => {
          if (showPassed && transaction.status === "Passed") {
            return true;
          }
          if (showFailed && transaction.status === "Failed") {
            return true;
          }
          return false;
        });
      })
      .filter((group) => {
        if (selectedStocks.length === 0) {
          return true;
        }

        return group.transactions.some((transaction) =>
          selectedStocks.includes(transaction.stock_name)
        );
      });

    setFilterResults(filteredResults);
    setLoading(false);
  }, [
    searchTerm,
    startDate,
    endDate,
    sortedTransactions,
    showPassed,
    showFailed,
    selectedStocks,
    filterResults,
  ]);

  const clearFilters = () => {
    setFilterResults([]);
    setSearchTerm("");
    setStartDate(null);
    setEndDate(null);
    setShowPassed(false);
    setShowFailed(false);
    setSelectedStocks([]);
  };

  function formatTimestampToTime(timestamp: string): string {
    const date = new Date(timestamp);
    const formattedTime = new Intl.DateTimeFormat("en-US", {
      hour: "numeric",
      minute: "numeric",
      hour12: true,
    }).format(date);

    return formattedTime;
  }

  function formatDateToCustomFormat(dateString: string): string {
    const [day, month, year] = dateString.split("/").map(Number);
    const months = [
      "Jan",
      "Feb",
      "Mar",
      "Apr",
      "May",
      "Jun",
      "Jul",
      "Aug",
      "Sep",
      "Oct",
      "Nov",
      "Dec",
    ];

    return `${day} ${months[month - 1]} ${year}`;
  }
  return (
    <>
      {transactionsState === "pending" && <LoadingSpinner />}
      {loading && <LoadingSpinner />}
      {transactionsState === "fulfilled" && !loading && (
        <PortfolioMainSection>
          <FilterSectionContainer>
            <FilterSection>
              <FilterHeaderContainer>
                <p>Filters</p>
                <button onClick={clearFilters}>Clear All</button>
              </FilterHeaderContainer>
              <FilterSearchContainer>
                <SearchBox>
                  <img src={search} alt="searchImage" />
                  <input
                    type="text"
                    placeholder="Search For a Stock"
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                  />
                </SearchBox>
              </FilterSearchContainer>
              <FilterDateContainer>
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                  <DemoContainer components={["DatePicker"]}>
                    <DatePicker
                      label="Start Date"
                      value={startDate}
                      onChange={(newValue) => setStartDate(newValue)}
                    />
                  </DemoContainer>
                </LocalizationProvider>

                <LocalizationProvider dateAdapter={AdapterDayjs}>
                  <DemoContainer components={["DatePicker"]}>
                    <DatePicker
                      label="End Date"
                      value={endDate}
                      onChange={(newValue) => setEndDate(newValue)}
                    />
                  </DemoContainer>
                </LocalizationProvider>
              </FilterDateContainer>
              <FilterTransactionTypeContainer>
                <CheckBoxContainer>
                  <input
                    type="checkbox"
                    checked={showPassed}
                    onChange={() => setShowPassed(!showPassed)}
                  />
                  <p>Passed</p>
                </CheckBoxContainer>

                <CheckBoxContainer>
                  <input
                    type="checkbox"
                    checked={showFailed}
                    onChange={() => setShowFailed(!showFailed)}
                  />
                  <p>Failed</p>
                </CheckBoxContainer>
              </FilterTransactionTypeContainer>
              <FilterStockContainer>
                {uniqueStockNames.map((stock) => {
                  return (
                    <CheckBoxContainer key={stock}>
                      <input
                        type="checkbox"
                        checked={selectedStocks.includes(stock)}
                        onChange={() => {
                          const updatedStocks = selectedStocks.includes(stock)
                            ? selectedStocks.filter(
                                (selected) => selected !== stock
                              )
                            : [...selectedStocks, stock];

                          setSelectedStocks(updatedStocks);
                        }}
                      />
                      <p>{stock}</p>
                    </CheckBoxContainer>
                  );
                })}
              </FilterStockContainer>
            </FilterSection>
          </FilterSectionContainer>
          <TransactionSection>
            {filterResults.length > 0 &&
              filterResults.map((transactionGroup) => {
                return (
                  <DateContainer key={transactionGroup.date}>
                    <p>{formatDateToCustomFormat(transactionGroup.date)}</p>
                    {transactionGroup.transactions.map((transaction) => {
                      return (
                        <TransactionItemData key={transaction.stock_name}>
                          <Details>{transaction.stock_name}</Details>
                          <Details>{transaction.stock_symbol}</Details>
                          <Details>₹{transaction.transaction_price}</Details>
                          <TimeStampAndStatus>
                            <p>
                              {formatTimestampToTime(transaction.timestamp)}
                            </p>
                            <TransactionTypeContainer
                              $type={transaction.status}
                            ></TransactionTypeContainer>
                          </TimeStampAndStatus>
                        </TransactionItemData>
                      );
                    })}
                  </DateContainer>
                );
              })}
            {filterResults.length === 0 &&
              sortedTransactions.map((transactionGroup) => {
                return (
                  <DateContainer key={transactionGroup.date}>
                    <p>{formatDateToCustomFormat(transactionGroup.date)}</p>
                    {transactionGroup.transactions.map((transaction) => {
                      return (
                        <TransactionItemData key={transaction.stock_name}>
                          <Details>{transaction.stock_name}</Details>
                          <Details>{transaction.stock_symbol}</Details>
                          <Details>₹{transaction.transaction_price}</Details>
                          <TimeStampAndStatus>
                            <p>
                              {formatTimestampToTime(transaction.timestamp)}
                            </p>
                            <TransactionTypeContainer
                              $type={transaction.status}
                            ></TransactionTypeContainer>
                          </TimeStampAndStatus>
                        </TransactionItemData>
                      );
                    })}
                  </DateContainer>
                );
              })}
          </TransactionSection>
        </PortfolioMainSection>
      )}
      {transactionsState === "error" && <p>Data not found</p>}
    </>
  );
}
