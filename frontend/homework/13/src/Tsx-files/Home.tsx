import { useContext, useEffect } from "react";
import { ItemCard } from "./ItemCard";
import { IProductDetails } from "../Types/Item-type";
import { ItemContext } from "./ItemsContext";

export default function Home() {
  const mainSectionStyles: React.CSSProperties = {
    width: "90%",
    margin: "0 auto",
    display: "flex",
    flexDirection: "column",
    gap: "40px",
    marginTop: "40px",
    fontFamily: "Poppins, sans-serif",
  };

  const mainHeadingStyles: React.CSSProperties = {
    textAlign: "center",
    fontFamily: "Poppins, sans-serif",
    fontSize: "45px",
    fontWeight: "500",
    letterSpacing: "4px",
  };

  const marketplaceStyle: React.CSSProperties = {
    color: "#2A2A72",
  };

  const itemsContainerStyles: React.CSSProperties = {
    display: "flex",
    flexWrap: "wrap",
    justifyContent: "space-between",
  };

  const { items, setItems, displayItems, setDisplayItems } =
    useContext(ItemContext);

  useEffect(() => {
    fetch("https://fakestoreapi.com/products")
      .then((response) => response.json())
      .then((data: IProductDetails[]) => {
        return setItems(data);
      });
  }, [setItems]);

  useEffect(() => {
    setDisplayItems(items);
  }, [items, setDisplayItems]);

  return (
    <div style={mainSectionStyles}>
      <p style={mainHeadingStyles}>
        KDU <span style={marketplaceStyle}>MARKETPLACE</span>
      </p>
      <div style={itemsContainerStyles}>
        {displayItems.length === 0 && <p>No Items Found</p>}
        {displayItems.length > 0 &&
          displayItems.map((item) => {
            return <ItemCard key={item.id} item={item} />;
          })}
      </div>
    </div>
  );
}
