# cs0320 Term Project 2021

**Team Members:**<br/>
Ian Acosta (iacosta1), Alex Le (ale22), Delora Li (dli65), Sage Matsushima (smatsush)

**Team Strengths and Weaknesses:** <br/>
Languages: Python, Java, HTML/CSS/Javascript, React.JS <br/>
Weaknesses: Concepts/Languages: Spark server (with React.JS), Caching, Docker


**Project Idea(s):** 
### Idea 1
_Interest Connection App_ <br/>
A platform on which people can connect with strangers with similar interests and hobbies. 
As a result of this pandemic, it has been hard to build meaningful connections with others. 
Bridging the gap between Facebook(virtual connections) and Omegle (meeting strangers) to 
build lasting relationships. Similar to datamatch, where each person contains attributes/interests 
where we can create edges between the people. Use the tree to find nearest neighbors/page rank. 
Create a mathematical formula to make sure that people are accurately matched with others. <br/>
Extra addons:<br/>
• Match people with others that want to teach them things<br/>
• Create study rooms for classes in which people have in common<br/>
• Have a video feed where you keep on swiping and talking to others<br/>
• Create a robust video chat feature<br/>
• Have a venter/listener feature<br/>
• Map number of connections you have with others<br/>

**HTA Approval (dpark20):** Idea approved - make sure your algorithm is complex!

### Idea 2 <br/>
_Schedule Concentration Planner_ <br/>
A website where students can log their classes and concentrations in, and they can see the 
overall difficulty of their semester, the classes they still need to take to meet their 
concentration, and which classes satisfy which requirements. They can also search up and add 
classes to their course load, seeing how different ones would impact their semester.
<br/>
We would need to parse information from Critical Review, looking at time required, difficulty, 
and overall rating on the course and on professors.

**HTA Approval (dpark20):** Idea not approved - not enough algorithmic complexity as of now. Feel free to add more details if you'd like to do this idea.

### Idea 3
_Marketplace App_ <br/>
Students can sign up and create an account to buy and sell items. A buyer can click on either
an “interested” button or “buy now” button. The seller will get a notification and they can 
message each other about how they will deal with their exchange. When both parties agree on a 
trade, both students can retrieve each other's information (which they can decide to show or not)
such as their name, email, dorm, mailing address, etc. Sellers are required to upload pictures 
of their items. In addition, the seller will have to specify how they want their payment to 
be made (i.e. cash, venmo, paypal, etc.) <br/>
When a buyer clicks on “interested” they item will be put on hold for a certain amount of 
time (probably a day) where they can talk about the item with the seller. Once the exchange 
is done, both the seller and buyer will be asked about their experience.

**HTA Approval (dpark20):** Idea not approved for same reason as above.

Good ideas - make sure to add more if you'd like to pursue Ideas 2 or 3. Good luck! :)

**Mentor TA:** _Put your mentor TA's name and email here once you're assigned one!_

## Meetings
_On your first meeting with your mentor TA, you should plan dates for at least the following meetings:_

**Specs, Mockup, and Design Meeting:** _(Schedule for on or before March 15)_

**4-Way Checkpoint:** _(Schedule for on or before April 5)_

**Adversary Checkpoint:** _(Schedule for on or before April 12 once you are assigned an adversary TA)_

## How to Build and Run

#### Tests
Run `mvn clean` first to clean. <br />
JUnit Tests: run `mvn package` or  `mvn test`<br />
Our System Tests: run `./cs32-test tests/*.test` <br />

#### Program
Run `mvn clean` first to clean. <br />
Run `mvn package` to compile. <br />
Then run `./run --gui 4567` from root project directory to run program with the GUI.
To view the GUI, navigate to `http://localhost:4567/yoki` in your local browser.
To exit, press `CMD+D`

## Project Overview
Our project is called Yoki, a web application that focuses on connecting people based on similar interests. 

### Complex Algorithm
We used a KDTree for our complex algorithm, in which each point in the tree represents a user's array of 
interest levels. In other words, with **n** total interests a user can choose from, each user will be associated with an 
array size **n**, where each index represents a unique interest category, and its values are the user's interest 
level for that specific interest. In addition, we use the KDTree create a sorted ArrayList of users with a 
special heuristic comparator our team designed. The heuristic comparator takes two users' interest level arrays
and compares their values of the same interests categories one by one. We designed it so that, the smaller the gap 
is between two users' interest levels, the higher their sorting priority is. In addition, the higher the interest
levels of both users are, the higher their sorting priority is. With this, we were able to create a list of "best" matches
between a user and other users which is based off of similar interests. 

