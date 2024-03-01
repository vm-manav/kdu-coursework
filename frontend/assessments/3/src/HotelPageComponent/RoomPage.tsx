import { useEffect, useRef, useState } from "react";
import {
  CostSection,
  HotelHeadingSection,
  HotelPageSection,
  OptionTypeWrapper,
  TypeCard,
  TypeContainer,
} from "./RoomPageStyles";
import dayjs, { Dayjs } from "dayjs";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.min.css";
import { DemoContainer } from "@mui/x-date-pickers/internals/demo";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../Redux/Store";
import { getRoom } from "../Thunk/GetRoomType";
import Spinner from "../SpinnerComponent/Spinner";
import { RoomType } from "../Type/RoomType";

export function RoomPage() {
  const toastRef = useRef(false);

  const [startValue, setStartValue] = useState<Dayjs | null>(
    dayjs("2024-03-01")
  );
  const [endValue, setEndValue] = useState<Dayjs | null>(dayjs("2024-03-02"));
  const [roomType, setRoomType] = useState<RoomType | null>(null);
  const [cost, setCost] = useState<number>(0);

  const room = useSelector((state: RootState) => state.room.room);
  const state = useSelector((state: RootState) => state.room.state);

  const reduxDispatcher = useDispatch();
  useEffect(() => {
    reduxDispatcher(getRoom());
  }, [reduxDispatcher]);

  useEffect(() => {
    if (!toastRef.current) {
      if (state === "fulfilled") {
        toast.success("Success", {
          position: "top-center",
        });
      }

      if (state === "error") {
        toast.error("Error while making api call ..", {
          position: "top-center",
        });
      }
    }
  }, [state]);

  function handelRoomTypeCLick(roomItem: RoomType) {
    setRoomType(roomItem);
  }

  useEffect(() => {
    computePrice();
  }, [roomType]);

  function computePrice() {
    if (roomType) {
      const costTotal: number = parseInt(roomType.costPerNight);
      setCost(costTotal);
    } else {
      setCost(0);
    }
  }

  return (
    <>
      <ToastContainer />
      {state === "pending" && <Spinner />}
      {state === "fulfilled" && (
        <HotelPageSection>
          <HotelHeadingSection>Hotel Booking</HotelHeadingSection>
          <OptionTypeWrapper>
            <p>Select Room Type</p>
            <TypeContainer>
              {room.map((roomItem) => {
                return (
                  <TypeCard
                    key={roomItem.id}
                    onClick={() => handelRoomTypeCLick(roomItem)}
                  >
                    {roomItem.name}
                  </TypeCard>
                );
              })}
            </TypeContainer>
          </OptionTypeWrapper>
          <OptionTypeWrapper>
            <p>Select Date</p>

            {!roomType && <span>Please select a room type first</span>}
            {roomType && (
              <LocalizationProvider dateAdapter={AdapterDayjs}>
                <DemoContainer components={["DatePicker", "DatePicker"]}>
                  <DatePicker
                    label="Start Date"
                    value={startValue}
                    onChange={(newValue) => setStartValue(newValue)}
                  />
                  <DatePicker
                    label="End Date"
                    value={endValue}
                    onChange={(newValue) => setEndValue(newValue)}
                  />
                </DemoContainer>
              </LocalizationProvider>
            )}
          </OptionTypeWrapper>
          <OptionTypeWrapper>
            <p>Select additional add ons/preferences</p>
            <TypeContainer>
              {!roomType && <span>Please select a room type first</span>}
              {roomType?.addOns.map((item) => {
                return <TypeCard key={item.name}>{item.name}</TypeCard>;
              })}
            </TypeContainer>
          </OptionTypeWrapper>
          <CostSection>
            <p>Cost + 18% Gst = {cost}</p>
            <button>Submit</button>
          </CostSection>
        </HotelPageSection>
      )}
      {state === "error" && <div></div>}
    </>
  );
}
