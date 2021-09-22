<#assign nav>
    <nav>
        <a href="/main"><img src="/images/whiteLogo.png" id="logo" alt="Yoki Logo"></a>
        <a href="/main">
            <div class="sidebar">Home</div>
        </a>
        <a href="/match">
            <div class="sidebar">Matches</div>
        </a>
        <a href="/profileOverview">
            <div class="sidebar">My Profile</div>
        </a>
        <a href="/settings">
            <div class="sidebar">Settings</div>
        </a>
    </nav>
</#assign>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Profile</title>
    <link rel="stylesheet" href="css/profileEdit.css">
    <link rel="stylesheet" href="css/matching.css">
</head>
<script src="script/profileEdit.js"></script>
<script src="script/lightDark.js"></script>
<body>
<div id="profile">
    ${nav}
    <h2>Edit Profile</h2><br>
    <div id="main">
        <div id="inputs">
            <div id="first">First Name<br><input id="firstInput" type="text"></div>
            <div id="last">Last Name<br><input id="lastInput" type="text"></div>
            <div id="major">Major<br><input id="majorInput" type="text"></div>
            <div id="gradYear">Grad Year<br><input id="gradYearInput" type="text"></div>
            <br>
            <div id="email">Email<br><input id="emailInput" type="text"></div>
            <br>
            <div id="bio">Bio<br><textarea id="bioBox" type="text"></textarea></div>
        </div>
        <div id="picDiv"></div>
    </div>
</div>
</body>
</html>