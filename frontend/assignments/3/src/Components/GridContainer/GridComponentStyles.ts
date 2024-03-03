import styled from "styled-components";

export const Container = styled.div`
  border: 1px solid black;
  display: grid;
  grid-template-columns: repeat(6, 235px);
  grid-template-rows: repeat(4, 175px);
`;

export const Cell = styled.div`
  width: 100%;
  height: 100%;
  border: 1px dashed #a1a1a2;
  box-sizing: border-box;
`;
