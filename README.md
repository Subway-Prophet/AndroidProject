# Pointy Words
We would like a multiplayer (eventually local and online) game that relies on both diplomacy and military prowess. 
The game is meant to replicate the diplomatic tension from games like Conspiracy,
while having the edge-of-your-seat kind of combat present in a strategy game like Risk. 
The turn based system allows for a relaxed style of play while still maintaining rabid interest of the players, 
having them constantly think about and rethink their every choice.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 
See deployment for notes on how to deploy the project on a live system.

## Prerequisites
- Java
- Android Studio

## Installing
After Cloning your copy of the program you will need to add firebase to the project, follow these steps in android studio:
- Click tools in the toolbar, hit firebase
- Then scroll down to Firestore and expand it and click the blue link "read and write..."
- Click the button in #1 and sign in with the your android development account,
- In the "Connect to Firebase" dialog that shows up you will need to select "create new..."
- Then hit "Add Cloud Firestore to your app" in #2 and accept the dependencies

At this point you will need to add the Auth dependencies. You can do so by going back to tools, Firebase, and then hitting "Authentication", and clicking on the linked dialog. Then hit the button in #2.

Lastly you will need to setup the database, and authentication though the firebase consol.
- Go to https://console.firebase.google.com and login with your development account
- On the left side click on "Database" and then the "Create Database" button in the middle of the screen
- We are currently using "test mode" though you could use locked mode if you wanted to setup the security rules

## Deployment
You will need two devices who both have the current version of the app.

## Built With
- Android Studio
- Google Play
- Firebase

## Contributing
- Please Don't

## Authors
Der Mannschaftskapitän William Grim,
Wachtmeister Jason Baldwin,
Vizefeldwebel Eli Slothower,
Und, Unteroffizier Levy Ford IV

## License
*Scoffs*

## Acknowledgments
Hat tip to Mr. Linburg
Inspiration from Paradox Interactive, Conspiracy, Risk, and Diplomacy
Thanks to the country of Ireland
