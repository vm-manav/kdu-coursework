import styled from "styled-components";

export const StockPagesection = styled.div`
  display: flex;
  margin: 35px;
  gap: 30px;
  font-family: "Poppins, sans-serif";
  width: 98vw;
  box-sizing: border-box;
`;

export const StockDetailsSection = styled.div`
  flex-basis: 75%;
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 35px;
`;

export const StockBuySection = styled.div`
  display: flex;
  gap: 22px;
  justify-content: space-between;
`;

export const StockNameSection = styled.button`
  background-color: transparent;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
  gap: 10px;
  font-family: "Poppins", sans-serif;
  border: 1px solid black;
  min-width: 320px;
  cursor: pointer;
`;
export const StockLogo = styled.p`
  font-size: 22px;
  font-weight: 600;
  background-color: #ffec99;
  color: #f18f05;
  padding-inline: 15px;
  padding-block: 8px;
  border: 2px solid #f5ad35;
`;

export const StockName = styled.div`
  line-height: 30px;
  display: flex;
  gap: 10px;
  align-items: center;
  p {
    font-size: 27px;
    font-weight: 400;
  }
  img {
    align-self: center;
    width: 20px;
  }
`;

export const StockPriceSection = styled.div`
  display: flex;
  gap: 20px;
  padding: 10px;
  width: 350px;
  justify-content: center;
  align-items: center;
  font-family: "Poppins", sans-serif;
  border: 1px solid black;
  p {
    font-size: 28px;
    font-weight: 400;
  }
`;

export const PriceAmount = styled.div`
  display: flex;
  gap: 8px;
  width: 150px;
  text-align: center;
  p {
    width: 100px;
    align-self: center;
    font-size: 25px;
    font-weight: 400;
  }
  span {
    padding-bottom: 5px;
    font-weight: 900;
    font-size: 42px;
  }
`;
export const PricePercentage = styled.div`
  text-align: center;
  width: 80px;
  font-size: 18px;
  padding-top: 10px;
`;
export const StockQuantityInput = styled.input`
  font-family: "Poppins", sans-serif;
  font-size: 18px;
  color: black;
  width: 280px;
  text-align: center;
  background-color: transparent;
  outline: none;
  border: 1px solid black;
  &::placeholder {
    color: black;
    opacity: 1;
  }
`;

export const StockBuyButton = styled.button`
  font-family: "Poppins", sans-serif;
  font-size: 20px;
  font-weight: 400;
  background-color: #b2f2bb;
  color: #2f9e44;
  border: 1px solid #50ae62;
  padding-inline: 18px;
`;

export const StockSellButton = styled.button`
  font-family: "Poppins", sans-serif;
  font-size: 20px;
  font-weight: 400;
  background-color: #ffc9c9;
  color: #e03131;
  border: 1px solid #e54b4b;
  padding-inline: 18px;
`;

export const StockGraphSection = styled.div`
  height: 500px;
  position: relative;
`;

export const StockTransactionSection = styled.div`
  flex-basis: 21.5%;
  display: flex;
  flex-direction: column;
  gap: 30px;
  height: 84vh;
`;

export const StockHistorySection = styled.div`
  border: 1px solid black;
  flex-basis: 65%;
  display: flex;
  flex-direction: column;
  padding-bottom: 20px;
  gap: 15px;
  overflow-y: auto;
  max-height: 84vh;
  p {
    font-family: "Poppins", sans-serif;
    font-size: 28px;
    font-weight: 500;
    padding-inline: 18px;
    margin-top: 18px;
  }
`;
export const TransactionDetailsContainer = styled.div`
  border: 1px solid black;
  margin-inline: 18px;
  border-radius: 15px;
  display: flex;
  align-items: center;
  gap: 10px;
`;
export const TransactionTimeAndQuantity = styled.div`
  flex-grow: 4;
  display: flex;
  flex-direction: column;
  gap: 12px;
  padding-block: 10px;
  p {
    font-family: "Poppins", sans-serif;
    font-size: 24px;
    font-weight: 400;
    padding-inline: 12px;
    margin-top: 0px;
  }
  span {
    font-family: "Poppins", sans-serif;
    font-size: 17px;
    font-weight: 400;
    padding-inline: 12px;
  }
`;
export const TransactionType = styled.div<{ $type: string }>`
  flex-grow: 1;
  font-family: "Poppins", sans-serif;
  font-size: 23px;
  font-weight: 400;
  color: ${(props) => (props.$type === "Buy" ? "#2F9E44" : "#E03131")};
`;

export const GlobalHistorySection = styled.div`
  border: 1px solid black;
  flex-basis: 35%;
  display: flex;
  flex-direction: column;
  gap: 25px;
  padding-block: 15px;
  overflow-y: auto;
  max-height: 300px;
`;
export const GlobalHistoryItem = styled.div`
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-inline: 15px;
  p {
    font-family: "Poppins", sans-serif;
    font-size: 22px;
    font-weight: 400;
  }
  span {
    font-family: "Poppins", sans-serif;
    font-weight: 300;
    font-size: 18px;
  }
`;

export const PriceBarSection = styled.div`
  position: absolute;
  top: 0;
  width: 100%;
  height: 700px;
  background-color: transparent;
  display: flex;
  gap: 1px;
  align-items: end;
`;

export const PriceCandleStick = styled.div<{ $price: number; $type: boolean }>`
  width: 46px;
  height: ${(props) => props.$price * 1.4}px;
  border: 1px solid ${(props) => (props.$type ? "#2F9E44" : "#E03131")};
  background-color: ${(props) => (props.$type ? "#B2F2BB" : "#FFC9C9")};
`;
