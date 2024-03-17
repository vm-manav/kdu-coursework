import { IProductDetails } from "../Types/Item-type";
import { Link } from "react-router-dom";
import "../Css-files/ItemCard.scss";

interface IItemCardProp {
  item: IProductDetails;
}

export function ItemCard({ item }: Readonly<IItemCardProp>) {
  return (
    <Link to={`/products/${item.id}`} className="item-card-container">
      <img src={item.image} alt="mobile" className="item-image-card" />
      <div className="item-card-desc">
        <p className="product-name-card">{item.title}</p>
        <p className="product-price-card">${item.price}</p>
      </div>
      <div className="rating">
        <p className="rating-value">{item.rating.rate}</p>
      </div>
    </Link>
  );
}
