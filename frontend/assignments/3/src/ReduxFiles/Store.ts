import { configureStore } from "@reduxjs/toolkit";
import { stockReducer } from "./StockSlice";
import { wishListReducer } from "./WishList";
import { transactionReducer } from "./TransactionSlice";
import { stockPriceReducer } from "./StockPriceSlice";
import { stockHistoryReducer } from "./StockHistorySlice";
import { walletReducer } from "./WalletSlice";
import { globalTransactionReducer } from "./GlobalTransactionSlice";

export const store = configureStore({
  reducer: {
    stock: stockReducer,
    wishList: wishListReducer,
    transaction: transactionReducer,
    stockPrice: stockPriceReducer,
    stockHistory: stockHistoryReducer,
    wallet: walletReducer,
    globalTransaction: globalTransactionReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
