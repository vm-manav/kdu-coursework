import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./Tsx-files/Home";
import { ItemPage } from "./Tsx-files/ItemPage";
import { ItemContextProvider } from "./Tsx-files/ItemsContext";
import Navbar from "./Tsx-files/Navbar";

const appStyle: React.CSSProperties = {
  display: "flex",
  flexDirection: "column",
  backgroundColor: "#F3F3F3",
};

function App() {
  return (
    <div style={appStyle}>
      <ItemContextProvider>
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
      </ItemContextProvider>
    </div>
  );
}

export default App;
