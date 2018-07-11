# TQFTHPG

The quest for the holy pint glass.

Project description:
The project is a drinking game application designed to be played with between 3-8 players.  Players
 take it in turns to participate in 2 evemts each and must pick the best outcome for their characters
Then a results screen will tell players what to do.

External Libraries:
-Dagger2
-Butterknife
-Realm

Architecture:
This application is built using MVP architecture.  Each view has a presenter class and interactions are 
controlled via interfaces.

Model Classes
There are two object classes used within the application.  The first is Character which is used for the
Player characters.  The second is event which is used to create events for the users to solve.

Activities and fragments:
Each new screen is tepresented by its own individual activity and fragment.  The only exception is during
The main game loop where only one activity is used and this flips between 2 fragments.

Version history:
This is currently in its first implementation phase.  Future balance and gameplay improvements are required.
