import React, { useContext, useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { IProductDetails } from "../Types/Item-type";
import { ItemContext } from "./ItemsContext";

export function ItemPage() {
  const itemPageContainerStyle: React.CSSProperties = {
    display: "flex",
    flexDirection: "column",
    fontFamily: "Poppins, sans-serif",
    alignItems: "center",
    marginBottom: "115px",
  };
  const itemPageProductNameStyle: React.CSSProperties = {
    fontSize: "45px",
    fontWeight: "500",
    color: "#2A2A72",
    marginBlock: "90px",
    paddingInline: "100px",
    lineHeight: "60px",
  };
  const otherDetailsStyle: React.CSSProperties = {
    display: "flex",
  };
  const itemPageImageContainerStyle: React.CSSProperties = {
    flexBasis: "50%",
    display: "flex",
    justifyContent: "center",
  };
  const itemPageImageStyle: React.CSSProperties = {
    width: "35%",
  };
  const productOtherDetailsStyle: React.CSSProperties = {
    flexBasis: "50%",
    display: "flex",
    flexDirection: "column",
    gap: "20px",
    paddingRight: "40px",
  };

  const detailTagStyle: React.CSSProperties = {
    fontSize: "20px",
    fontWeight: "600",
  };
  const detailValueTagStyle: React.CSSProperties = {
    fontSize: "20px",
    fontWeight: "300",
    lineHeight: "25px",
  };
  const blueTagStyle: React.CSSProperties = {
    color: "#2A2A72",
  };
  const detailPriceTagStyle: React.CSSProperties = {
    fontSize: "35px",
    fontWeight: "600",
  };
  const descriptionValueTagStyle: React.CSSProperties = {
    fontSize: "16px",
    fontWeight: "200",
    lineHeight: "25px",
  };

  const backButtonStyle: React.CSSProperties = {
    color: "	#448EE4",
    fontSize: "30px",
    width: "40%",
    padding: "10px",
    marginTop: "30px",
    border: "1px solid #448EE4",
    borderRadius: "5px",
  };

  const { productId } = useParams();

  const navigate = useNavigate();

  const { items } = useContext(ItemContext);

  const [item, setItem] = useState<IProductDetails>();

  useEffect(() => {
    setItem(items.find((product) => product.id.toString() === productId));
    console.log(item);
  }, [setItem, items, item, productId]);

  function backHandler() {
    navigate(-1);
  }

  return (
    <div style={itemPageContainerStyle}>
      <p style={itemPageProductNameStyle}>{item?.title}</p>
      <div style={otherDetailsStyle}>
        <div style={itemPageImageContainerStyle}>
          <img style={itemPageImageStyle} src={item?.image} alt="Product" />
        </div>
        <div style={productOtherDetailsStyle}>
          <p style={blueTagStyle}>
            <span style={detailPriceTagStyle}>Price: </span>
            <span style={detailPriceTagStyle}>${item?.price}</span>
          </p>
          <p>
            <span style={detailTagStyle}>Category : </span>
            <span style={detailValueTagStyle}>{item?.category}</span>
          </p>
          <p>
            <span style={detailTagStyle}>Rating : </span>
            <span style={detailValueTagStyle}>{item?.rating.rate}</span>
          </p>
          <p>
            <span style={detailTagStyle}>Rated by : </span>
            <span style={detailValueTagStyle}>{item?.rating.count} buyers</span>
          </p>
          <p>
            <span style={detailTagStyle}>Description: </span>
            <span style={descriptionValueTagStyle}>{item?.description}</span>
          </p>

          <button onClick={backHandler} style={backButtonStyle}>
            Back to Products
          </button>
        </div>
      </div>
    </div>
  );
}
