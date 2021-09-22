<#assign login>
    <img src="/images/whiteLogo.png" id="logo-login"><br>
    <div class="container">
        <div id="login-fields">
            <label for="uname"><b>Username</b></label><br>
            <input type="text" placeholder="Enter Username" name="uname" required id="login-email"
                   value="ian_henry_acosta@brown.edu"><br>

            <label for="psw"><b>Password</b></label><br>
            <input type="password" placeholder="Enter Password" name="psw" required id="login-password" value="123"><br>
        </div>

        <div id="login-status">Incorrect login information!</div>

        <button type="submit" class="button" id="login-button" onclick="onLoginPressed()"><p>Login</p></button>
        <br>
        <div id="signup-redir-wrapper">
            <h6 class="no-account">Dont have an account?</h6>
            <a class="no-account" href="/signup"><h6 id="signup-link">Sign up</h6></a>
        </div>
    </div>
</#assign>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/matching.css">
</head>
<script src="script/login.js"></script>
<script src="script/lightDark.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"
        integrity="sha256-/H4YS+7aYb9kJ5OKhFYPUjSJdrtV6AeyJOtTkw6X72o=" crossorigin="anonymous"></script>
<body>
<div id="login-div">
    ${login}
</div>


</body>
</html>