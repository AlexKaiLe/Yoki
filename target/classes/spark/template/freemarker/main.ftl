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

<#assign card>
    <script src="script/matchHandler.js"></script>
    <script src="script/lightDark.js"></script>
    <div id="potential_card">
        <div id="card_content">
            <div id="left-content">
                <div id="name_info">
                    <h1 id="match-name"></h1>
                    <i><h5 id="match-major"></h5></i>
                    <h5 id="match-grade"></h5>
                </div>
                <div id="top_interests">
                    <h3>Common Interests</h3>
                    <ul id="top_interests_list">
                    </ul>
                </div>
            </div>
            <div id="right-content">
                <img id="match_image" src="">
            </div>
        </div>
        <div id="no-matches-msg">
            No Matches Left!
        </div>
    </div>
</#assign>

<#assign matchButton>
    <button class="button" id="match-button" onclick="onMatchPressed(true)">
        match
    </button>
</#assign>

<#assign passButton>
    <button class="button" id="pass-button" onclick="onMatchPressed(false)">
        pass
    </button>
</#assign>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Yoki</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="css/matching.css">
</head>
<body>
<div id="profile">
    ${nav}
    <script src="script/matchHandler.js"></script>
    <div id="card-div">
        ${card}
        <div id="button-div">
            ${passButton}
            ${matchButton}
        </div>
    </div>
</div>
</body>
</html>
