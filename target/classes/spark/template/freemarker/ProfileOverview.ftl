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

<#assign save>
    <button class="interestBtn" id="match-button" onclick="save()">Save</button>
</#assign>

<#assign editProfile>
    <button id="match-button" class="request" onclick="">Edit Profile</button>
</#assign>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Profile</title>
    <link rel="stylesheet" href="css/profileOverview.css">
    <link rel="stylesheet" href="css/matching.css">
</head>
<script src="script/interestButtons.js"></script>
<script src="script/profileLoad.js"></script>
<script src="script/lightDark.js"></script>
<body>
<div id="profile">
    ${nav}
    <div id="main">
        <h2 class="page-title">My Profile</h2>
        <div id="side">
            <div id="profileTag">
                <div id="info">
                    <div id="user-info"></div>
                    <a href="/profileEdit">
                        <button id="match-button" class="edit" onclick="">Edit Profile</button>
                    </a>
                </div>
                <div id="picDiv"></div>
            </div>
            <div id="subjects">
                <div id="subjects-header">
                    <input type="text" id="search-bar" onkeyup="search()" placeholder="Search for ineterests to add..."
                           title="Type in an ineterest">
                </div>
                <div id="subjects-list">

                </div>
            </div>
        </div>

        <h2 id="title">Interests</h2>
        <div id="interestList">
        </div>
        ${save}
    </div>
</div>
</body>
</html>