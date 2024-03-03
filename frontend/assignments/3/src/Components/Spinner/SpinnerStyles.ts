import styled, { keyframes } from "styled-components";

export const rotate = keyframes`
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
`;

export const SpinnerContainer = styled.div`
  position: fixed;
  height: 100vh;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: flex;
  align-items: center;
  justify-content: center;
`;

export const Spinner = styled.div`
  width: 150px;
  height: 150px;
  border: 8px solid #ccc;
  border-top: 8px solid #3498db;
  border-radius: 50%;
  animation: ${rotate} 1s linear infinite;
`;
