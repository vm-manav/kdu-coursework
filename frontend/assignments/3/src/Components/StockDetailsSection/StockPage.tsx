import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { RootState } from "../../ReduxFiles/Store";
import { useState, useEffect, SetStateAction } from "react";
import { IStockData } from "../../TypeFiles/StockDataType";
import {
  GlobalHistoryItem,
  GlobalHistorySection,
  PriceAmount,
  PriceBarSection,
  PriceCandleStick,
  PricePercentage,
  StockBuyButton,
  StockBuySection,
  StockDetailsSection,
  StockGraphSection,
  StockHistorySection,
  StockLogo,
  StockName,
  StockNameSection,
  StockPagesection,
  StockPriceSection,
  StockQuantityInput,
  StockSellButton,
  StockTransactionSection,
  TransactionDetailsContainer,
  TransactionTimeAndQuantity,
  TransactionType,
} from "./StockPageStyles";
import downArrow from "../Images/arrow-down-sign-to-navigate.png";
import { DropDown } from "../DropDownComponents/DropDown";
import GridContainer from "../GridContainer/GridContainer";
import {
  addStockPrice,
  resetStockPrices,
} from "../../ReduxFiles/StockPriceSlice";
import { addTransaction } from "../../ReduxFiles/StockHistorySlice";
import { updateBalance } from "../../ReduxFiles/WalletSlice";
import { io } from "socket.io-client";
import {
  addGlobalTransaction,
  resetGlobalTransaction,
} from "../../ReduxFiles/GlobalTransactionSlice";
import { addToTransactions } from "../../ReduxFiles/TransactionSlice";
import {
  ITransactionData,
  Status,
} from "../../TypeFiles/PersonalTransactionType";

