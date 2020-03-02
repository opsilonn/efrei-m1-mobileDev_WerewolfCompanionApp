# Werewolf Companion App'

Made as a project for the following :

* School : Efrei Paris - Semester 8 in Software Engineering
* Course : Mobile Development (Android)
* Goal : Design and build an Android Application.



## Werewolf Game

### What is it ?
It is a boardgame meant to be played by (ideally) at least 8 people ; the more players, the better. It takes place in a small village where each player has a given role that only he knows (he and no one else !).

But some werewolves are hidden amongst the civilians : these beasts take out a player each night. In response, each day, the village votes to kill one of its members. The game is won by the werewolves if all the civilians are killed, or by the civilians if all the werewolves are killed.

In addition to that, some other roles can be played, which impact the game in
different ways (to name a few : Cupidon, the Witch, the Soothsayer…)


Here is the link to their website : https://www.loups-garous-en-ligne.com/


### How a Mobile App' could help ?
There a few things required to play the game efficiently : knowledge of the game, obviously, and some equipment (aka some cards with a role written on it), to give randomly to each player. The Game Master can also try to remember everyone’s role, but this is never a good idea.

An app’ would solve both issues, and greatly help the Game Master. All role’s data can be displayed at any time, and allows him to easily create games with any selection of character he wants, and keep track of which player plays which role.


### How to use the app'
IMPORTANT : the project is meant to HELP organize and follow a game, not to direct players likes heeps.

When launching the App', you have 3 choices : 
* Create a Party by entering the names of the players and choosing the roles available
* See the rules, to familiarize yourself with the concept and the different roles
* Change the language of the App' : English and French

If you create a party, the app' will automatically give each player role randomly (more on that on the API section).
Afterwards, it will help the Game Master organise the game by keeping track of who play which role, and whose players turn it is to play.



## The Project

### Getting Started
The programmation is fully completed !
You may only only need to fulfill one task (AndroidStudio will automatically warn you of it, so don't worry) :

```
The location of the Android SDK is set according to my own path, with will obviously vary with yours.
Worry not : Android Studio will automatically warn you of it, and propose you to change the path to the one you have.
Now, it's all set and done ! you can use the app freely ! :D
```


### The Structure
I won't get too technical, but the project works following two axess :

* organise data : I have a big, big BIG Enumeration containing all the Roles (that a player can incarnate). Each iteration points to the according Strings (name, description), Drawable (picture, icon)... Same, I have some classes to represent players and Party.
* UI : I have a total of 5 Activities (one of them being dedicated to communicating to the API) and 6 Fragments. They implement a large array of Widgets (including Recycler Views : there's quite a lot of them).


### The API
lorem ipsum dolor amet...




## Built With

* [Android Studio 3.5.3]https://developer.android.com/studio/) - The IDE used to develop the app'
* [Android SDK 26.1.1](https://developer.android.com/studio/) - The Android SDK used (same link)
* Api level 29: Android 10.0 (Q) revision 4 - The development environment used
* Pixel 2 API 29 - The Android Studio's emulator used for the tests




## Authors

It was made by the following Efrei Paris students :
* **BEGEOT Hugues** - [his Git repository](https://github.com/opsilonn)

See also the list of [contributors](https://github.com/opsilonn/mobileDev_WerewolfCompanionApp/contributors) who participated in this project.

Note : made in the 4th year of Software Engineering cursus (1st year of Master).



## Acknowledgments
:)
