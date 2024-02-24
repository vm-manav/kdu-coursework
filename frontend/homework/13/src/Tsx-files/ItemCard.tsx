import React from "react";
import { IProductDetails } from "../Types/Item-type";
import { Link } from "react-router-dom";

interface IItemCardProp {
  item: IProductDetails;
}

export function ItemCard({ item }: Readonly<IItemCardProp>) {
  const itemCardContainerStyles: React.CSSProperties = {
    display: "flex",
    flexDirection: "column",
    flexWrap: "wrap",
    width: "23%",
    backgroundColor: "white",
    borderRadius: "10px",
    marginBottom: "30px",
    justifyContent: "space-between",
    fontFamily: "Poppins, sans-serif",
    textDecoration: "none",
  };

  const itemImageCardStyles: React.CSSProperties = {
    width: "35%",
    alignSelf: "center",
    paddingBlock: "10%",
  };

  const itemCardDescStyles: React.CSSProperties = {
    marginInline: "20px",
    marginBottom: "10px",
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between",
    gap: "15px",
  };

  const productNameCardStyles: React.CSSProperties = {
    fontSize: "15px",
    lineHeight: "20px",
    fontWeight: "400",
    color: "black",
  };

  const productPriceCardStyles: React.CSSProperties = {
    fontSize: "17px",
    lineHeight: "20px",
    fontWeight: "600",
    color: "#2A2A72",
  };

  const ratingStyle: React.CSSProperties = {
    position: "absolute",
    backgroundColor: "#2A2A72",
    borderRadius: "50%",
    width: "2%",
    height: "4%",
    display: "flex",
    justifyContent: "center",
    alignItems: "center",
  };

  const ratingValueStyle: React.CSSProperties = {
    fontFamily: "Poppins, sans-serif",
    fontSize: "15px",
    padding: "5px",
    color: "white",
  };

  return (
    <Link to={`/products/${item.id}`} style={itemCardContainerStyles}>
      <img src={item.image} alt="mobile" style={itemImageCardStyles} />
      <div style={itemCardDescStyles}>
        <p style={productNameCardStyles}>{item.title}</p>
        <p style={productPriceCardStyles}>${item.price}</p>
      </div>
      <div style={ratingStyle}>
        <p style={ratingValueStyle}>{item.rating.rate}</p>
      </div>
    </Link>
  );
}
