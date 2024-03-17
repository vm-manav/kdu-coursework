import "../Css-files/ListItem.scss";
import { IItemsObject } from "../type/Items-list-type";
import { checkItem, deleteItems, editItem } from "../redux/ItemsSlice";
import { useDispatch } from "react-redux";
import editIcon from "../images/edit.png";
import { useState } from "react";

interface IListItemsProp {
  listItem: IItemsObject;
}

export function ListItems({ listItem }: Readonly<IListItemsProp>) {
  const [editInput, setEditInput] = useState<string>("");
  const [isEditing, setIsEditing] = useState<boolean>(false);

  const reduxDispatcher = useDispatch();

  function deleteItem() {
    reduxDispatcher(deleteItems(listItem));
  }

  function markDone() {
    reduxDispatcher(checkItem(listItem.id));
  }

  function editButtonHandeler() {
    setIsEditing(true);
    setEditInput(listItem.item);
  }

  function handelEditButtonClick() {
    if (editInput.length === 0 || editInput.trim().length === 0) {
      return;
    }
    const newItem = {
      id: listItem.id,
      item: editInput,
      checked: listItem.checked,
    };
    reduxDispatcher(editItem(newItem));
    setIsEditing(false);
  }

  return (
    <>
      {isEditing && (
        <div className="edit-section">
          <input
            data-testid="edit-box"
            type="text"
            className=" edit-input"
            value={editInput}
            onChange={(e) => setEditInput(e.target.value)}
          />
          <button
            data-testid="edit-submit-button"
            className="edit-button"
            onClick={handelEditButtonClick}
          >
            Edit
          </button>
        </div>
      )}
      {!isEditing && (
        <div className="individual-list-element">
          <li className="list-item">
            <div className="input-and-checkbox-box">
              <input
                data-testid="list-element-checkbox"
                className="checkbox"
                type="checkbox"
                onClick={markDone}
              />
              <span
                className="list-item-name"
                style={{
                  textDecoration: `${listItem.checked ? "line-through" : ""}`,
                }}
              >
                {listItem.item}
              </span>
            </div>
            <div className="edit-and-delete-box">
              <img
                data-testid="list-edit-button"
                className="edit-icon"
                src={editIcon}
                alt="edit icon"
                onClick={editButtonHandeler}
              />
              <button
                data-testid="list-delete-button"
                onClick={deleteItem}
                className="delete-button"
              >
                X
              </button>
            </div>
          </li>
        </div>
      )}
    </>
  );
}
