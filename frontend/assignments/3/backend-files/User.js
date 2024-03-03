const users = [];

function addUserToRoom(stockSymbol, socketId) {
  users.push({ stockSymbol, socketId });
}

function removeUserFromRoom(socketId) {
  const index = users.findIndex((user) => user.socketId === socketId);
  if (index !== -1) {
    users.splice(index, 1);
  }
}

module.exports = { users, addUserToRoom, removeUserFromRoom };
