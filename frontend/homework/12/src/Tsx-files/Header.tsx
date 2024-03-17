import { useContext } from "react";
import "../Css-files/Header.scss";
import { TodoContext } from "./TodoContext";
export function Header() {
  const { items, searchValue, setSearchValue, setSearchResult } =
    useContext(TodoContext);

  function getResults() {
    const lowerCaseSearchValue = searchValue.toLowerCase();
    setSearchResult(
      items.filter((itemelement) => {
        const lowerCaseItem = itemelement.item.toLowerCase();
        return lowerCaseItem.includes(lowerCaseSearchValue);
      })
    );
  }

  function changeSearchValue(event: any) {
    if (event.target.length === 0) {
      setSearchResult([]);
      setSearchValue("");
      return;
    }
    setSearchValue(event.target.value);
    getResults();
  }

  return (
    <div id="header-container">
      <h1 id="header-head">Item Lister</h1>
      <input
        onChange={changeSearchValue}
        value={searchValue}
        id="input-box"
        type="text"
        placeholder="Search Items..."
      />
    </div>
  );
}
