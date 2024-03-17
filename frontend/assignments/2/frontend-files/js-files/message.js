const socket=io("http://localhost:5002");

function getUsernameFromUrl() {
    const currentUrl = window.location.href;
    const match = currentUrl.match(/[?&]username=([^&]+)/);
    socket.emit("joinuser",match[1]);
}
getUsernameFromUrl();

let username="";
let fullName="";
let profile_url="";

socket.on("takedata",(data)=>{
    username=data.username;
    fullName=data.name;
    profile_url=data.image;


    let lowerNavBlockImage=document.getElementById("profile-image-nitesh");
    lowerNavBlockImage.src=profile_url;

    let profileUserName=document.getElementById("name");
    profileUserName.innerHTML=fullName;

    let profileUserHandel=document.getElementById("username");
    profileUserHandel.innerHTML="@"+username;

    let profileIconMobile=document.querySelector(".profile-icon");
    profileIconMobile.src=profile_url;
})

let senderSocketId="";
let receiverSocketId="";


socket.on("updateliveusers", (chatUsers) => {

    const usersContainer = document.querySelector(".users-dm");

    usersContainer.innerHTML = "";

    chatUsers.forEach((user) => {
        if(user.user_handle===username){
            senderSocketId=user.socketid;
        }
        const individualUserDiv = document.createElement("div");
        individualUserDiv.classList.add("messages-individual-user");

        const userImage = document.createElement("img");
        userImage.classList.add("messages-user-image");
        userImage.src = user.profile_url;
        userImage.alt = "profile image";

        const userNameParagraph = document.createElement("p");
        userNameParagraph.classList.add("messages-user-name");
        userNameParagraph.textContent = user.fullName;

        const userUsernameParagraph = document.createElement("p");
        userUsernameParagraph.classList.add("messages-user-username");
        userUsernameParagraph.textContent = `@${user.user_handle}`;

        individualUserDiv.appendChild(userImage);
        individualUserDiv.appendChild(userNameParagraph);
        individualUserDiv.appendChild(userUsernameParagraph);

        individualUserDiv.addEventListener("click", () => {
            openChat(user);
        });
        usersContainer.appendChild(individualUserDiv);
    });
});

socket.on("initialupdatedmessage", (messagesarr)=>{
    addMessagesToDom(messagesarr);
})

socket.on("takeupdatedmessage",(messageobj)=>{
    if(messageobj.sendersocketid===senderSocketId){
        addMessagesToDom(messageobj.senderdata);
    } 
    else if(messageobj.receiversocketid===senderSocketId){
        addMessagesToDom(messageobj.receiverdata);
    }
})


function addMessagesToDom(messagesarr){
    console.log("hello");
    const conversationContainer = document.querySelector('.coversation-container');
    conversationContainer.innerHTML="";
    messagesarr.forEach((message) => {
        if (message.receiversocketid === receiverSocketId) {
            const messageDiv = document.createElement('div');

            if (message.sender === 'you') {
                messageDiv.classList.add('coversation-you');
            } else {
                messageDiv.classList.add('coversation-user');
            }

            const messageParagraph = document.createElement('p');
            messageParagraph.classList.add('conversation-message');
            messageParagraph.textContent = message.message;

            const timeParagraph = document.createElement('p');
            timeParagraph.classList.add('conversation-time');
            timeParagraph.textContent=message.time;

            messageDiv.appendChild(messageParagraph);
            messageDiv.appendChild(timeParagraph);

            conversationContainer.appendChild(messageDiv);
        }
    });
}




function openChat(user) {
    receiverSocketId=user.socketid;
    const chatContainer = document.querySelector('.direct-message-container');

    const userNameDiv = document.querySelector('.direct-message-user-name');

    userNameDiv.querySelector('p').textContent = user.fullName;

    chatContainer.style.display = 'block';

    const arrowBackDiv = userNameDiv.querySelector('.arrow-back');
    arrowBackDiv.addEventListener('click', () => {
        closeChat();
    });
    socket.emit("getinitialmessages",senderSocketId);
}

function closeChat() {
    const chatContainer = document.querySelector('.direct-message-container');
    chatContainer.style.display = 'none';
}

let messageInputBox=document.getElementById("message-box-input");
let messageSendButton=document.getElementById("message-box-button");
messageSendButton.addEventListener("click", () => {
    let message = messageInputBox.value.trim();

    if (message !== "") {
        let data = {
            sendersocketid: senderSocketId,
            receiversocketid: receiverSocketId,
            message: message
        };
        socket.emit("newmessage",data);
        console.log(`Sending message from ${senderSocketId} to ${receiverSocketId}: ${message}`);
        messageInputBox.value = "";
    }
});