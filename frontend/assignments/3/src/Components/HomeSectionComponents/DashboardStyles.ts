import { Link } from "react-router-dom";
import { styled } from "styled-components";

export const DasboardSection = styled.div`
  margin-inline: 12px;
  display: flex;
  flex-direction: column;
  gap: 50px;

  @media only screen and (max-width: 414px) {
    margin-inline: 6px;
    gap: 20px;
    margin-bottom: 20px;
  }
`;

export const TabSection = styled.div``;

export const TableSection = styled.div`
  align-self: center;
  width: 75%;

  @media only screen and (max-width: 414px) {
    width: 92%;
  }
`;

export const LinkTag = styled(Link)`
  text-decoration: none;
  color: black;
  &:hover {
    cursor: pointer;
  }
`;
