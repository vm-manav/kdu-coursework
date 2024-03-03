import { Link } from "react-router-dom";
import styled from "styled-components";

export const DropDownSection = styled.div`
  position: absolute;
  top: 215px;
  max-height: 30vh;
  display: flex;
  flex-direction: column;
  border-radius: 15px;
  width: 320px;
  overflow-x: auto;
  scrollbar-width: none;
  background-color: #e9ecef;
  border: 2px solid black;
  z-index: 2000;
`;

export const StockNameSection = styled(Link)`
  text-decoration: none;
  color: black;
  display: flex;
  align-items: center;
  padding: 15px;
  gap: 10px;
  font-family: "Poppins", sans-serif;
  border-bottom: 1px solid black;
  &:hover {
    background-color: #d1d4d7;
  }
`;

export const StockLogo = styled.p`
  font-size: 18px;
  font-weight: 600;
  background-color: #ffec99;
  color: #f18f05;
  padding: 10px;
  border: 2px solid #f5ad35;
`;

export const StockName = styled.p`
  font-size: 22px;
  font-weight: 400;
`;
