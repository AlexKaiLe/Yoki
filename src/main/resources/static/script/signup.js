/**
 * Onclick handler for signup button
 */
function onSignupPressed() {
    getKey()
}

/**
 * Checks if all the signup input fields have valid values
 * @returns {boolean}
 */
function validateFields() {
    let firstName = document.getElementById("signup-first-name").value
    let lastName = document.getElementById("signup-last-name").value
    let email = document.getElementById("signup-email").value
    let password = document.getElementById("signup-password").value
    let confirmPassword = document.getElementById("signup-password-confirm").value
    let major = document.getElementById("signup-major").value
    let bio = document.getElementById("signup-bio").value

    if (firstName == ""
        || lastName == ""
        || email == ""
        || password == ""
        || confirmPassword == ""
        || major == ""
        || bio == "") {
        document.getElementById("signup-status").innerText = "All fields must be filled!";
        document.getElementById("signup-status").style.opacity = 100;
        return false;
    }
    if (password != confirmPassword) {
        document.getElementById("signup-status").innerText = "Password and confirm password must match!"
        document.getElementById("signup-status").style.opacity = 100;
        return false;
    }
    if (!email.endsWith("@brown.edu")) {
        document.getElementById("signup-status").innerText = "Email must be a Brown University email!"
        document.getElementById("signup-status").style.opacity = 100;
        return false;
    }
    return true;
}

/**
 * encrypts any given text
 * @param text
 * @returns {string}
 */
function encrypt(text, key) {
    const keyutf = CryptoJS.enc.Utf8.parse(key);
    const iv = CryptoJS.enc.Base64.parse(key);

    const enc = CryptoJS.AES.encrypt(text, keyutf, {iv: iv});
    const encStr = enc.toString();
    return encStr;
}

/**
 * sends a post request to sign up a user
 * @param postPara
 */
function requestSignup(postPara) {
    fetch('http://localhost:4567/addUser', {
        method: 'post',
        body: JSON.stringify(postPara),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .then((response) =>
            response.json())
        .then((data) => {
            if (data.success == "true") {
                let loginBtn = document.getElementById("login-button")
                loginBtn.getElementsByTagName("p")[0].style.opacity = 0;
                setTimeout(function () {
                    loginBtn.style.width = "60px";
                    setTimeout(function () {
                        window.location.href = "/main";
                    }, 1500);
                }, 500);
            } else {
                document.getElementById("signup-status").innerText = "Email is already taken!";
                document.getElementById("signup-status").style.opacity = 100;
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}

function getKey() {

    fetch('http://localhost:4567/key', {
        method: 'post',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .then((response) =>
            response.json())
        .then((data) => {
            let firstName = document.getElementById("signup-first-name").value
            let lastName = document.getElementById("signup-last-name").value
            let email = document.getElementById("signup-email").value
            let password = document.getElementById("signup-password").value
            let year = document.getElementById("signup-year").value
            let major = document.getElementById("signup-major").value
            let bio = document.getElementById("signup-bio").value
            if (validateFields()) {
                const postPara = {
                    firstName: firstName,
                    lastName: lastName,
                    email: email,
                    password: encrypt(password, data.key),
                    year: year,
                    major: major,
                    bio: bio
                }
                requestSignup(postPara)
            }
        })
}
