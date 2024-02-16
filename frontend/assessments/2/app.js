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

    setInterval(()=>{
        sentPrice(socket);
    },5000);

    console.log("connection established");

    io.emit("connection-established");

    io.on("disconnect",(socket) => {
        console.log("user disconnected")
    })
    
    function sentPrice(socket){
        socket.emit('price-update', {price: generateRandom()});
    }
});

function generateRandom(min = 1, max = 500) {
    let difference = max - min;
    let rand = Math.random();
    rand = Math.floor( rand * difference);
    rand = rand + min;
    return rand;
}

console.log(generateRandom());


server.listen(5001,()=>{
    console.log("server starting");
})