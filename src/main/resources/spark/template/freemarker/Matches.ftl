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
    <meta charset="utf-8">
    <title>Yoki | Your Matches</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="css/matching.css">
</head>
<body id="main">
<div id="profile">
    ${nav}
    <script src="script/matchUpdate.js"></script>
    <script src="script/lightDark.js"></script>
    <div id="match-div">
        <h1>Your Matches</h1>
        <div id="match-list">
            <p id="noMatchText">You have no matches!</p>
        </div>
    </div>
</body>
</html>
