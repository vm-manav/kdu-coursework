import { styled } from "styled-components";

export const HotelPageSection = styled.div`
  display: flex;
  flex-direction: column;
  font-family: "Rubik", sans-serif;
  gap: 50px;
  margin-bottom: 50px;
`;
export const HotelHeadingSection = styled.p`
  align-self: center;
  margin-top: 40px;
  font-size: 30px;
`;

export const OptionTypeWrapper = styled.div`
  display: flex;
  flex-direction: column;
  margin-inline: 50px;
  gap: 15px;
  p {
    font-size: 22px;
    font-weight: 500;
    color: white;
    background-color: #f08a52;
    padding: 15px;
  }
`;
export const TypeContainer = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
`;

export const TypeCard = styled.div`
  font-size: 18px;
  font-weight: 400;
  border: 1px solid black;
  padding-inline: 40px;
  padding-block: 20px;
  &:hover {
    cursor: pointer;
  }
  &:active {
    color: grey;
  }
`;

export const CostSection = styled.div`
  display: flex;
  flex-direction: column;
  margin-inline: 50px;
  margin-top: 30px;
  font-weight: 400;
  gap: 30px;
  p {
    font-size: 30px;
  }
  button {
    width: 200px;
    color: white;
    background-color: #f08a52;
    font-weight: 600;
    font-size: 30px;
    padding-block: 10px;
    padding-inline: 30px;
    outline: none;
    border: none;
  }
`;
