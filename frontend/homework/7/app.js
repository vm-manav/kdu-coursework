const express=require("express");
const http=require("http");
const cors=require("cors");
const socketIo=require("socket.io");

const app=express();
const server=http.createServer(app);
const io=new socketIo.Server(server,{
    cors:{
        origin: "http://127.0.0.1:5500"
    }
});

app.use(cors());
app.use(express.json());

app.get("/",(req,res)=>{
    res.json({msg:"hello"});
})

io.on("connection",(socket)=>{
    console.log("connection established");

    socket.on("message",(messageData) => {
        console.log(messageData);
        io.except(socket.id).emit("new-message",messageData);
    })

    io.on("disconnect",(socket) => {
        console.log("user disconnected")
    })
})

server.listen(5001,()=>{
    console.log("server starting");
})