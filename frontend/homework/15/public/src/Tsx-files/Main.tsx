import { ListItems } from "./ListItems";
import "../Css-files/Main.scss";
import { TodoContext } from "./TodoContext";
import { useContext } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../redux/Store";
import { addItems, deleteCheckedItems } from "../redux/ItemsSlice";

export function Main() {
  const { itemElement, setItemElement, searchValue } = useContext(TodoContext);

  const items = useSelector((state: RootState) => state.items.items);
  const displayItems = useSelector(
    (state: RootState) => state.displayItems.displayItems
  );
  const reduxDispatcher = useDispatch();

  function handelChangedItemElement(event: any) {
    setItemElement(event.target.value);
  }
  function addItem() {
    if (itemElement.trim().length === 0 || itemElement.length === 0) {
      console.log("empty item can not be added");
      return;
    }

    const newItem = {
      id: items.length === 0 ? 1 : items.length + 1,
      item: itemElement,
      checked: false,
    };
    reduxDispatcher(addItems(newItem));
    setItemElement("");
  }

  function clearItem() {
    reduxDispatcher(deleteCheckedItems());
  }

  return (
    <div className="main-cont">
      <div id="item-container">
        <h2 className="main-cont-heading">Add Items</h2>
        <div className="input-section">
          <input
            id="input-box"
            type="text"
            onChange={handelChangedItemElement}
            value={itemElement}
          />
          <button className="submit-button" onClick={addItem}>
            Submit
          </button>
          <button className="submit-button" onClick={clearItem}>
            Clear
          </button>
        </div>
      </div>
      <div id="list-container">
        <h2 className="main-cont-heading">Items</h2>
        <ul className="list">
          {displayItems.length > 0 &&
            searchValue.length > 0 &&
            displayItems.map((item) => {
              return <ListItems key={item.id} listItem={item} />;
            })}
          {searchValue.length > 0 && displayItems.length === 0 && (
            <p>No Match found</p>
          )}
          {searchValue.length === 0 &&
            items.map((item) => {
              return <ListItems key={item.id} listItem={item} />;
            })}
        </ul>
      </div>
    </div>
  );
}
