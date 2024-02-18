const messageHashMap = new Map();

function addMessage(senderSocketId,receiversocketid,sender,message,time) {
    if (!messageHashMap.has(senderSocketId)) {
        messageHashMap.set(senderSocketId, []);
    }
    messageHashMap.get(senderSocketId).push({receiversocketid, sender, message,time});
}

function getMessages(socketId) {
    return messageHashMap.get(socketId) || [];
}

module.exports = {
    addMessage,
    getMessages
};