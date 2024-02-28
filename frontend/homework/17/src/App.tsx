import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./Tsx-files/Home";
import { ItemPage } from "./Tsx-files/ItemPage";
import Navbar from "./Tsx-files/Navbar";
import { useEffect } from "react";
import { getProduct } from "./Thrunk/GetProduct";
import { useDispatch } from "react-redux";

const appStyle: React.CSSProperties = {
  margin: "0",
  padding: "0",
  display: "flex",
  flexDirection: "column",
  backgroundColor: "#F3F3F3",
};

function App() {
  const reduxDispatcher = useDispatch();

  reduxDispatcher;
  useEffect(() => {
    reduxDispatcher(getProduct());
  }, [reduxDispatcher]);

  return (
    <div style={appStyle}>
      <BrowserRouter>
        <Routes>
          <Route
            path="/"
            element={
              <>
                <Navbar />
                <Home />
              </>
            }
          ></Route>
          <Route
            path="/products/:productId"
            element={
              <>
                <Navbar />
                <ItemPage />
              </>
            }
          ></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
