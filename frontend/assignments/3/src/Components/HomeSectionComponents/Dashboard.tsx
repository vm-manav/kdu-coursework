import {
  Pagination,
  Paper,
  Stack,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Tabs,
} from "@mui/material";
import Tab from "@mui/material/Tab";
import { useState } from "react";
import {
  DasboardSection,
  LinkTag,
  TabSection,
  TableSection,
} from "./DashboardStyles";
import { RootState } from "../../ReduxFiles/Store";
import { useDispatch, useSelector } from "react-redux";
import { IStockData } from "../../TypeFiles/StockDataType";
import { addItem, removeItem } from "../../ReduxFiles/WishList";
import {
  markAsNotWishlisted,
  markAsWishlisted,
} from "../../ReduxFiles/StockSlice";
import WishListImageComponent from "./WishListImageComponent";
import LoadingSpinner from "../Spinner/LoadingSpinner";

export function Dashboard() {
  const [value, setValue] = useState("1");
  const [page, setPage] = useState(1);

  const stocks = useSelector((state: RootState) => state.stock.stocks);
  const wishListStocks = useSelector(
    (state: RootState) => state.wishList.wishlist
  );
  const state = useSelector((state: RootState) => state.stock.state);
  const reduxDispatcher = useDispatch();

  const rowsPerPage = 7;
  const indexOfLastStock = page * rowsPerPage;
  const indexOfFirstStock = indexOfLastStock - rowsPerPage;
  const renderStocks = value === "2" ? wishListStocks : stocks;

  const handleWishListClick = (stock: IStockData) => {
    if (stock.wishlisted) {
      reduxDispatcher(removeItem(stock));
      reduxDispatcher(markAsNotWishlisted(stock));
    } else {
      reduxDispatcher(addItem(stock));
      reduxDispatcher(markAsWishlisted(stock));
    }
    console.log(wishListStocks);
  };

  const handleChange = (newValue: string) => {
    setValue(newValue);
    setPage(1);
  };

  const handlePageChange = (
    event: React.ChangeEvent<unknown>,
    value: number
  ) => {
    setPage(value);
  };

  return (
    <>
      {state === "pending" && <LoadingSpinner />}
      {state === "fulfilled" && (
        <DasboardSection>
          <TabSection>
            <Tabs
              value={value}
              onChange={(event, newValue) => handleChange(newValue)}
            >
              <Tab
                label="Explore"
                value="1"
                sx={{
                  color: "black",
                  fontFamily: "Poppins, sans-serif",
                  fontSize: "1rem",
                  "&.Mui-selected": {
                    borderBottom: "2px solid #1971c2",
                    color: "#1971c2",
                  },
                }}
              />
              <Tab
                label="My WatchList"
                value="2"
                sx={{
                  color: "black",
                  fontFamily: "Poppins, sans-serif",
                  fontSize: "1rem",
                  "&.Mui-selected": {
                    borderBottom: "2px solid #1971c2",
                    color: "#1971c2",
                  },
                }}
              />
            </Tabs>
          </TabSection>

          <TableSection>
            <TableContainer
              sx={{ border: "3px solid #5B6065", borderRadius: "15px" }}
              component={Paper}
            >
              <Table>
                <TableHead
                  sx={{
                    marginInline: "20px",
                    borderBottom: "3px solid #5B6065",
                  }}
                >
                  <TableRow>
                    <TableCell
                      sx={{
                        fontFamily: "Poppins, sans-serif",
                        fontWeight: 300,
                        fontSize: "1.6rem",
                        paddingLeft: "50px",
                        paddingBlock: "20px",
                      }}
                    >
                      <LinkTag to={`/stock/${stock.stock_symbol}`}>
                        {stock.stock_name}
                      </LinkTag>
                        marginInline: "200px",
                        width: "60%",
                        paddingLeft: "50px",
                        paddingBlock: "30px",
                      }}
                    >
                      Company
                    </TableCell>
                    <TableCell
                      align="center"
                      sx={{
                        fontFamily: "Poppins, sans-serif",
                        fontWeight: 300,
                        fontSize: "1.6rem",
                        width: "15%",
                      }}
                    >
                      {stock.base_price}
                      Base Price
                    </TableCell>
                    <TableCell
                      align="center"
                      sx={{
                        fontFamily: "Poppins, sans-serif",
                        fontWeight: 300,
                        fontSize: "1.6rem",
                        width: "15%",
                        paddingRight: "50px",
                      }}
                    >
                      Watchlist
                    </TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {renderStocks
                    .slice(indexOfFirstStock, indexOfLastStock)
                    .map((stock) => (
                      <TableRow
                        key={stock.stock_name}
                        sx={{
                          "&:last-child td, &:last-child th": { border: 0 },
                        }}
                      >
                        <TableCell
                          sx={{
                            fontFamily: "Poppins, sans-serif",
                            fontWeight: 300,
                            fontSize: "1.6rem",
                            paddingLeft: "50px",
                            paddingBlock: "20px",
                          }}
                        >
                          <LinkTag to={`/stock/${stock.stock_symbol}`}>
                            {stock.stock_name}
                          </LinkTag>
                        </TableCell>
                        <TableCell
                          align="center"
                          sx={{
                            fontFamily: "Poppins, sans-serif",
                            fontWeight: 300,
                            fontSize: "1.6rem",
                            width: "15%",
                          }}
                        >
                          {stock.base_price}
                        </TableCell>
                        <TableCell
                          align="center"
                          sx={{
                            fontFamily: "Poppins, sans-serif",
                            fontWeight: 300,
                            fontSize: "1.6rem",
                            width: "15%",
                          }}
                        >
                          <WishListImageComponent
                            onClick={() => handleWishListClick(stock)}
                            wishlisted={stock.wishlisted}
                          />
                        </TableCell>
                      </TableRow>
                    ))}
                </TableBody>
              </Table>
              <Stack
                spacing={2}
                style={{
                  alignItems: "center",
                  paddingBlock: "20px",
                  borderTop: "1px solid black",
                  paddingBottom: "30px",
                }}
              >
                <Pagination
                  count={Math.ceil(stocks.length / rowsPerPage)}
                  color="primary"
                  page={page}
                  onChange={handlePageChange}
                />
              </Stack>
            </TableContainer>
          </TableSection>
        </DasboardSection>
      )}
      {state === "error" && <p>Data not found</p>}
    </>
  );
}
