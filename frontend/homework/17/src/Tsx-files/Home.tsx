import { useEffect, useRef } from "react";
import { ItemCard } from "./ItemCard";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../Redux/Store";
import { setDisplayItems } from "../Redux/DisplayItems";
import "../Css-files/Home.scss";
import { ToastContainer, toast } from "react-toastify";

import "react-toastify/dist/ReactToastify.min.css";
import { Spinner } from "./Spinner";

export default function Home() {
  const toastRef = useRef(false);
  const state = useSelector((state: RootState) => state.items.state);
  const items = useSelector((state: RootState) => state.items.items);
  const displayItems = useSelector(
    (state: RootState) => state.displayItems.displayItems
  );
  const reduxDispatcher = useDispatch();

  useEffect(() => {
    reduxDispatcher(setDisplayItems(items));
  }, [items]);

  useEffect(() => {
    if (!toastRef.current) {
      if (state === "fulfilled") {
        toast.success("Success", {
          position: "top-center",
        });
        toastRef.current = true;
      }

      if (state === "error") {
        toast.error("Error", {
          position: "top-center",
        });
        toastRef.current = true;
      }
    }
  }, [state]);

  return (
    <>
      <ToastContainer />
      {state === "pending" && <Spinner />}
      {state === "fulfilled" && (
        <main>
          <p className="main-heading">
            KDU <span className="marketplace">MARKETPLACE</span>
          </p>
          <div className="items-container">
            {displayItems.length === 0 && (
              <p className="no-items-found">No Items Found</p>
            )}
            {displayItems.length > 0 &&
              displayItems.map((item) => {
                return <ItemCard key={item.id} item={item} />;
              })}
          </div>
        </main>
      )}
      {state === "error" && <div></div>}
    </>
  );
}
