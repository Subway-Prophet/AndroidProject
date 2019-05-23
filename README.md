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

## Installation and Setup
After Cloning your copy of the program you will need to integrat Cloud Firestore and Authentication through Firebase.

### Adding Firestore in Android Studio
1. Click tools in the toolbar, hit firebase
2. Then scroll down to Firestore and expand it and click the blue link "read and write..."
3 Click the button in #1 and sign in with the your android development account,
4. In the "Connect to Firebase" dialog that shows up you will need to select "create new..."
5. Then hit "Add Cloud Firestore to your app" in #2 and accept the dependencies

### Adding auth in Android Stuido
1. Click tools in the toolbar, hit Firebase
2. Click "Authentication" and click the blue link "read and write"
3. Click on the button in #2

### Setup Firestore in the Firebase console
1. Go to https://console.firebase.google.com and login with your development account
2  On the left side click on "Database" and then the "Create Database" button in the middle of the screen
3. We are currently using "test mode" though you could use locked mode if you wanted to, though you will need to setup the security rules

*This is all the setup that is needed. The app will automaticaly build the database structure once it runs*

### Setup Authentication in the Firebase console
1. Click "Sign-in Method" on the top bar
2. Click "Google"
3. Click the "Enable" switch and input a project name.
4 Hit save.

You should now be done with the basic setup.

## Deployment
You will need two devices who both have the current version of the app and working Google accounts.

## Built With
- Android Studio
- Google Play
- Firebase
  -  Cloud Firestore
  -  Authentication

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
