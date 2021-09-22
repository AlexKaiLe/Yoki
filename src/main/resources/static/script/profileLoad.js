const interest = document.getElementsByClassName('slider');

window.onload(getUserInfo());

/**
 * Retrieves all of the user's basic information
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
            loadInfoDiv(data.user);
            getPicture(data.user);
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * Updates the new user information on the website
 * @param user
 */
function loadInfoDiv(user) {
    const info = document.getElementById("user-info");
    const name = document.createElement("h4");
    name.innerText = user.firstName;
    const year = document.createElement("h5");
    year.innerText = user.year;
    const concentration = document.createElement("h5");
    concentration.innerText = user.major;

    info.append(name);
    info.append(year);
    info.append(concentration);
}

/**
 * Displays the user's profile picture
 * @param user
 */
function getPicture(user) {
    const picture = document.createElement("img");
    picture.id = "picture"
    picture.src = user.images;

    document.getElementById("picDiv").append(picture);
}