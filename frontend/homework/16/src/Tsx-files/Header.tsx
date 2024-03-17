import { useContext } from "react";
import "../Css-files/Header.scss";
import { TodoContext } from "./TodoContext";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../redux/Store";
import { filterDisplayItems } from "../redux/DisplayItemsSlice";
export function Header() {
  const { searchValue, setSearchValue } = useContext(TodoContext);

  const items = useSelector((state: RootState) => state.items.items);
  const reduxDispatcher = useDispatch();

  function getResults() {
    reduxDispatcher(filterDisplayItems({ items, searchValue }));
  }

  function changeSearchValue(event: any) {
    if (event.target.length === 0) {
      setSearchValue("");
      return;
    }
    setSearchValue(event.target.value);
    getResults();
  }

  return (
    <div id="header-container">
      <h1 data-testid="search-heading" id="header-head">
        Item Lister
      </h1>
      <input
        data-testid="search-box"
        onChange={changeSearchValue}
        value={searchValue}
        id="input-box"
        type="text"
        placeholder="Search Items..."
      />
    </div>
  );
}
