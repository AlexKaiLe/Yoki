window.onload(getUserInfo());

/**
 * Gets the User's information from the backend.
 */
function getUserInfo() {
    fetch('/profileInfo', {
        method: 'post',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .then((response) =>
            response.json())
        .then((data) => {
            setUser(data.user);
            getPicture(data.user);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * Sets the users information when the page first loads in the text boxes.
 * @param user
 */
function setUser(user) {
    document.getElementById("firstInput").value = user.firstName;
    document.getElementById("lastInput").value = user.lastName;
    document.getElementById("majorInput").value = user.major;
    document.getElementById("gradYearInput").value = user.year;
    document.getElementById("emailInput").value = user.email;
    document.getElementById("bioBox").innerText = user.bio;
}

/**
 * Post request to update the user's information based off of the text boxes.
 */
function updateProfile() {
    updatePic();
}

function postUpdate() {
    const postParameters = {
        firstName: document.getElementById("firstInput").value,
        lastName: document.getElementById("lastInput").value,
        major: document.getElementById("majorInput").value,
        year: document.getElementById("gradYearInput").value,
        email: document.getElementById("emailInput").value,
        bio: document.getElementById("bioBox").value,
        image: document.getElementById("picture").src
    };
    fetch('http://localhost:4567/updateUser', {
        method: 'post',
        body: JSON.stringify(postParameters),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })  .then((response) =>
        response.json())
        .then((data) => {
            window.location.href = "/profileEdit";
        })
        .catch(function (error) {
            console.log(error);
        });
}
/**
 * Gets the user's picture from the backend.
 * @param user info
 */
function getPicture(user) {
    const div = document.getElementById("picDiv");
    div.id = "div";
    const picture = document.createElement("img");
    picture.id = "picture"
    picture.src = user.images;

    const change = document.createElement("button");
    change.id = "match-button";
    change.className = "changePic";
    const pic = document.createElement("img");
    pic.src = "images/camera.png"
    pic.id = "camera";
    change.append(pic);
    picture.append(change);
    change.onclick = function() { inputText(user); };

    const saveButton = document.createElement("button");
    saveButton.className = "save";
    saveButton.innerText = "Save";
    saveButton.id = "match-button";
    saveButton.onclick = function() { updateProfile(); };

    div.append(change);
    div.append(picture);
    div.append(saveButton);
}

/**
 * Updates the users picture from the textbox entry.
 */
function updatePic() {
    const text = document.getElementById("text");
    if (document.getElementById("picture") != null && text != null) {
        var oldImgLink = document.getElementById("picture").src;

        var im = document.getElementById('picture');
        im.onerror = function(){
            // image not found or change src like this as default image:
            im.src = oldImgLink;
            alert("invalid image link!")
        };
        im.onload = function(){
            postUpdate();
        };
        im.src = text.value
    } else {
        postUpdate();
    }
}

/**
 * Updates the user's picture.
 * @param user info
 */
function inputText(user) {
    const inputElement = document.getElementById("text");
    if (inputElement !== null) {
        inputElement.remove();
    }

    const picture = document.getElementById("div");
    const text = document.createElement("input");
    text.id = "text";
    text.value = user.images;
    picture.src = text.value;
    picture.append(text);
}

