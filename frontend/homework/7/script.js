const socket=io("http://localhost:5001");

const sendButton=document.getElementById("send-message-button");
const inputBox=document.getElementById("chat-input");

function addMessage(sender,message){

    let messageContainer = document.createElement('div');
    messageContainer.className = 'message-format';

    let avatarImage = document.createElement('img');
    avatarImage.className = 'avatar-logo';
    avatarImage.alt = 'avatar image';

    if(sender==="You") {
        avatarImage.src = 'images/img1.png';
    } else if(sender==="User") {
        avatarImage.src = 'images/img2.jpeg';
    } else{
        avatarImage.src = '';
    }
    
    let textMessage = document.createElement('p');
    textMessage.className = 'text-message';
    textMessage.textContent = message;

    messageContainer.appendChild(avatarImage);
    messageContainer.appendChild(textMessage);

    let messagesSection = document.querySelector(".messages");
    messagesSection.appendChild(messageContainer);

}

sendButton.addEventListener("click",() => {
    const message=inputBox.value;

    if(!message){
        console.log("no message to send");
        return;
    }
    addMessage("You",message);
    inputBox.value="";
    inputBox.focus();
    socket.emit("message",message);
})

socket.on("new-message", (message) => {
    addMessage("User", message);
});
