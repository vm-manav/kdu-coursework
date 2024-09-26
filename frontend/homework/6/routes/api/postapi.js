const express = require("express");
const router = express.Router();
const uuid = require("uuid");
const posts = require("../../model/posts");

router.get('/',(req,res) => {
    res.json(posts);
})

router.post('/',(req,res) => {

    if(!req.body.username){
        return res.status(400).json({msg : "please provide username"});
    } else if(!req.body.name){
        return res.status(400).json({msg : "please provide name"});
    } else if(!req.body.post){
        return res.status(400).json({msg : "nothing to post .."});
    }

    const newPost={
        id: uuid.v4(),
        name: req.body.name,
        username: req.body.username,
        post: req.body.post
    };

    posts.push(newPost);
    res.json({msg : `Post added successfully with id ${newPost.id}`});
})

router.get('/:id',(req,res) => {
    posts.forEach(post => {
        if(post.id===req.params.id){
            return res.json(post);
        }
    })
    res.status(400).json({msg : `no post found with id ${req.params.id}`});
})

router.put('/:id',(req,res) => {
    posts.forEach(post => {
        if(post.id===req.params.id){
            post.name=req.body.name ? req.body.name : post.name;
            post.username=req.body.username ? req.body.username : post.username;
            post.post=req.body.post ? req.body.post : post.post; 

            return res.json(post);
        }
    })
    res.status(400).json({msg : `no post found with id ${req.params.id}`});
})

router.delete('/:id', (req, res) => {
    const updatedPosts = posts.filter(post => post.id !== req.params.id);
    if (updatedPosts.length < posts.length) {
        posts.length = 0;
        posts.push(...updatedPosts);
        return res.json({ msg: "Post deleted" });
    } else {
        return res.status(400).json({ msg: `No post found with id ${req.params.id}` });
    }
});
module.exports=router;