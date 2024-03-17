const socket=io("http://localhost:5002");


let username="";
let fullName="";
let profile_url="";

const apiUrlPost = 'http://localhost:5002/api/posts';

let postButton = document.getElementById('tweet-btn');
let postInput = document.querySelector('.post-input');
let imageupload=document.getElementById("imageInput");

postInput.addEventListener('keyup', function () {
    let inputValue = postInput.value.trim(); 

    postButton.style.backgroundColor = inputValue ? '#1D9BF0' : '';
});

postButton.addEventListener('click', ()=>{
    const inputValue = postInput.value;
    dopostoperation(inputValue);
});

async function dopostoperation(inputValue) {
    let imageurl;
    if(imageupload.files.length!=0){
        imageurl=await getImageBlob(imageupload.files[0]);
    }
    if (!inputValue) {
        console.log('Input value is empty');
        return;
    }
    const postData = {
        content: inputValue,
        username:username,
        fullName:fullName,
        profile_url:profile_url,
        imagelink:imageurl
    };
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(postData),
    };
    fetch(apiUrlPost, requestOptions)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            } else {
                console.log("data posted");
                location.reload();
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

let pageNumber = 1;
const pageSize = 5;

function fetchPosts() {
    const apiUrl = `http://localhost:5002/api/posts?page=${pageNumber}&pageSize=${pageSize}`;

    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            appendPostsToDOM(data);
            pageNumber++;
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function appendPostsToDOM(posts) {
    const postsContainer = document.querySelector('.posts');

    posts.forEach(post => {
        const postElement = createPostElement(post);
        postsContainer.appendChild(postElement);
    });
}

let postinputdiv=document.querySelector(".post-input-section1");

function displayImage(file) {
    const reader = new FileReader();
    let img = document.createElement("img");
    img.classList.add("uploaded-image")
    reader.onload = function (e) {
        img.src = e.target.result;
        console.log(e.target.result)
        postinputdiv.appendChild(img)
    };
    reader.readAsDataURL(file);
}
function getImageBlob(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onload = function (e) {
            resolve(e.target.result);
        };
        reader.onerror = function (error) {
            reject(error);
        };
        reader.readAsDataURL(file);
    });
}
imageupload.addEventListener("change", handleFiles, false);
function handleFiles() {
    const fileList = this.files;
    console.log(fileList[0]);
    displayImage(fileList[0])
}



function createPostElement(post) {
    const newPost = document.createElement('div');
    newPost.className = 'post1';

    const profilePicContainer = document.createElement('div');
    profilePicContainer.className = 'profile-pic-post';

    const profilePic = document.createElement('img');
    profilePic.id = 'profile-image-post';
    profilePic.src = post.profile_url;
    profilePic.alt = 'profile pic';

    profilePicContainer.appendChild(profilePic);

    const postDetailsContainer = document.createElement('div');
    postDetailsContainer.className = 'post-details';

    const postUserContainer = document.createElement('div');
    postUserContainer.className = 'post-user-cont';

    const userNameElement = document.createElement('p');
    userNameElement.textContent = post.fullName;

    const userHandleContainer = document.createElement('span');
    userHandleContainer.id = 'user-handel';
    userHandleContainer.textContent = `@${post.user_handel} · 1s`;

    userNameElement.appendChild(userHandleContainer);
    postUserContainer.appendChild(userNameElement);

    postDetailsContainer.appendChild(postUserContainer);

    const contentPostContainer = document.createElement('div');
    contentPostContainer.className = 'content-post';

    const contentPostElement = document.createElement('p');
    contentPostElement.textContent = post.content;
    contentPostContainer.appendChild(contentPostElement);

    if(post.imagelink!=null){
        const imagePostElement = document.createElement('img');
        imagePostElement.src = post.imagelink;
        contentPostContainer.appendChild(imagePostElement);
    }

    const postReactContainer = document.createElement('div');
    postReactContainer.className = 'post-react-svg';

    const commentContainer = document.createElement('div');
    commentContainer.className = 'post-svg-cont';
    const commentIcon = document.createElement('img');
    commentIcon.className = 'post-reaction-svg';
    commentIcon.src = '/frontend-files/icons/comment.svg';
    commentIcon.alt = 'comment svg';
    commentContainer.appendChild(commentIcon);
    postReactContainer.appendChild(commentContainer);

    const retweetContainer = document.createElement('div');
    retweetContainer.className = 'post-svg-cont';
    const retweetIcon = document.createElement('img');
    retweetIcon.className = 'post-reaction-svg';
    retweetIcon.src = '/frontend-files/icons/retweet.svg';
    retweetIcon.alt = 'retweet';
    retweetContainer.appendChild(retweetIcon);
    postReactContainer.appendChild(retweetContainer);

    const likeContainer = document.createElement('div');
    likeContainer.id = 'like-class';
    likeContainer.className = 'post-svg-cont';
    const likeIcon = document.createElement('img');
    likeIcon.id = 'like-svg';
    likeIcon.className = 'post-reaction-svg like-post unlike-post';
    likeIcon.src = '/frontend-files/icons/heart.svg';
    likeIcon.alt = 'heart svg';
    likeContainer.appendChild(likeIcon);
    postReactContainer.appendChild(likeContainer);

    const likesCountElement = document.createElement('p');
    likesCountElement.className = 'likes-count';
    likesCountElement.textContent = '1';

    likeContainer.appendChild(likesCountElement);

    const reactionCountContainer = document.createElement('div');
    reactionCountContainer.id = 'reaction-count';
    reactionCountContainer.className = 'post-svg-cont';

    const statsIcon = document.createElement('img');
    statsIcon.className = 'post-reaction-svg';
    statsIcon.src = '/frontend-files/icons/stats.svg';
    statsIcon.alt = 'stats svg';
    reactionCountContainer.appendChild(statsIcon);

    const reactionCountElement = document.createElement('p');
    reactionCountElement.className = 'reaction-count';
    reactionCountElement.textContent = '1';

    reactionCountContainer.appendChild(reactionCountElement);

    const bookmarkContainer = document.createElement('div');
    bookmarkContainer.className = 'post-svg-cont';
    const bookmarkIcon = document.createElement('img');
    bookmarkIcon.className = 'post-reaction-svg';
    bookmarkIcon.src = '/frontend-files/icons/bookmark2.svg';
    bookmarkIcon.alt = 'bookmark svg';
    bookmarkContainer.appendChild(bookmarkIcon);

    postReactContainer.appendChild(reactionCountContainer);
    postReactContainer.appendChild(bookmarkContainer);

    postDetailsContainer.appendChild(postUserContainer);
    postDetailsContainer.appendChild(contentPostContainer);
    postDetailsContainer.appendChild(postReactContainer);

    const othersContainer = document.createElement('div');
    othersContainer.className = 'others';

    const otherDotsContainer = document.createElement('div');
    otherDotsContainer.className = 'other-icons';
    const dotsIcon = document.createElement('img');
    dotsIcon.className = 'other-icon-svg';
    dotsIcon.src = '/frontend-files/icons/threedots2.svg';
    dotsIcon.alt = 'three dots';
    otherDotsContainer.appendChild(dotsIcon);

    const shareContainer = document.createElement('div');
    shareContainer.className = 'other-icons';
    const shareIcon = document.createElement('img');
    shareIcon.className = 'other-icon-svg';
    shareIcon.src = '/frontend-files/icons/share.svg';
    shareIcon.alt = 'upload icon';
    shareContainer.appendChild(shareIcon);

    othersContainer.appendChild(otherDotsContainer);
    othersContainer.appendChild(shareContainer);

    newPost.appendChild(profilePicContainer);
    newPost.appendChild(postDetailsContainer);
    newPost.appendChild(othersContainer);

    return newPost;
}

