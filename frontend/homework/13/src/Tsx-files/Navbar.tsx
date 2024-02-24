import React, { useContext, useEffect, useRef } from "react";
import searchlogo from "../image/search.png";
import { ItemContext } from "./ItemsContext";
import { useParams, useSearchParams } from "react-router-dom";

export default function Navbar() {
  const navbarSectionStyles: React.CSSProperties = {
    display: "flex",
    justifyContent: "space-between",
    fontFamily: "Poppins, sans-serif",
    backgroundColor: "#2A2A72",
    padding: "8px",
  };

  const searchInputContainerStyles: React.CSSProperties = {
    marginLeft: "5%",
    marginTop: "6px",
    display: "flex",
    fontFamily: "Poppins, sans-serif",
  };

  const searchInputBoxStyle: React.CSSProperties = {
    outline: "none",
    fontSize: "12px",
    paddingLeft: "5px",
    height: "70%",
    border: "1px solid gray",
    fontWeight: "600",
  };

  const searchImageStyle: React.CSSProperties = {
    height: "80%",
  };
  const optionContainerStyles: React.CSSProperties = {
    marginRight: "5%",
    display: "flex",
    gap: "20px",
    fontFamily: "Poppins, sans-serif",
  };

  const filterAndBrandContainerStyles: React.CSSProperties = {
    display: "flex",
    gap: "10px",
    fontFamily: "Poppins, sans-serif",
    alignSelf: "center",
    fontWeight: "500",
  };

  const optionTagStyle: React.CSSProperties = {
    color: "white",
  };
  const dropDownStyle: React.CSSProperties = {
    outline: "none",
    fontFamily: "Poppins, sans-serif",
    fontSize: "12px",
    fontWeight: "500",
  };

  const searchButtonStyle: React.CSSProperties = {
    height: "80%",
  };

  const { items, displayItems, setDisplayItems } = useContext(ItemContext);

  const searchInput = useRef<string>("");
  const filterSelect = useRef<HTMLSelectElement>(null);
  const sortSelect = useRef<HTMLSelectElement>(null);

  function handelSearchIconClick() {
    const searchTerm = searchInput.current.trim().toLowerCase();
    if (searchTerm.length === 0) {
      console.log("Empty search parameter");
      setDisplayItems(items);
    }

    setDisplayItems(
      items.filter((item) => item.title.toLowerCase().includes(searchTerm))
    );
  }

  function handleFilterSelectChange() {
    const selectedFilter = filterSelect.current?.value;
    if (!selectedFilter) {
      console.log("Empty filter parameter");
      return;
    }

    setDisplayItems(
      items.filter(
        (item) => item.category.toLowerCase() === selectedFilter.toLowerCase()
      )
    );
  }

  function handleSortSelectChange() {
    const selectedSort = sortSelect.current?.value;
    if (!selectedSort) {
      console.log("Empty sort parameter");
      return;
    }

    if (selectedSort === "increasing") {
      setDisplayItems([...displayItems].sort((a, b) => a.price - b.price));
    } else if (selectedSort === "decreasing") {
      setDisplayItems([...displayItems].sort((a, b) => b.price - a.price));
    }
  }

  const [searchParm, setSearchParm] = useSearchParams();
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

  const { productId } = useParams();

  return (
    <div style={navbarSectionStyles}>
      <div style={searchInputContainerStyles}>
        <input
          onChange={(e) => (searchInput.current = e.target.value)}
          style={searchInputBoxStyle}
          type="text"
          name="search-input"
          placeholder="Search .."
        />
        <button onClick={handelSearchIconClick} style={searchButtonStyle}>
          <img style={searchImageStyle} src={searchlogo} alt="search" />
        </button>
      </div>
      {!productId && (
        <div style={optionContainerStyles}>
          <div style={filterAndBrandContainerStyles}>
            <p style={optionTagStyle}>Filter :</p>
            <select
              ref={filterSelect}
              onChange={handleFilterSelectChange}
              name="filter"
              style={dropDownStyle}
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
          <div style={filterAndBrandContainerStyles}>
            <p style={optionTagStyle}>Sort :</p>
            <select
              ref={sortSelect}
              onChange={handleSortSelectChange}
              name="sort"
              style={dropDownStyle}
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
