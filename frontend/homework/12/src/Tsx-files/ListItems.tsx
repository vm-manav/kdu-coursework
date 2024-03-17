import { useContext } from "react";
import "../Css-files/ListItem.scss";
import { IItemsObject } from "../type/Items-list-type";
import { TodoContext } from "./TodoContext";

interface IListItemsProp {
  listItem: IItemsObject;
}

export function ListItems({ listItem }: Readonly<IListItemsProp>) {
  const todoContextValue = useContext(TodoContext);

  function deleteItem() {
    todoContextValue.setItems(
      todoContextValue.items.filter(
        (item: IItemsObject) => item.id !== listItem.id
      )
    );
  }

  return (
    <div className="individual-list-element">
      <li className="list-item">
        <span className="list-item-name">{listItem.item}</span>
        <button onClick={deleteItem} className="delete-button">
          X
        </button>
      </li>
    </div>
  );
}
