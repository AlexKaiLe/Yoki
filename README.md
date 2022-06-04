# README

![](yellowLogo.png)

Languages and programs used:
- JavaSrcipt 
- Java
- HTML 
- CSS 
- Maven

## How to Build and Run

#### Tests
Run `mvn clean` first to clean. 
JUnit Tests: run `mvn package` or  `mvn test`
Our System Tests: run `./cs32-test tests/*.test` 

#### Program
Run `mvn clean` first to clean. 
Run `mvn package` to compile.
Then run `./run --gui 4567` from root project directory to run program with the GUI.
To view the GUI, navigate to `http://localhost:4567/yoki` in your local browser.
To exit, press `CMD+D`

## Project Overview
Our project is called Yoki, a web application that focuses on connecting people based on similar interests. 

### Complex Algorithm
We used a KDTree for our complex algorithm, in which each point in the tree represents a user's array of interest levels. In other words, with **n** total interests a user can choose from, each user will be associated with an array size **n**, where each index represents a unique interest category, and its values are the user's interest level for that specific interest. In addition, we use the KDTree create a sorted ArrayList of users with a special heuristic comparator our team designed. The heuristic comparator takes two users' interest level arraysand compares their values of the same interests categories one by one. We designed it so that, the smaller the gap is between two users' interest levels, the higher their sorting priority is. In addition, the higher the interestlevels of both users are, the higher their sorting priority is. With this, we were able to create a list of "best" matchesbetween a user and other users which is based off of similar interests. 

### Encryption
To ensure the safety and privacy protection of all of our users, we decided to implement encryptionfor user logs in and signs ups. We included a password encryption algorithm between the frontend and backend,as well as between the backend and database. This way, no outsiders can access the real value of the user's passwordwhen the different parts of our project communicate with one another. To create extra security, we made sure thatwe did not hardcode the cypher key in any of our project files. Instead, we have it stored in a separate `key.txt`file, which can only be accessed by a file reader. With the cypher key in a separate file, we will add it to `.gitignore` so that it will never be pushed to the repo and thus the public. (Note: For the purpose of TAgrading, the `key.txt` file will be added to the Github repository)

Lastly, the database will store all passwords as encrypted values. 

### Backend
#### REPL
The REPL instantiates all the command classes. Although our website does not use the REPL, we utilized it to individually test the important parts of our program such as encryption, interest reading, match finding, and data reading. This is important to implement for our system tests.
#### KDTree
Since the KDTree's main purpose is to provide the best structure for quicker sorting on a fixed dataset, we programmed the KDTree so that it is (re)built everytime a user updates their interest or when a new user is added to the database. This is because the KDTree is created based on the comparing individual dimension (or interests categories) for each depth/level in the tree, but since changing a user's interests gives the user a new "coordinate," the KDTree will not longer be the most efficient for nearest searched. Thus, we reconstruct the tree everytime a user's interest levels are modified. In addition, when a user does not specify their level of interest in a particular interest category, its default value will be 0.
#### Handlers
In order to communicate the database to the frontend, we created handlers for each page in our website as well as any essential functionalities for our application such as
- adding users (sign up)
- login authentication,
- add/remove/modify interests
- modify user's basic information (name, year, picture, bio, etc.)
- matching
- reporting
#### SQL Commands
As for our SQLCommands, we have all of them stored inside the SQLcommands class so that we can statically call any SQL command anywhere in the project without having to instantiate the class. Having all of our SQL commands in one class allowed us to better organize our code. The commands are often called from the Spark post and get handlers in Main.java to allow the frontend to receive information from the database and vice versa.

#### Database
We structured our database into four main tables:
- **user_data:** holds all the basic information of each user
- **user_interests:** contains the interest level on all interest categories of each user  
- **matches:** includes any matches/passes a user has done on another user
- **reports:** keeps track of all the user reports from a user  

We ensured that everytime a new row is added to each table, they are preset with default values to prevent any errors from occurring.

### Frontend
The front end is divided into 8 main pages as described below.

#### Login
If the user has not logged in during their session, they will be redirected to the login page no matter which page they try to visit (except the signup page). This prevents people from performing any  activity and potentially ruining user-specific logic without logging in first. In addition, if the user has not made an account yet, they can redirect themselves to the signup page.

#### Signup
The signup page only asks for basic information of the user, which are all required to be filled before signing up. In addition, the email is first checked if it ends with `@brown.edu` since we intended this application to only be used within the Brown community. In addition, the program checks if the specified email is already in the database before actually adding a new user to prevent duplicate users.

#### Main
The main page is where the user is able to match or pass a suggested user who they have the most common interests with. Each suggested user that is displayed will have most of their basic information showed as well as their top common interests with the current user.

#### Matches
The matches page includes a list of all the users that a user has clicked "match" on. If the user clicks on one of their matches, they are able to view all of their basic information, contact them, as well as unmatch with them. 

#### Profile Overview
The profile overview page displays all of the user's basic information and list of intersests. Here, the user is able to add/remove/modify any interests and save it to the database. Once the user saves any modifications on their interests, the KDTree of best matches will be constructed again in the back end. In addition, the user is able to navigate to the profile edit page.

#### Profile Edit
The profile edit page allows the user to modify all of their basic information as well as their profile picture. The images can be modified by a link to an online image. If the image is an invalid image, then the profile image will remain the same in the front end and database (error checking).

#### Settings
The settings page includes a log out function, which redirects the user to the login page and resets the backend's  user-specific logic. In additon, the user can view the terms and conditions, change between light and dark mode, and report other users. For the report section, the user is asked to input the email of the person that they are reporting and the program will check if the specified email exists (error checking).

Login page
![](https://github.com/AlexKaiLe/Yoki/blob/master/gifs/interests.gif)
