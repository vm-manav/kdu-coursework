import { useEffect, useRef } from "react";
import searchlogo from "../image/search.png";
import { useParams, useSearchParams } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../Redux/Store";
import { setDisplayItems } from "../Redux/DisplayItems";
import "../Css-files/Navbar.scss";

export default function Navbar() {
  const reduxDispatcher = useDispatch();
  const items = useSelector((state: RootState) => state.items.items);
  const displayItems = useSelector(
    (state: RootState) => state.displayItems.displayItems
  );

  const searchInput = useRef<string>("");
  const filterSelect = useRef<HTMLSelectElement>(null);
  const sortSelect = useRef<HTMLSelectElement>(null);
  const [searchParm, setSearchParm] = useSearchParams();
  const { productId } = useParams();

  function handelSearchIconClick() {
    const searchTerm = searchInput.current.trim().toLowerCase();
    if (searchTerm.length === 0) {
      reduxDispatcher(setDisplayItems(items));
    }

    reduxDispatcher(
      setDisplayItems(
        items.filter((item) => item.title.toLowerCase().includes(searchTerm))
      )
    );
  }

  function handleFilterSelectChange() {
    const selectedFilter = filterSelect.current?.value;
    if (!selectedFilter) {
      return;
    }

    reduxDispatcher(
      setDisplayItems(
        items.filter(
          (item) => item.category.toLowerCase() === selectedFilter.toLowerCase()
        )
      )
    );
  }

  function handleSortSelectChange() {
    const selectedSort = sortSelect.current?.value;
    if (!selectedSort) {
      return;
    }

    if (selectedSort === "increasing") {
      reduxDispatcher(
        setDisplayItems([...displayItems].sort((a, b) => a.price - b.price))
      );
    } else if (selectedSort === "decreasing") {
      reduxDispatcher(
        setDisplayItems([...displayItems].sort((a, b) => b.price - a.price))
      );
    }
  }

  useEffect(() => {
    if (
      searchParm.get("filter") === "electronics" ||
      searchParm.get("filter") === "jewelery" ||
      searchParm.get("filter") === "mens" ||
      searchParm.get("filter") === "womens"
    ) {
      filterSelect.current.value = searchParm.get("filter");
      handleFilterSelectChange();
    }
  });

  return (
    <div className="navbar-section">
      <div className="search-input-container">
        <input
          onChange={(e) => (searchInput.current = e.target.value)}
          className="search-input-box"
          type="text"
          name="search-input"
          placeholder="Search .."
          disabled={!items.length}
        />
        <button onClick={handelSearchIconClick} className="search-button">
          <img className="search-image" src={searchlogo} alt="search" />
        </button>
      </div>
      {!productId && (
        <div className="option-container">
          <div className="filter-and-brand-container">
            <p className="option-tag">Filter :</p>
            <select
              ref={filterSelect}
              onChange={handleFilterSelectChange}
              name="filter"
              className="drop-down"
              disabled={!items.length}
            >
              <option value="" disabled selected>
                Select Category
              </option>
              <option value="jewelery">Jewelery</option>
              <option value="men's clothing">Men's</option>
              <option value="electronics">Electronics</option>
              <option value="women's clothing">Women's</option>
            </select>
          </div>
          <div className="filter-and-brand-container">
            <p className="option-tag">Sort :</p>
            <select
              ref={sortSelect}
              onChange={handleSortSelectChange}
              name="sort"
              className="drop-down"
              disabled={!items.length}
            >
              <option value="" disabled selected>
                Price
              </option>
              <option value="increasing">Increasing</option>
              <option value="decreasing">Decreasing</option>
            </select>
          </div>
        </div>
      )}
    </div>
  );
}
