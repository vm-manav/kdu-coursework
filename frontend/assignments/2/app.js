const express=require("express");
const http=require("http");
const moment = require('moment');
const cors=require("cors");
const socketIo=require("socket.io");
const users = require("./model/users");
const posts = require("./model/posts");
const messagesModule = require('./model/messages');
const {chatusers,addActiveUser,removeUserBySocketId} = require("./model/chatusers");
const { v4: uuidv4 } = require('uuid');

const app=express();
const server=http.createServer(app);

const io=new socketIo.Server(server,{
    cors:{
        origin: "http://127.0.0.1:5500"
    }
});

app.use(cors());
app.use(express.json());

app.post("/api/user/login",(req,res)=>{
    const username=req.body.username;
    const password=req.body.password;
    let flag=1;
    users.forEach((user)=>{
        if(user.user_name===username && user.password===password) {
            res.status(200).json({authorized:true});
            flag=0;
        }
    })
    if(flag==1) {
        res.status(400).json({authorized:false});
    }
})

app.post('/api/posts', (req, res) => {
    const { content,username,fullName,profile_url,imagelink} = req.body;
    if (!content) {
        return res.status(400).json({ error: 'Content cannot be empty' });
    }
    const newPost = {
        id: uuidv4(),
        user_handel: username,
        content: content,
        fullName:fullName,
        profile_url:profile_url,
        imagelink:imagelink
    };
    posts.unshift(newPost);
    res.json(newPost);
    console.log(newPost);
});

app.get('/api/posts', (req, res) => {
    const { page, pageSize } = req.query;

    const startIndex = (page - 1) * pageSize;
    const endIndex = startIndex + Number(pageSize);

    const paginatedPosts = posts.slice(startIndex, endIndex);

    res.json(paginatedPosts);
});

app.get('/api/post', (req, res) => {
    const postId = req.query.id;

    const post = posts.find(post => post.id === postId);

    if (!post) {
        return res.status(404).json({ error: 'Post not found' });
    }
    res.json(post);
});


io.on("connection",(socket)=>{
    console.log("connection established");

    socket.on("joinuser",(username) => {
        users.forEach((user)=>{
            if(user.user_name===username){
                let data={
                    username:user.user_name,
                    name:user.name,
                    image:user.profile_url
                }
                let activeUser={
                    user_handle:user.user_name,
                    fullName:user.name,
                    profile_url:user.profile_url,
                    socketid:socket.id,
                }
                addActiveUser(activeUser);
                socket.emit("takedata",data);
                io.emit("updateliveusers",chatusers);
            }
        })
    })
    socket.on("getinitialmessages",(socketid)=>{
        socket.emit("initialupdatedmessage",messagesModule.getMessages(socketid));
    })

    socket.on("disconnect",() => {
        console.log("user disconnected");
        removeUserBySocketId(socket.id);
        io.emit("updateliveusers",chatusers);
    })

    socket.on("newmessage",(data)=>{
        if(data.sendersocketid===data.receiversocketid) {
            messagesModule.addMessage(data.sendersocketid,data.receiversocketid,'you',data.message,formatChatMessageTime());            
        } 
        else{
            messagesModule.addMessage(data.sendersocketid,data.receiversocketid,'you',data.message,formatChatMessageTime());
            messagesModule.addMessage(data.receiversocketid,data.sendersocketid,'user',data.message,formatChatMessageTime())
        }
        let dataobj={
            sendersocketid:data.sendersocketid,
            senderdata:messagesModule.getMessages(data.sendersocketid),
            receiversocketid:data.receiversocketid,
            receiverdata:messagesModule.getMessages(data.receiversocketid)
        }
        io.emit("takeupdatedmessage",dataobj);
        console.log(dataobj);
    })
})


function formatChatMessageTime(timestamp) {
    const messageTime = moment(timestamp);
    const currentTime = moment();
    const differenceInMinutes = currentTime.diff(messageTime, 'minutes');

    if (differenceInMinutes < 10) {
        return messageTime.fromNow();
    } else {
        return messageTime.format('h:mm A');
    }
}

function getCurrentTime() {
    const now = new Date();
    let hours = now.getHours();
    let minutes = now.getMinutes();
    let ampm = hours >= 12 ? 'PM' : 'AM';
    hours = hours % 12 || 12;
    minutes = minutes < 10 ? '0' + minutes : minutes;
    const currentTime = `${hours}:${minutes} ${ampm}`;
    return currentTime;
}

server.listen(5002,()=>{
    console.log("server starting");
})
