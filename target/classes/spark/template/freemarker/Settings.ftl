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

<#assign logout>
    <button class="settings" id="match-button" onclick="login()">Logout</button><br>
</#assign>

<#assign LD>
    <button class="settings" id="match-button" onclick="lightDark()">Light/Dark Mode</button><br>
</#assign>

<#assign TC>
    <button class="settings" id="match-button" onclick="termsConditions()">Terms and Conditions</button><br>
</#assign>

<#assign report>
    <button class="settings" id="match-button" onclick="report()">Report</button>
</#assign>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Settings</title>
    <link rel="stylesheet" href="css/settings.css">
    <link rel="stylesheet" href="css/matching.css">
</head>
<script src="script/settings.js"></script>
<script src="script/lightDark.js"></script>
<body>
<div id="profile">
    ${nav}
    <h2>Settings</h2><br>
    <div id="main">
        <div class="options">
            ${logout}
            ${LD}
            ${TC}
            ${report}
        </div>
    </div>
</div>


</body>
</html>