### Encryption
To ensure the safety and privacy protection of all of our users, we decided to implement encryption
for user logs in and signs ups. We included a password encryption algorithm between the frontend and backend,
as well as between the backend and database. This way, no outsiders can access the real value of the user's password
when the different parts of our project communicate with one another. To create extra security, we made sure that
we did not hardcode the cypher key in any of our project files. Instead, we have it stored in a separate `key.txt`
file, which can only be accessed by a file reader. With the cypher key in a separate file, we will add it to 
`.gitignore` so that it will never be pushed to the repo and thus the public. (Note: For the purpose of TA
grading, the `key.txt` file will be added to the Github repository)

Lastly, the database will store all passwords as encrypted values. 


### Backend
#### REPL
The REPL instantiates all the command classes. Although our website does not use the
REPL, we utilized it to individually test the important parts of our program such as encryption,
interest reading, match finding, and data reading. This is important to implement for our system tests.
#### KDTree
Since the KDTree's main purpose is to provide the best structure for quicker sorting on a fixed dataset, 
we programmed the KDTree so that it is (re)built everytime a user updates their interest or when a 
new user is added to the database. This is because the KDTree is created based on the comparing individual
dimension (or interests categories) for each depth/level in the tree, but since changing a user's interests
gives the user a new "coordinate," the KDTree will not longer be the most efficient for nearest searched.
Thus, we reconstruct the tree everytime a user's interest levels are modified. In addition, when a user does
not specify their level of interest in a particular interest category, its default value will be 0.
#### Handlers
In order to communicate the database to the frontend, we created handlers for each page in our website
as well as any essential functionalities for our application such as
- adding users (sign up)
- login authentication,
- add/remove/modify interests
- modify user's basic information (name, year, picture, bio, etc.)
- matching
- reporting
#### SQL Commands
As for our SQLCommands, we have all of them stored inside the SQLcommands class so that
we can statically call any SQL command anywhere in the project without having to
instantiate the class. Having all of our SQL commands in one class allowed us to better
organize our code. The commands are often called from the Spark post and get handlers in Main.java
to allow the frontend to receive information from the database and vice versa.

#### Database
We structured our database into four main tables:
- **user_data:** holds all the basic information of each user
- **user_interests:** contains the interest level on all interest categories of each user  
- **matches:** includes any matches/passes a user has done on another user
- **reports:** keeps track of all the user reports from a user  

We ensured that everytime a new row is added to each table, they are preset with default 
values to prevent any errors from occurring.

### Frontend
The front end is divided into 8 main pages as described below.

#### Login
If the user has not logged in during their session, they will be redirected to the login
page no matter which page they try to visit (except the signup page). This prevents people from performing any 
activity and potentially ruining user-specific logic without logging in first. In addition, 
if the user has not made an account yet, they can redirect themselves to the signup page.
#### Signup
The signup page only asks for basic information of the user, which are all required to be filled before
signing up. In addition, the email is first checked if it ends with `@brown.edu` since we intended
this application to only be used within the Brown community. In addition, the program checks if the specified
email is already in the database before actually adding a new user to prevent duplicate users.
#### Main
The main page is where the user is able to match or pass a suggested user who they have the most common interests with.
Each suggested user that is displayed will have most of their basic information showed as well as their top common
interests with the current user.
#### Matches
The matches page includes a list of all the users that a user has clicked "match" on. If the user clicks
on one of their matches, they are able to view all of their basic information, contact them, as well as unmatch
with them. 
#### Profile Overview
The profile overview page displays all of the user's basic information and list of intersests. Here, the user is
able to add/remove/modify any interests and save it to the database. Once the user saves any modifications on their
interests, the KDTree of best matches will be constructed again in the back end. 

In addition, the user is able to navigate to the profile edit page.
#### Profile Edit
The profile edit page allows the user to modify all of their basic information as well as their profile picture.
The images can be modified by a link to an online image. If the image is an invalid image, then the profile image
will remain the same in the front end and database (error checking).
#### Settings
The settings page includes a log out function, which redirects the user to the login page and resets the backend's 
user-specific logic. In additon, the user can view the terms and conditions, change between light
and dark mode, and report other users. For the report section, the user is asked to input the email of the person
that they are reporting and the program will check if the specified email exists (error checking).