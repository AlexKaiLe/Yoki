/**
 * onClick handler for login button
 */
function onLoginPressed() {
    let password = document.getElementById("login-password").value
    encrypt(password);
}

/**
 * encrypts given text
 * @param text
 * @returns {string}
 */

function encrypt(text) {

    fetch('http://localhost:4567/key', {
        method: 'post',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .then((response) =>
            response.json())
        .then((data) => {
            console.log(data.key)
            let email = document.getElementById("login-email").value
            const key = data.key
            const keyutf = CryptoJS.enc.Utf8.parse(key);
            const iv = CryptoJS.enc.Base64.parse(key);

            const enc = CryptoJS.AES.encrypt(text, keyutf, { iv: iv });
            const encStr = enc.toString();
            requestLogin(email, encStr);
        })
}

/**
 * authenticates user login information
 * @param email
 * @param password
 */
function requestLogin(email, password) {
    const postPara = {
        email: email,
        password: password
    };
    fetch('http://localhost:4567/login', {
        method: 'post',
        body: JSON.stringify(postPara),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .then((response) =>
            response.json())
        .then((data) => {
            if (data.authenticated == "true") {
                let loginBtn = document.getElementById("login-button")
                loginBtn.getElementsByTagName("p")[0].style.opacity = 0;
                setTimeout(function(){
                    loginBtn.style.width = "60px";
                    setTimeout(function() {
                        window.location.href = "/main";
                    }, 1500);
                }, 500);
            } else {
                document.getElementById("login-status").style.opacity = 100;
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}