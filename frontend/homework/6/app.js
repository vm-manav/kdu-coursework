const express=require("express");

const app=express();
app.use(express.json());
app.use('/api/posts',require("./routes/api/postapi"));


const PORT=5000;
app.listen(PORT,() => {
    console.log(`App running on port ${PORT}`);
})
