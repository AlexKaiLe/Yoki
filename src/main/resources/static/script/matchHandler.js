let matchMap = new Map();
let currUser;
window.onload = getNextMatch();

/**
 * Moves to the next match when the button is pressed.
 * @param isMatch the next match
 */
function onMatchPressed(isMatch) {
    getNextMatch();
    matchMap.set(currUser.firstName, currUser);
    setBackMatch(isMatch);
}

/**
 * Post request that sends the match's information to the backend.
 * @param isMatch the match
 */
function setBackMatch(isMatch) {
    const postParameters = {
        id: currUser.id,
        first: currUser.firstName,
        last: currUser.lastName,
        isMatch: isMatch
    };

    fetch('http://localhost:4567/sendmatch', {
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

/**
 * Post request that gets the next match from te kd tree.
 */
function getNextMatch() {
    fetch('http://localhost:4567/yokimatch', {
        method: 'get',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
        },
    })
        .then((response) =>
            response.json())
        .then((data) => {
            if (data != null) {
                document.getElementById("no-matches-msg").style.display = "none";
                document.getElementById("card_content").style.display = "flex";

                let matchImage = document.getElementById("match_image");
                matchImage.style.opacity = 100;

                let matchName = document.getElementById('match-name');
                let matchGrade = document.getElementById('match-grade');
                let topInterests = document.getElementById('top_interests_list')
                let matchMajor = document.getElementById('match-major')

                matchName.innerHTML = data.user.firstName;
                matchGrade.innerHTML = "Class of " + data.user.year;
                topInterests.innerHTML = ""
                matchMajor.innerHTML = data.user.major;
                currUser = data.user;
                for (var i in data.topCommonInterests) {
                    let interest = data.topCommonInterests[i]
                    let intDiv = '<div className="interest"><ul>' + interest.name + '</ul>'
                        + '<progress className="interestBar" value="0" max="10"></progress></div>';
                    topInterests.innerHTML += intDiv;
                }

                let matchList = document.getElementById('match-list');
                Object.keys(matchMap).map(function (key) {
                    matchList.innerHTML = "<li> " + matchMap[key].firstName + " </li>";
                });

                let progressBars = document.getElementsByTagName('progress');
                for (i = 0; i < progressBars.length; i++) {
                    move(progressBars[i], data.topCommonInterests[i].score)
                }

                matchImage.alt = "image of " + data.user.firstName;
                matchImage.src = data.user.images;
                return data;
            } else {
                document.getElementById("no-matches-msg").style.display = "block";
                document.getElementById("card_content").style.display = "none";
            }
        })
        .catch(function (error) {
            console.log(error);
        });
}

/**
 * Animates the progress bar for interests.
 * @param progressBar the bar itself.
 * @param interestScore the value of the bar.
 */
const move = (progressBar, interestScore) => {
    progressBar.value = 0
    let i = 0;
    if (i == 0) {
        i = 1;
        var width = 0;
        var id = setInterval(frame, 10);

        function frame() {
            if (width >= interestScore) {
                clearInterval(id);
                i = 0;
            } else {
                width += 0.1;
                progressBar.value = width
            }
        }
    }
}