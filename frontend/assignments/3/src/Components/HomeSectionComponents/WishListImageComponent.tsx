import React, { useState } from "react";
import styled from "styled-components";
import check from "../Images/check.png";
import add from "../Images/plus.png";
import remove from "../Images/remove.png";

const WishListImage = styled.img`
  width: 28px;
`;

interface WishListImageComponentProps {
  wishlisted: boolean;
  onClick: () => void;
}

const WishListImageComponent: React.FC<WishListImageComponentProps> = ({
  wishlisted,
  onClick,
}) => {
  const [isHovered, setIsHovered] = useState(false);

  const handleMouseEnter = () => {
    setIsHovered(true);
  };

  const handleMouseLeave = () => {
    setIsHovered(false);
  };

  const getImageSrc = () => {
    if (wishlisted) {
      return isHovered ? remove : check;
    } else {
      return add;
    }
  };

  return (
    <WishListImage
      src={getImageSrc()}
      onMouseEnter={handleMouseEnter}
      onMouseLeave={handleMouseLeave}
      onClick={onClick}
      alt="Wish List Image"
    />
  );
};

export default WishListImageComponent;
