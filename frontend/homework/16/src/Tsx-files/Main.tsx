import { ListItems } from "./ListItems";
import "../Css-files/Main.scss";
import { TodoContext } from "./TodoContext";
import { useContext, useEffect } from "react";
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

  function handelChangedItemElement(
    event: React.ChangeEvent<HTMLInputElement>
  ) {
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

  useEffect(() => {
    localStorage.setItem("lists", JSON.stringify(items));
  }, [items]);

  return (
    <div className="main-cont">
      <div id="item-container">
        <h2 data-testid="todo-heading" className="main-cont-heading">
          Add Items
        </h2>
        <div className="input-section">
          <input
            data-testid="todo-box"
            id="input-box"
            type="text"
            onChange={handelChangedItemElement}
            value={itemElement}
          />
          <button
            data-testid="todo-submit-button"
            className="submit-button"
            onClick={addItem}
          >
            Submit
          </button>
          <button
            data-testid="todo-clear-button"
            className="submit-button"
            onClick={clearItem}
          >
            Clear
          </button>
        </div>
      </div>
      <div id="list-container">
        <h2 data-testid="todo-items-heading" className="main-cont-heading">
          Items
        </h2>
        <ul data-testid="items-list" className="list">
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
