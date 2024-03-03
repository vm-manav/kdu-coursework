import { styled } from "styled-components";

export const PortfolioMainSection = styled.div`
  display: flex;
  flex-wrap: wrap;
  margin-inline: 12px;
  padding-block: 12px;
  margin-top: 60px;
  @media screen and (max-width: 414px) {
    margin-inline: 2px;
    padding-block: 2px;
    margin-top: 0px;
  }
`;
export const FilterSectionContainer = styled.div`
  flex-basis: 30%;
  display: flex;
  justify-content: center;
  height: fit-content;
  position: sticky;
  top: 20px;
  @media screen and (max-width: 414px) {
    position: relative;
    justify-content: center;
  }
`;
export const FilterSection = styled.div`
  flex-basis: 70%;
  display: flex;
  flex-direction: column;
  border: 2px solid black;
  border-radius: 30px;
  background-color: #e9ecef;
  width: 500px;
  padding-bottom: 18px;
  @media screen and (max-width: 414px) {
    flex-basis: 90%;
    width: 380px;
    margin-left: 18px;
  }
`;
export const FilterHeaderContainer = styled.div`
  display: flex;
  padding-block: 12px;
  padding-inline: 15px;
  justify-content: space-between;
  align-items: center;
  border-bottom: 2px solid #9fa1a3;
  p {
    font-family: "Poppins", sans-serif;
    font-size: 25px;
  }
  button {
    background-color: transparent;
    outline: none;
    border: none;
    font-family: "Poppins", sans-serif;
    font-size: 23px;
    color: #1971c2;
  }
`;
export const FilterSearchContainer = styled.div`
  display: flex;
  justify-content: center;
  padding-block: 15px;
  border-bottom: 2px solid #9fa1a3;
`;
export const SearchBox = styled.div`
  padding-inline: 12px;
  display: flex;
  gap: 10px;
  align-items: center;
  border: 2px solid #6f7072;
  width: fit-content;
  justify-content: center;
  border-radius: 8px;
  padding-block: 5px;

  img {
    width: 22px;
    height: 22px;
  }
  input {
    background-color: transparent;
    border: none;
    outline: none;
    font-family: "Poppins", sans-serif;
    font-size: 20px;
    max-width: 300px;
  }
  @media screen and (max-width: 414px) {
    input {
      max-width: 200px;
    }
  }
`;
export const FilterDateContainer = styled.div`
  display: flex;
  padding-inline: 8px;
  gap: 8px;
  padding-block: 8px;
  padding-bottom: 15px;
  border-bottom: 2px solid #9fa1a3;
  @media screen and (max-width: 414px) {
    flex-wrap: wrap;
  }
`;
export const FilterTransactionTypeContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 12px;
  padding-block: 12px;
  border-bottom: 2px solid #9fa1a3;
`;
export const FilterStockContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 12px;
  padding-block: 12px;
  overflow-y: auto;
  max-height: 300px;
  margin-right: ;
`;

export const CheckBoxContainer = styled.div`
  display: flex;
  gap: 10px;
  padding-left: 18px;
  input {
    background-color: transparent;
    outline: none;
    border: 1px solid black;
  }
  input[type="checkbox"] {
    appearance: none;
    -webkit-appearance: none;
    -moz-appearance: none;
    width: 16px;
    height: 16px;
    border: 2px solid #9d9fa0;
    border-radius: 5px;
    background-color: transparent;
    outline: none;
    cursor: pointer;
  }
  input[type="checkbox"]:checked {
    background-color: #007bff;
  }

  p {
    font-family: "Poppins", sans-serif;
    font-size: 20px;
    color: #8f9092;
  }
`;

export const TransactionSection = styled.div`
  flex-basis: 70%;
  padding-right: 70px;
  display: flex;
  flex-direction: column;
  gap: 50px;
  width: 100%;

  @media screen and (max-width: 414px) {
    padding-right: 20px;
    margin-top: 100px;
    width: 100vw;
  }
`;

export const DateContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 20px;
  p {
    font-family: "Poppins", sans-serif;
    font-size: 20px;
    color: #969697;
    padding-bottom: 15px;
    border-bottom: 1px dashed #969697;
  }
  @media screen and (max-width: 414px) {
    width: 95vw;
    padding-left: 10px;
  }
`;

export const TransactionItemData = styled.div`
  display: flex;
  align-items: center;
  border-bottom: 1px solid #b6b7b7;
  @media screen and (max-width: 414px) {
    flex-wrap: wrap;
    width: 90vw;
  }
`;

export const Details = styled.div`
  font-family: "Poppins", sans-serif;
  width: 29%;
  color: black;
  font-size: 22px;
  padding-bottom: 20px;

  @media screen and (max-width: 414px) {
    width: 50%;
    font-size: 18px;
  }
`;

export const TimeStampAndStatus = styled.div`
  display: flex;
  gap: 15px;
  width: 12%;
  justify-content: space-between;
  p {
    color: black;
    font-size: 22px;
    padding-bottom: 20px;
    border-bottom: none;
  }

  @media screen and (max-width: 414px) {
    width: 50%;
  }
`;

export const TransactionTypeContainer = styled.div<{ $type: string }>`
  width: 16px;
  height: 16px;
  background-color: ${(props) =>
    props.$type === "Passed" ? "#6BB97A" : "#E76D6D"};
  border-radius: 50%;
`;
