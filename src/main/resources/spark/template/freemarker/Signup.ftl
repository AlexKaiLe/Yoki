<#assign signup>
    <img src="/images/whiteLogo.png" id="logo-login"><br>
    <div class="container">
        <label for="first-name"><b>First Name</b></label><br>
        <input type="text" placeholder="Enter First Name" name="first-name" required id="signup-first-name"
               value=""><br>
        <label for="last-name"><b>Last Name</b></label><br>
        <input type="text" placeholder="Enter Last Name" name="last-name" required id="signup-last-name" value=""><br>


        <label for="email"><b>Email</b></label><br>
        <input type="text" placeholder="@brown.edu" name="email" required id="signup-email" value=""><br>

        <label for="psw"><b>Password</b></label><br>
        <input type="password" placeholder="Enter Password" name="psw" required id="signup-password" value=""><br>
        <label for="psw-confirm"><b>Confirm Password</b></label><br>
        <input type="password" placeholder="Confirm Password" name="psw-confirm" required id="signup-password-confirm"
               value=""><br>

        <label for="year"><b>Class Year</b></label><br>
        <select name="year" id="signup-year" required>
            <option value="2023">2021</option>
            <option value="2023.5">2021.5</option>
            <option value="2024">2022</option>
            <option value="2024.5">2022.5</option>
            <option value="2023">2023</option>
            <option value="2023.5">2023.5</option>
            <option value="2024">2024</option>
            <option value="2024.5">2024.5</option>
        </select><br>

        <label for="major"><b>Major/Concentration</b></label><br>
        <input type="text" placeholder="Enter Major/Concentration" name="major" required id="signup-major" value=""><br>

        <label for="bio"><b>Biography</b></label><br>
        <textarea maxlength="150" placeholder="Enter bio here..." name="bio" required id="signup-bio"></textarea>

        <div id="signup-status">Incorrect login information!</div>

        <button type="submit" class="button" id="login-button" onclick="onSignupPressed()"><p>Sign up</p></button>
        <div id="signup-redir-wrapper">
            <h6 class="no-account">Already have an account?</h6>
            <a class="no-account" href="/main"><h6 id="signup-link">Sign in</h6></a>
        </div>
    </div>
</#assign>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="css/matching.css">
    <link rel="stylesheet" href="css/login.css">
</head>
<script src="script/signup.js"></script>
<script src="script/lightDark.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.2/rollups/aes.js"
        integrity="sha256-/H4YS+7aYb9kJ5OKhFYPUjSJdrtV6AeyJOtTkw6X72o=" crossorigin="anonymous"></script>
<body>
<div id="signup-div">
    ${signup}
</div>


</body>
</html>