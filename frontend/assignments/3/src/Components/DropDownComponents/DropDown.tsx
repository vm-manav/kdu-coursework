import { useSelector } from "react-redux";
import { RootState } from "../../ReduxFiles/Store";
import {
  DropDownSection,
  StockLogo,
  StockName,
  StockNameSection,
} from "./DropDownStyles";

export function DropDown() {
  const stocks = useSelector((state: RootState) => state.stock.stocks);
  return (
    <DropDownSection>
      {stocks.map((stock) => {
        return (
          <StockNameSection
            to={`/stock/${stock.stock_symbol}`}
            key={stock.stock_name}
          >
            <StockLogo>{stock?.stock_symbol}</StockLogo>
            <StockName>{stock?.stock_name}</StockName>
          </StockNameSection>
        );
      })}
    </DropDownSection>
  );
}
