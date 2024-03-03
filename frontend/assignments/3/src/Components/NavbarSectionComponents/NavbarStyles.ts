import { styled } from "styled-components";
import { Link } from "react-router-dom";

export const NavbarContainer = styled.div`
  display: flex;
  justify-content: space-between;
  margin: 12px;
  background-color: #1971c2;
  border: 1.5px solid black;
`;

export const NavbarLogo = styled(Link)`
  display: flex;
  text-decoration: none;
  align-items: center;
  margin-block: 15px;
  margin-left: 25px;
  gap: 15px;
  img {
    width: 39px;
  }
  p {
    color: white;
    font-family: "Poppins", sans-serif;
    font-weight: 500;
    font-size: 2.1rem;
  }
`;

export const NavbarOptions = styled.div`
  display: flex;
  gap: 20px;
  margin: 12px;
  align-items: center;
  margin-right: 25px;
`;

export const NavbarDirect = styled(Link)`
  text-decoration: none;
  color: white;
  font-family: "Poppins", sans-serif;
  font-size: 1.5rem;
`;

export const HamburgerMenu = styled.div`
  cursor: pointer;
  display: flex;
  align-items: center;
  color: white;
  font-size: 2rem;
  margin-right: 20px;
`;

export const DropdownMenu = styled.div`
  position: absolute;
  padding: 15px;
  top: 80px;
  margin-right: 12px;
  right: 0;
  background-color: #1971c2;
  border: 1.5px solid black;
  display: flex;
  flex-direction: column;
  gap: 10px;
  z-index: 1;
  ${NavbarDirect} {
    font-size: 1.3rem;
  }
`;
