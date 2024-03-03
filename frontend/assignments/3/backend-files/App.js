const express = require("express");
const http = require("http");
const cors = require("cors");
const socketIo = require("socket.io");

const { getRandomName } = require("./UserNames");
const { addUserToRoom, removeUserFromRoom, users } = require("./User");
const { Console } = require("console");

const app = express();
const server = http.createServer(app);
const io = new socketIo.Server(server, {
  cors: {
    origin: "http://localhost:5173",
  },
});

function convertToTimeFormat(dateString) {
  const date = new Date(dateString);
  const timeString = date.toLocaleTimeString([], {
    hour: "2-digit",
    minute: "2-digit",
  });
  return timeString;
}

app.use(cors());
app.use(express.json());

io.on("connection", (socket) => {
  console.log("Connection established:", socket.id);

  socket.on("join-room", (stockSymbol) => {
    addUserToRoom(stockSymbol, socket.id);
    socket.join(stockSymbol);
    socket.emit("user-joined", getRandomName());

    console.log(`${socket.id} joined room: ${stockSymbol}`);
  });

  socket.on("disconnect", () => {
    const user = users.find((user) => user.socketId === socket.id);
    if (user) {
      io.to(user.stockName).emit(
        "user-left",
        `A user has left the ${user.stockName} room`
      );
      console.log("User disconnected", socket.id);
    }
    removeUserFromRoom(socket.id);
  });

  socket.on("user-transaction", (transaction, userName, stockName) => {
    if (transaction.type == "Buy") {
      io.to(transaction.stockName)
        .except(socket.id)
        .emit(
          "new-transaction",
          `${userName} bought ${transaction.amount} ${stockName}`,
          convertToTimeFormat(transaction.time)
        );
    }
    if (transaction.type == "Sell") {
      io.to(transaction.stockName)
        .except(socket.id)
        .emit(
          "new-transaction",
          `${userName} sold ${transaction.amount} ${stockName}`,
          convertToTimeFormat(transaction.time)
        );
    }
  });
});

server.listen(5001, () => {
  console.log("Server starting on port 5001");
});
