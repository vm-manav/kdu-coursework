import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Navbar } from "./Components/NavbarSectionComponents/Navbar";
import { Dashboard } from "./Components/HomeSectionComponents/Dashboard";
import { StockPage } from "./Components/StockDetailsSection/StockPage";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { getStocks } from "./ThunkFiles/GetStocks";
import { Portfolio } from "./Components/PortfolioSection/Portfolio";
import { getTransactions } from "./ThunkFiles/GetTransactions";

function App() {
  const reduxDispatcher = useDispatch();
  useEffect(() => {
    reduxDispatcher(getStocks());
    reduxDispatcher(getTransactions());
  }, [reduxDispatcher]);

  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/"
          element={
            <>
              <Navbar />
              <Dashboard />
            </>
          }
        />
        <Route
          path="/stock/:stockSymbol"
          element={
            <>
              <Navbar />
              <StockPage />
            </>
          }
        />
        <Route
          path="/portfolio"
          element={
            <>
              <Navbar />
              <Portfolio />
            </>
          }
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
