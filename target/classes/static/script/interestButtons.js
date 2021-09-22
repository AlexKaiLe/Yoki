const addInterest = new Map();
let all = new Map();

window.onload(allInterests());

function userInterests() {
    fetch('/profileInfo', {
        method: 'post',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .then((response) =>
            response.json())
        .then((data) => {
            for (const [key, value] of Object.entries(data.user.interests)) {
                if (value !== 0) {
                    addCurrentInterests(value, key);
                }

            }

        })
        .catch(function (error) {
            console.log(error);
        });
}

function addCurrentInterests(value, key) {
    key = parseInt(key);
    if (!addInterest.has(key)) {
        const interest = document.createElement("div");
        interest.className = "interests";
        interest.id = key + "remove";

        const nameButton = document.createElement("div");
        nameButton.id = "nameButton";
        nameButton.style.display = "flex";
        const name = document.createElement("h4");
        key += 11
        name.innerHTML = all.get(key + "").name;
        const remove = document.createElement("button");
        remove.className = "remove";
        remove.innerHTML = "Remove";

        const inputWrapper = document.createElement("div")
        inputWrapper.className = "input-wrapper"

        const input = document.createElement("input");
        input.className = "slider interestValue";
        input.id = key;
        input.type = "range";
        input.min = "0";
        input.max = "10";
        input.value = value;


        remove.onclick = function () {
            removeInterest(key);
        };

        inputWrapper.appendChild(input)
        interest.appendChild(nameButton);
        document.getElementById("interestList").appendChild(interest);
        nameButton.appendChild(name);
        nameButton.appendChild(remove);
        interest.appendChild(inputWrapper);
        addInterest.set(key + "", input.value);
    }
}


/**
 * Adds an interest to the user.
 * @param value for dictionary
 * @param key for dictionary
 */
function addInterestDiv(value, key) {
    key = parseInt(key);
    if (!addInterest.has(key)) {
        const interest = document.createElement("div");
        interest.className = "interests";
        interest.id = key + "remove";

        const nameButton = document.createElement("div");
        nameButton.id = "nameButton";
        nameButton.style.display = "flex";
        const name = document.createElement("h4");
        name.innerHTML = value.name;
        const remove = document.createElement("button");
        remove.className = "remove";
        remove.innerHTML = "Remove";

        const input = document.createElement("input");
        input.className = "slider interestValue";
        input.id = value.id;
        input.type = "range";
        input.min = "0";
        input.max = "10";
        input.value = "5";
        remove.onclick = function () {
            removeInterest(key);
        };

        interest.appendChild(nameButton);
        document.getElementById("interestList").appendChild(interest);
        nameButton.appendChild(name);
        nameButton.appendChild(remove);
        interest.appendChild(input);
        addInterest.set(key, input.value);
    }

}

/**
 * Makes the list of clickable buttons of the interests in the database
 */
function allInterests() {
    fetch('http://localhost:4567/listInterests', {
        method: 'post',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .then((response) =>
            response.json())
        .then((data) => {
            for (const [key, value] of Object.entries(data.interestsList)) {
                const interest = document.createElement("button");
                interest.addEventListener("click", () => addInterestDiv(value, key));
                interest.className = "subject";
                interest.id = key;
                interest.innerHTML = value.name;
                document.getElementById("subjects-list").appendChild(interest);
                all.set(key, value);
            }
            userInterests();
            return data;
        })
        .catch(function (error) {
            console.log(error);
        });
}

function search() {
    var input, filter, subjects, btns, a, i, txtValue;
    input = document.getElementById("search-bar");
    filter = input.value.toUpperCase();
    subjects = document.getElementById("subjects-list");
    btns = subjects.getElementsByTagName("button");
    for (i = 0; i < btns.length; i++) {
        txtValue = btns[i].innerText
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            btns[i].style.display = "";
        } else {
            btns[i].style.display = "none";
        }
    }
}

/**
 * Updates the interests based on the users input.
 */
function updateInterest() {
    for (let i of document.getElementsByClassName("interestValue")) {
        if (addInterest.get(i.id) != 0) {
            addInterest.set(i.id, i.value);
        }
    }
}

/**
 * Saves the interests of the user and sends it to the backend.
 */
function save() {
    updateInterest();
    const postParameters = {
        interests: Object.fromEntries(addInterest)
    };
    fetch('http://localhost:4567/updateInterests', {
        method: 'post',
        body: JSON.stringify(postParameters),
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .catch(function (error) {
            console.log(error);
        });
}

function removeInterest(key) {
    addInterest.set(key + "", 0);
    key = key - 11;
    const interest = document.getElementById(key + "remove");
    interest.remove();
}