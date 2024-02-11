var postButton = document.getElementById('tweet-btn');

var postInput = document.querySelector('.post-input');

postInput.addEventListener('keyup', function () {
    var inputValue = postInput.value.trim(); 

    postButton.style.backgroundColor = inputValue ? '#1D9BF0' : '';
});


postButton.addEventListener('click', function () {
    var inputValue = postInput.value;

    if (inputValue){
    function formatHashtags(text) {
        return text.replace(/#(\w+)/g, '<span class="bluetext">#$1</span>');
    }
    var formattedInput = formatHashtags(inputValue);
    var newPost = document.createElement('div');
    newPost.className = 'post1';

    newPost.innerHTML = `
        <div class="profile-pic-post">
            <img id="profile-image-post" src="images/profile pic.png" alt="profile pic">
        </div>
        <div class="post-details">
        <div class="post-user-cont"><p>Nitesh Gupta <span id="user-handel">@nit_hck · 1s</span></p></div>
            <div class="content-post">
                <p>${formattedInput}</p>
            </div>
            <div class="post-react-svg">
                <div class="post-svg-cont">
                    <img class="post-reaction-svg" src="icons/comment.svg" alt="comment svg">
                </div>
                <div class="post-svg-cont">
                    <img class="post-reaction-svg" src="icons/retweet.svg" alt="retweet">
                </div>
                <div id="like-class" class="post-svg-cont">
                    <img id="like-svg" class="post-reaction-svg like-post unlike-post" src="icons/heart.svg" alt="heart svg">
                    <p class="likes-count">1</p>
                </div>
                <div id="reaction-count"  class="post-svg-cont">
                    <img class="post-reaction-svg" src="icons/stats.svg" alt="stats svg">
                    <p class="reaction-count">1</p>
                </div>
                <div class="post-svg-cont">
                    <img class="post-reaction-svg" src="icons/bookmark2.svg" alt="bookmark svg">
                </div>
                    </div>
        </div>
        <div class="others">
            <div class="other-icons">
                <img class="other-icon-svg" src="icons/threedots2.svg" alt="three dots">
            </div>
            <div class="other-icons">
                <img class="other-icon-svg" src="icons/share.svg" alt="upload icon">
            </div>
        </div>
    `;
    var postsContainer = document.querySelector('.posts');
    postsContainer.appendChild(newPost);

  postInput.value = '';
  postButton.style.backgroundColor = '';

    }
    else {
        alert('Please enter a non-empty post before submitting.');
    }
    
});

var profileIcon = document.querySelector('.profile-icon');

var navbarSection = document.querySelector('.navigation-section');

profileIcon.addEventListener('click', function() {
    navbarSection.style.display = (navbarSection.style.display === 'flex') ? 'none' : 'flex';
});

document.addEventListener('DOMContentLoaded', function() {
    var floatingTweetBoxIcon = document.querySelector('.floating-tweet-box-icon');
    var mainBodySection = document.querySelector('.main-body');
    var profileLogoButton = document.querySelector('.profile-logo-button');
    var postIconMobile = document.querySelector('.post-icon-mobile');
    floatingTweetBoxIcon.addEventListener('click', function() {
        mainBodySection.style.display = 'flex';
        profileLogoButton.style.display = 'none';
        postIconMobile.style.display = 'none';
    });
});

var liked = false;

document.addEventListener('DOMContentLoaded', function() {
    var likeSvg = document.getElementById('like-svg');
    var likesCount = document.querySelector('.likes-count');
    var reactionCount = document.querySelector('.reaction-count');
    likeSvg.addEventListener('click', function() {
        likesCount.style.display = liked ? 'none' : 'block';
        reactionCount.style.display = liked ? 'none' : 'block';
        var likeImage = document.getElementById('like-svg');
        likeImage.src = liked ? "icons/heart.svg" : "icons/heart_full.svg";
        liked = !liked;
    });
});
