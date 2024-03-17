import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { IProductDetails } from "../Types/Item-type";
import { useSelector } from "react-redux";
import { RootState } from "../Redux/Store";
import "../Css-files/ItemPage.scss";

export function ItemPage() {
  const { productId } = useParams();
  const navigate = useNavigate();
  const items = useSelector((state: RootState) => state.items.items);
  const [item, setItem] = useState<IProductDetails>();

  useEffect(() => {
    setItem(items.find((product) => product.id.toString() === productId));
  }, [items, productId]);

  function backHandler() {
    navigate(-1);
  }

  return (
    <div className="item-page-container">
      <p className="item-page-product-name">{item?.title}</p>
      <div className="other-details">
        <div className="item-page-image-container">
          <img className="item-page-image" src={item?.image} alt="Product" />
        </div>
        <div className="product-other-details">
          <p className="blue-tag">
            <span className="detail-price-tag">Price: </span>
            <span className="detail-price-tag">${item?.price}</span>
          </p>
          <p>
            <span className="detail-tag">Category : </span>
            <span className="detail-value-tag">{item?.category}</span>
          </p>
          <p>
            <span className="detail-tag">Rating : </span>
            <span className="detail-value-tag">{item?.rating.rate}</span>
          </p>
          <p>
            <span className="detail-tag">Rated by : </span>
            <span className="detail-value-tag">
              {item?.rating.count} buyers
            </span>
          </p>
          <p>
            <span className="detail-tag">Description: </span>
            <span className="description-value-tag">{item?.description}</span>
          </p>

          <button onClick={backHandler} className="back-button">
            Back to Products
          </button>
        </div>
      </div>
    </div>
  );
}