fetchPosts();

window.addEventListener('scroll', () => {
        fetchPosts();
    }
);

let messageButton=document.getElementById("message-logo");
messageButton.addEventListener("click",()=>{
    window.location.href=`http://127.0.0.1:5500/frontend-files/html-files/message.html?username=${username}`;
})
let messageButtonHome=document.getElementById("message-logo-home");
messageButtonHome.addEventListener("click",()=>{
    window.location.href=`http://127.0.0.1:5500/frontend-files/html-files/message.html?username=${username}`;
})



socket.on("takedata",(data)=>{
    username=data.username;
    fullName=data.name;
    profile_url=data.image;

    let profilePostIcon=document.getElementById("profile-image-main");
    profilePostIcon.src=profile_url;

    let lowerNavBlockImage=document.getElementById("profile-image-nitesh");
    lowerNavBlockImage.src=profile_url;

    let profileUserName=document.getElementById("name");
    profileUserName.innerHTML=fullName;

    let profileUserHandel=document.getElementById("username");
    profileUserHandel.innerHTML="@"+username;

    let mobileProfileIcon=document.getElementById("mobile-view-profile-button");
    mobileProfileIcon.src=profile_url;
})






function getUsernameFromUrl() {
    const currentUrl = window.location.href;
    const match = currentUrl.match(/[?&]username=([^&]+)/);
    socket.emit("joinuser",match[1]);
}
getUsernameFromUrl();



let profileIcon = document.querySelector('.profile-icon');

let navbarSection = document.querySelector('.navigation-section');

profileIcon.addEventListener('click', function() {
    let homeNavBar=document.querySelector('.home-navigation');
    navbarSection.style.display = (navbarSection.style.display === 'flex') ? 'none' : 'flex';
    homeNavBar.style.display='none';
});

document.addEventListener('DOMContentLoaded', function() {
    let floatingTweetBoxIcon = document.querySelector('.floating-tweet-box-icon');
    let mainBodySection = document.querySelector('.main-body');
    let profileLogoButton = document.querySelector('.profile-logo-button');
    let postIconMobile = document.querySelector('.post-icon-mobile');
    let homeNavBar=document.querySelector('.home-navigation');
    floatingTweetBoxIcon.addEventListener('click', function() {
        mainBodySection.style.display = 'flex';
        profileLogoButton.style.display = 'none';
        postIconMobile.style.display = 'none';
        homeNavBar.style.display='none';
    });
});