export function StockPage() {
  const stocks = useSelector((state: RootState) => state.stock.stocks);
  const stockPrices = useSelector(
    (state: RootState) => state.stockPrice.prices
  );
  const stockHistory = useSelector(
    (state: RootState) => state.stockHistory.transactions
  );
  const walletBalance = useSelector((state: RootState) => state.wallet.balance);
  const globalTransactions = useSelector(
    (state: RootState) => state.globalTransaction.data
  );
  const dispatch = useDispatch();

  const { stockSymbol } = useParams();
  const [stock, setStock] = useState<IStockData | undefined>();
  const [open, setOpen] = useState(false);
  const [socket, setSocket] = useState(null);
  const [basePrice, setBasePrice] = useState<number>(0);
  const [price, setPrice] = useState<number>(0);
  const [percentageChange, setPercentageChange] = useState<number>(0);
  const [userName, setUserName] = useState("");

  const handleOpen = () => {
    setOpen(!open);
  };

  useEffect(() => {
    const foundStock = stocks.find(
      (stock) => stock.stock_symbol === stockSymbol
    );
    setStock(foundStock);
    setOpen(false);
    setBasePrice(foundStock?.base_price ?? 0);
    setPrice(foundStock?.base_price ?? 0);
    dispatch(resetStockPrices());
    dispatch(resetGlobalTransaction());
  }, [stocks, stockSymbol, dispatch]);

  useEffect(() => {
    const interval = setInterval(() => {
      const randomChange = (Math.random() - 0.5) * 50;
      const newPrice = Math.max(0, Math.min(500, price + randomChange));
      const newPercentageChange =
        price !== 0 ? ((newPrice - price) / price) * 100 : 0;

      setPrice(newPrice);
      setPercentageChange(newPercentageChange);
      dispatch(
        addStockPrice({
          price: newPrice,
          isProfit: newPercentageChange >= 0,
        })
      );
    }, 5000);

    return () => clearInterval(interval);
  }, [price, stock, dispatch]);

  useEffect(() => {
    const newSocket = io("http://localhost:5001");
    newSocket.emit("join-room", stockSymbol);

    newSocket.on("user-joined", (name) => {
      setUserName(name);
    });

    newSocket.on("user-left", (message) => {
      console.log(message);
    });
    newSocket.on(
      "new-transaction",
      (transactionDetails: string, time: string) => {
        const transaction = {
          transactionDetails: transactionDetails,
          transactionTime: time,
        };
        dispatch(addGlobalTransaction(transaction));
      }
    );
    setSocket(newSocket);

    return () => {
      newSocket.disconnect();
    };
  }, [dispatch, stockSymbol]);

  const isPositiveChange = percentageChange >= 0;
  const priceColor = isPositiveChange ? "#2f9e44" : "#e03131";
  const arrowUnicode = isPositiveChange ? "\u2191" : "\u2193";

  const [quantity, setQuantity] = useState("");

  const handleQuantityChange = (e: {
    target: { value: SetStateAction<string> };
  }) => {
    setQuantity(e.target.value);
  };

  const getTotalStockQuantity = (stockName: string) => {
    return stockHistory.reduce((acc, transaction) => {
      if (transaction.stockName === stockName) {
        if (transaction.type === "Buy") {
          return acc + transaction.amount;
        } else if (transaction.type === "Sell") {
          return acc - transaction.amount;
        }
      }
      return acc;
    }, 0);
  };

  const handleBuy = () => {
    const totalCost = price * Number(quantity);
    if (
      quantity &&
      !isNaN(quantity) &&
      Number(quantity) > 0 &&
      totalCost <= walletBalance
    ) {
      dispatch(updateBalance(-totalCost));
      const transaction = {
        amount: Number(quantity),
        time: getCurrentDateTime(),
        type: "Buy",
        stockName: stock?.stock_symbol,
        price: price,
      };
      const portfolioTransactionData: ITransactionData = {
        stock_name: stock!.stock_name,
        stock_symbol: stock!.stock_symbol,
        transaction_price: totalCost,
        timestamp: new Date().toUTCString(),
        status: Status.Passed,
      };
      dispatch(addTransaction(transaction));
      dispatch(addToTransactions(portfolioTransactionData));
      setQuantity("");

      socket.emit("user-transaction", transaction, userName, stock?.stock_name);
    } else {
      const portfolioTransactionData: ITransactionData = {
        stock_name: stock!.stock_name,
        stock_symbol: stock!.stock_symbol,
        transaction_price: totalCost,
        timestamp: new Date().toUTCString(),
        status: Status.Failed,
      };
      dispatch(addToTransactions(portfolioTransactionData));
      alert("Insufficient wallet balance or invalid quantity");
    }
  };

  const handleSell = () => {
    const ownedQuantity = getTotalStockQuantity(stock?.stock_symbol);
    const totalRevenue = price * Number(quantity);
    if (
      quantity &&
      !isNaN(quantity) &&
      Number(quantity) > 0 &&
      Number(quantity) <= ownedQuantity
    ) {
      dispatch(updateBalance(totalRevenue));
      const transaction = {
        amount: Number(quantity),
        time: getCurrentDateTime(),
        type: "Sell",
        stockName: stock?.stock_symbol,
        price: price,
      };
      const portfolioTransactionData: ITransactionData = {
        stock_name: stock!.stock_name,
        stock_symbol: stock!.stock_symbol,
        transaction_price: totalRevenue,
        timestamp: new Date().toUTCString(),
        status: Status.Passed,
      };
      dispatch(addTransaction(transaction));
      dispatch(addToTransactions(portfolioTransactionData));
      setQuantity("");

      socket.emit("user-transaction", transaction, userName, stock?.stock_name);
    } else {
      const portfolioTransactionData: ITransactionData = {
        stock_name: stock!.stock_name,
        stock_symbol: stock!.stock_symbol,
        transaction_price: totalRevenue,
        timestamp: new Date().toUTCString(),
        status: Status.Passed,
      };
      dispatch(addToTransactions(portfolioTransactionData));
      alert("Selling quantity is more than owned quantity or invalid quantity");
    }
  };

  function getCurrentDateTime() {
    const currentDate = new Date();
    const daysOfWeek = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
    const dayOfWeek = daysOfWeek[currentDate.getUTCDay()];
    const monthNames = [
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
    const month = monthNames[currentDate.getUTCMonth()];
    const day = currentDate.getUTCDate();
    const year = currentDate.getUTCFullYear();
    const hours = currentDate.getUTCHours();
    const minutes = currentDate.getUTCMinutes();
    const seconds = currentDate.getUTCSeconds();
    const formattedDateTime = `${dayOfWeek}, ${day} ${month} ${year} ${hours}:${minutes}:${seconds} GMT`;
    return formattedDateTime;
  }

  return (
    <StockPagesection>
      <StockDetailsSection>
        <StockBuySection>
          <StockNameSection onClick={handleOpen}>
            <StockLogo>{stock?.stock_symbol}</StockLogo>
            <StockName>
              <p>{stock?.stock_name}</p>
              <img src={downArrow} alt="down arrow" />
            </StockName>
          </StockNameSection>
          {open && <DropDown />}
          <StockPriceSection>
            <p>Price</p>
            <PriceAmount style={{ color: priceColor }}>
              <p>{price.toFixed(2)}</p>
              <span style={{ color: priceColor }}>{arrowUnicode}</span>
            </PriceAmount>
            <PricePercentage style={{ color: priceColor }}>
              {`${percentageChange.toFixed(2)}%`}
            </PricePercentage>
          </StockPriceSection>

          <StockQuantityInput
            placeholder="Enter QTY"
            value={quantity}
            onChange={handleQuantityChange}
          />
          <StockBuyButton onClick={handleBuy}>BUY</StockBuyButton>
          <StockSellButton onClick={handleSell}>SELL</StockSellButton>
        </StockBuySection>
        <StockGraphSection>
          <GridContainer />
          <PriceBarSection>
            {stockPrices.map((price, index) => {
              return (
                <PriceCandleStick
                  key={index}
                  $price={price.price}
                  $type={price.isProfit}
                >
                  {}
                </PriceCandleStick>
              );
            })}
          </PriceBarSection>
        </StockGraphSection>
      </StockDetailsSection>
      <StockTransactionSection>
        <StockHistorySection>
          <p>History</p>
          {stockHistory.map((history, index) => {
            return (
              <TransactionDetailsContainer key={index}>
                <TransactionTimeAndQuantity>
                  <p>{history.amount} stocks</p>
                  <span>{history.time}</span>
                </TransactionTimeAndQuantity>
                <TransactionType $type={history.type}>
                  {history.type}
                </TransactionType>
              </TransactionDetailsContainer>
            );
          })}
        </StockHistorySection>
        <GlobalHistorySection>
          {globalTransactions.map((transaction) => {
            return (
              <GlobalHistoryItem key={transaction.transactionDetails}>
                <p>{transaction.transactionDetails}</p>
                <span>{transaction.transactionTime}</span>
              </GlobalHistoryItem>
            );
          })}
        </GlobalHistorySection>
      </StockTransactionSection>
    </StockPagesection>
  );
}
