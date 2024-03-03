import { useState } from "react";
import StockImage from "../Images/stock-market.png";
import {
  NavbarContainer,
  NavbarLogo,
  NavbarOptions,
  NavbarDirect,
  HamburgerMenu,
  DropdownMenu,
} from "./NavbarStyles";

export function Navbar() {
  const [isDropdownOpen, setDropdownOpen] = useState(false);

  const toggleDropdown = () => {
    setDropdownOpen(!isDropdownOpen);
  };

  return (
    <NavbarContainer>
      <NavbarLogo to="/">
        <img src={StockImage} alt="Stock Market" />
        <p>KDU Stock Market</p>
      </NavbarLogo>

      {window.innerWidth <= 414 ? (
        <HamburgerMenu onClick={toggleDropdown}>
          ☰
          {isDropdownOpen && (
            <DropdownMenu>
              <NavbarDirect to="/summarizer">Summarizer</NavbarDirect>
              <NavbarDirect to="/portfolio">My Portfolio</NavbarDirect>
            </DropdownMenu>
          )}
        </HamburgerMenu>
      ) : (
        <NavbarOptions>
          <NavbarDirect to="/summarizer">Summarizer</NavbarDirect>
          <NavbarDirect to="/portfolio">My Portfolio</NavbarDirect>
        </NavbarOptions>
      )}
    </NavbarContainer>
  );
}
