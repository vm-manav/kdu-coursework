const apiUrlLogin = 'http://localhost:5002/api/user/login';

let loginButton=document.getElementById("login-button");
loginButton.addEventListener("click",()=>{
    let usernameInputValue = document.getElementById("username").value;
    let passwordInputValue = document.getElementById("password").value;

    if (!usernameInputValue || !passwordInputValue) {
        console.log("Username or password not found");
        return;
    }

    const loginData={username:usernameInputValue,password:passwordInputValue};
    
    const requestOptions = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(loginData),
    };
    
    fetch(apiUrlLogin, requestOptions)
        .then(response => {
            console.log(response)
            if(response.ok==true) {
                window.location.href=`http://127.0.0.1:5500/frontend-files/html-files/index.html?username=${usernameInputValue}`;
            } else{
                alert("invalid username or password");
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
})