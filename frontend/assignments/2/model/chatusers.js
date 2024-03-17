const chatusers = [

];

function addActiveUser(user) {
    chatusers.push(user);
}

function removeUserBySocketId(socketId) {
    const index = chatusers.findIndex(user => user.socketid === socketId);
    if (index !== -1) {
        chatusers.splice(index, 1);
    }
}

module.exports = {
    chatusers,
    addActiveUser,
    removeUserBySocketId
};