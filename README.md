# Werewolf Companion App'

Made as a project for the following :

* School : Efrei Paris - Semester 8 in Software Engineering
* Course : Mobile Development (Android)
* Goal : Design and build an Android Application.

![Loup-Garou's icon](https://github.com/opsilonn/efrei-m1-mobileDev_WerewolfCompanionApp/blob/master/Werewolf_Companion_App'/app/src/main/res/drawable/im_werewolf.png)


## Summary
**[Werewolf Game](https://github.com/opsilonn/efrei-m1-mobileDev_WerewolfCompanionApp#werewolf-game)**

[What is it ?](https://github.com/opsilonn/efrei-m1-mobileDev_WerewolfCompanionApp#what-is-it-)

[How a Mobile App' could help ?](https://github.com/opsilonn/efrei-m1-mobileDev_WerewolfCompanionApp#how-a-mobile-app-could-help-)

[How to use the App'](https://github.com/opsilonn/efrei-m1-mobileDev_WerewolfCompanionApp#how-to-use-the-app)


**[The Project](https://github.com/opsilonn/efrei-m1-mobileDev_WerewolfCompanionApp#the-project)**

[Getting Started](https://github.com/opsilonn/efrei-m1-mobileDev_WerewolfCompanionApp#getting-started)

[The Structure](https://github.com/opsilonn/efrei-m1-mobileDev_WerewolfCompanionApp#the-structure)

[The API](https://github.com/opsilonn/efrei-m1-mobileDev_WerewolfCompanionApp#the-api)


**[Technical round-up](https://github.com/opsilonn/efrei-m1-mobileDev_WerewolfCompanionApp#technical-round-up)**


**[Authors](https://github.com/opsilonn/efrei-m1-mobileDev_WerewolfCompanionApp#authors)**


**[Acknowledgments](https://github.com/opsilonn/efrei-m1-mobileDev_WerewolfCompanionApp#acknowledgments)**




## Werewolf Game

### What is it ?
It is a boardgame meant to be played by (ideally) at least 8 people ; the more players, the better. It takes place in a small village where each player has a given role that only he knows (he and no one else !).

But some werewolves are hidden amongst the civilians : these beasts take out a player each night. In response, each day, the village votes to kill one of its members. The game is won by the werewolves if all the civilians are killed, or by the civilians if all the werewolves are killed.

In addition to that, some other roles can be played, which impact the game in
different ways (to name a few : Cupidon, the Witch, the Soothsayer…)


Here is the link to [the official website](https://www.loups-garous-en-ligne.com)


### How a Mobile App' could help ?
There a few things required to play the game efficiently : knowledge of the game, obviously, and some equipment (aka some cards with a role written on it), to give randomly to each player. The Game Master can also try to remember everyone’s role, but this is never a good idea.

An app’ would solve both issues, and greatly help the Game Master. All role’s data can be displayed at any time, and allows him to easily create games with any selection of character he wants, and keep track of which player plays which role.


### How to use the app'
IMPORTANT : the project is meant to HELP organize and follow a game, not to direct players likes sheeps.

When launching the App', you have 3 choices : 
* Create a Party by entering the names of the players and choosing the roles available
* See the rules, to familiarize yourself with the concept and the different roles
* Change the language of the App' : English and French

If you create a party, the app' will automatically give each player role randomly (more on that on the API section).
Afterwards, it will help the Game Master organise the game by keeping track of who play which role, and whose players turn it is to play.




## The Project

### Getting Started
The programmation is fully completed !
You may only only need to fulfill one task :

```
The location of the Android SDK is set according to my own path, which will obviously vary with yours.
Worry not : Android Studio will automatically warn you of it,
and propose you to change the path to the one you have.

Now, it's all set and done ! you can use the app freely ! :D
```


### The Structure
I won't get too technical, but the project works following two axes :

* organise data : I have a big, big, BIG Enumeration containing all the Roles (that a player can incarnate). Each iteration points to the according Strings (name, description), Drawable (picture, icon)... In the same way, I have some classes to represent Players and Party.
* UI : I have a total of 5 Activities (one of them being dedicated to communicating with the API) and 6 Fragments. They implement a large array of Widgets (including Recycler Views : there's quite a lot of them).


### The API
The project is done using the [Deck of Cards API](https://www.deckofcardsapi.com).


#### What does it do ?
It allows to create deck (one, or a given number of deck), which will remain accessible for 2 weeks.
From theses decks, we can draw cards, create hands, shuffle... all that you could hope for with a card API.


#### How do I use it ?
Let's say you create a party with X players.
At this point in the App', you'd have entered X players and selected X roles, but you won't assign them manually ;
it is the API which will randomly assign a Role to each Player.

I use it in 3 steps :
- I draw X cards from a newly generated deck ; I assign each card with a Role.
- I create aspecific deck with these X cards.
- Foreach player, I draw one card from the deck, and assign the role corresponding role to the Player.


And it's done !!




## Technical round-up

* [Android Studio 3.5.3](https://developer.android.com/studio/) - The IDE used to develop the app'
* [Android SDK 26.1.1](https://developer.android.com/studio/) - The Android SDK used (same link)
* Api level 29: Android 10.0 (Q) revision 4 - The development environment used
* Pixel 2 API 29 - The Android Studio's emulator used for the tests




## Authors

It was made by the following Efrei Paris students :
* **BEGEOT Hugues** - [his Git repository](https://github.com/opsilonn)

See also the list of [contributors](https://github.com/opsilonn/efrei-m1-mobileDev_WerewolfCompanionApp/contributors) who participated in this project.

Note : made in the 4th year of Software Engineering cursus (1st year of Master).



## Acknowledgments
:)
