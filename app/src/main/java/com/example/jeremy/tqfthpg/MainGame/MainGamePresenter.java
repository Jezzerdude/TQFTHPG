package com.example.jeremy.tqfthpg.MainGame;

import android.app.Fragment;
import android.util.Log;

import com.example.jeremy.tqfthpg.CharacterScreen.Model.PCharacter;
import com.example.jeremy.tqfthpg.MainGame.Model2.Events;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainGamePresenter implements MainGameInterface.MainPresenterInterface{

    private MainGameFragment mainGameFrag;
    private MainGameFragment2 mainGameFrag2;
    Realm realm;
    private int[] UsedEvents = new int[16];

    @Override
    public MainGameFragment getFragment() {
        mainGameFrag = new MainGameFragment();
        return mainGameFrag;
    }

    @Override
    public MainGameFragment2 getFragment2() {
        mainGameFrag2 = new MainGameFragment2();
        return mainGameFrag2;
    }

    @Override
    public Events[] AddEvents(PCharacter[] players,int EventsNeeded) {
        Events[] eventList = new Events[18];
        int MaxPlayers = players.length;
        Events[] questList = new Events[EventsNeeded];
        int[] Leads= GenLeadChar(MaxPlayers, EventsNeeded);


        Events startingTown = new Events(0,"startingTown","Single","R.drawable.volcano","You Leave the town to start your adventure!","Travel onwards: ",players[0].getId(),"");
        Events volcano = new Events(1,"Volcano","Multiple","@drawable/volcano","A towering Volcano appears before you. As you trek down the ssulfurous path you are blocked by a stream of lava. What will you do?","Build a bridge: ","Climb around it: ","Jump the lava: ",players[0].getId(),"","NA");
        Events lightning = new Events(2,"Lightning Strike","Multiple","@drawable/volcano","A vicious storm has broken out in front of you. Lightning repeatedly strikes the ground leaving small smoking piles.  How will you proceed?","Run through: ","Build a lighting Rod: ","Find an alternate route: ",players[0].getId(),"","NA");
        Events rocks = new Events(3,"Rock Slide","Multiple","@drawable/volcano","Your attention is caught by a sudden rock slide.  As the bolders hurtle torwards you, you:","Dodge the rocks: ","Smash through the rocks: ","Take cover: ",players[0].getId(),"","NA");
        Events cliff = new Events(4,"Cliff walk","Multiple","@drawable/volcano","A perilous cliff walk with a small path lies ahead, what should you do?","Navigate the cliffs: ","Tunnel through: ","Find an alternate route: ",players[0].getId(),"","NA");
        Events BarAntics = new Events(5,"Bar Antics","Multiple","@drawable/volcano","Along your way you stumble into a tavern, what would you like to do?","Order a few drinks: ","Get involved in a small poker game going on in the corner: ","Socialise with the locals: ",players[0].getId(),"","NA");
        Events Djinn = new Events(6,"The Djinn","Multiple","@drawable/volcano","A mystical Djinn appears before you and grants you 1 wish.  What do you wish for?","Wish for Power: ","Wish for wealth: ","Wish for alcohol: ",players[0].getId(),"","NA");
        Events Coaster = new Events(7,"The Holy Coaster","Multiple","@drawable/volcano","You find the Holy Coaster, an artifact long thought lost to the ages! What will you do with it?","Attempt to use its ancient power: ","Keep it: ","Discard it: ",players[0].getId(),"","NA");
        Events RogueSquirrel = new Events(8,"Attacked by a rogue Squirrel","Multiple","@drawable/volcano","A rogue squirrel attacks you. What do you do?","Fight: ","Attempt to negotiate: ","Flee: ",players[0].getId(),"","NA");
        Events BanditAttack = new Events(9,"Bandit attack","Multiple","@drawable/volcano","A small group of bandits attack you. What do you do?","Fight: ","Attempt to negotiate: ","Flee: ",players[0].getId(),"","NA");
        Events TheEvilKnight = new Events(10,"Attacked by the Evil Knight","Multiple","@drawable/volcano","The Dreaded Evil Knight appears and attacks you. What do you do?","Fight: ","Attempt to negotiate: ","Flee: ",players[0].getId(),"","NA");
        Events SwarmOfBeetles = new Events(11,"Attacked by a Swarm of Beetles","Multiple","@drawable/volcano","A swarm of beetles attack you. What do you do?","Fight: ","Attempt to negotiate: ","Flee: ",players[0].getId(),"","NA");
        Events RopeBridge = new Events(1,"Crossing the rope bridge","Multiple","@drawable/volcano","You find yourself at a wide ravine with a rope bridge going across it. It dosen't look very safe.  What do you do?","Just cross the bridge: ","Climb down the ravine: ","Pray to the spirits for guidance and cross carefully: ",players[0].getId(),"","NA");
        Events TheDeep = new Events(1,"Entering the Deep","Multiple","@drawable/volcano","A single path heads down into the Deep. The Deep is known to be full of dangers, (Vampire bats and trolls), but it would speed up your journey considerably.","Enter the Deep: ","Leave the Path and find a safer route: ","Loudly exclaim to the party that they: Shall Not Pass! ",players[0].getId(),"","NA");

        Events Injury = new Events(18,"Injury","SingleWithPlayer","@drawable/volcano","you have taken a crippling Injury.","Someone help me!: ",players[0].getId(),"Drink! 6");
        Events Sickness = new Events(18,"Sickness","SingleWithPlayer","@drawable/volcano","you appear to have gone down with a nasty sickness.","Consult the medical manual: ",players[0].getId(),"Drink! 6");
        Events CurseOfThePlatypus = new Events(18,"Curse of the Platypus!","SingleWithPlayer","@drawable/volcano","you seem to have been afflicted with the dreaded Curse of the Platypus!  You have been transformed into a Platypus.","Somebody do something! ",players[0].getId(),"Drink! 6");


        Events THPG = new Events(18,"THPG!","Single","@drawable/volcano","The Holy Pint Glass stands before you!","Take the grail: ",players[0].getId(),"Drink! 6");

        eventList[0] = startingTown;
        eventList[1] = volcano;
        eventList[2] = lightning;
        eventList[3] = rocks;
        eventList[4] = cliff;
        eventList[5] = BarAntics;
        eventList[6] = Djinn;
        eventList[7] = Coaster;
        eventList[8] = RogueSquirrel;
        eventList[9] = BanditAttack;
        eventList[10] = TheEvilKnight;
        eventList[11] = SwarmOfBeetles;
        eventList[12] = RopeBridge;
        eventList[13] = TheDeep;
        eventList[14] = Injury;
        eventList[15] = Sickness;
        eventList[16] = CurseOfThePlatypus;
        eventList[17] = THPG;

        boolean used = false;
        int Counter = 0;

        for (int i = 0; i < UsedEvents.length; i++) {
            UsedEvents[i]=0;
        }

        for(int i=0;i<questList.length;i++){
            int rand = (int) (Math.random() * ((16 - 1) + 1)) + 1;
            if (UsedEvents[i] != 0) {
                Counter++;
            }
            if(i>0 && i<questList.length-1) {
                for (int u = 0; u < UsedEvents.length; u++) {
                    if (rand == UsedEvents[u]) {
                        used = true;
                        u = UsedEvents.length;
                    } else {
                        used = false;
                    }
                }
            }

            if(i==0){
                eventList[i].setLeadChar(Leads[i]);
                questList[i]=eventList[i];
                UsedEvents[i]=99;
                Counter++;
            }else if(i==(questList.length-1)){
                questList[i]=eventList[17];
                questList[i].setLeadChar(Leads[i-2]);
                questList[i].setEventId(i);
            }else if(!used){
                eventList[rand].setLeadChar(Leads[i-1]);
                eventList[rand].setEventId(i);
                questList[i]=eventList[rand];
                UsedEvents[Counter] = rand;
                Counter++;
            }else{
                i--;
            }
        }

        return questList;
    }

    @Override
    public PCharacter[] getPlayers(int PlayerNo) {
        realm = Realm.getDefaultInstance();
        PCharacter[] playerList = new PCharacter[PlayerNo];
        for(int i=0; i<PlayerNo;i++){
            //Query
            PCharacter result = realm.where(PCharacter.class)
                    .equalTo("Id", i)
                    .findFirst();

            playerList[i] = result;
        }
        return playerList;
    }

    @Override
    public int[] GenLeadChar(int PlayerNo, int EventsNeeded) {
        int[] leadCharsList = new int[EventsNeeded-2];
        int[] CharsPerEvent = new int[PlayerNo];
        int Temp = PlayerNo;

        for(int i = 0; i<leadCharsList.length;i++) {
            int rand = (int) (Math.random() * PlayerNo);
            if ((CharsPerEvent[rand]<2 && rand!=Temp) || (CharsPerEvent[rand]<2 && i==leadCharsList.length-1)) {
                leadCharsList[i] = rand;
                CharsPerEvent[rand] = CharsPerEvent[rand]+1;
                Temp = rand;
            }else{
                i--;
            }
        }

        return leadCharsList;
    }

    @Override
    public void SaveEvents(Events[] events) {
        realm = Realm.getDefaultInstance();
        for(int i = 0;i<events.length;i++){
            realm.beginTransaction();
            Events events1 = realm.createObject(Events.class);
            events1.setEventId(events[i].getEventId());
            events1.setName(events[i].getName());
            events1.setEventType(events[i].getEventType());
            events1.setImgURL(events[i].getImgURL());
            events1.setDescription(events[i].getDescription());
            events1.setAct1(events[i].getAct1());
            events1.setAct2(events[i].getAct2());
            events1.setAct3(events[i].getAct3());
            events1.setLeadChar(events[i].getLeadChar());
            events1.setEventResult(events[i].getEventResult());
            events1.setPassOrFail(events[i].getPassOrFail());
            realm.commitTransaction();
            Log.d("Events: ", "Event added!");
        }
    }

    @Override
    public void DeleteEvents() {
        RealmResults<Events> result = realm.where(Events.class).findAll();;
        realm.beginTransaction();
        result.deleteAllFromRealm();
        realm.commitTransaction();
        Log.e("Events: ","Events Deleted!");
    }

    @Override
    public Events[] getEvents(int MaxEvents) {
        realm = Realm.getDefaultInstance();
        Events[] eventList = new Events[MaxEvents];
        for(int i=0; i<MaxEvents;i++){
            //Query
            Events result = realm.where(Events.class)
                    .equalTo("EventId", i)
                    .findFirst();

            eventList[i] = result;
        }
        return eventList;
    }

    @Override
    public String rollResult(Events activeEvent, String Option,PCharacter[] Players) {
        int rand = (int) (Math.random() * ((100 - 1) + 1)) + 1;
        int Modifier = 0;

        if(activeEvent.getName().equals("Volcano")&& Option.equals("option1")){
            String PClass = Players[activeEvent.getLeadChar()].getCharclass();

            if(PClass.equals("Cannoneer")||PClass.equals("Fighter")||PClass.equals("Ranger")){
                Modifier+=20;
            }
            if(PClass.equals("Tinkerer")){
                Modifier+=40;
            }
            if(PClass.equals("Spiritualist")){
                Modifier-=20;
            }

        }
        if(activeEvent.getName().equals("Volcano")&& Option.equals("option2")){
            String Race = Players[activeEvent.getLeadChar()].getRace();
            String PClass = Players[activeEvent.getLeadChar()].getCharclass();

            if(PClass.equals("Fighter")||PClass.equals("Ranger")||Race.equals("Goblin")||Race.equals("Changeling")||Race.equals("Ogre")){
                Modifier+=20;
            }
            if(Race.equals("Wolfborn")){
                Modifier-=20;
            }
        }
        if(activeEvent.getName().equals("Volcano")&& Option.equals("option3")){
            String Race = Players[activeEvent.getLeadChar()].getRace();
            String PClass = Players[activeEvent.getLeadChar()].getCharclass();
            String Weakness = Players[activeEvent.getLeadChar()].getWeakness();

            if(Weakness.equals("Lava")){
                Modifier-=70;
            }

            if(PClass.equals("Wizard")||Race.equals("Wolfborn")||Race.equals("Ogre")||Race.equals("Changeling")||Race.equals("Elf")){
                Modifier+=20;
            }

            if(PClass.equals("Cannoneer")||Race.equals("Goblin")||Race.equals("Dwarf")){
                Modifier-=20;
            }
        }

        //------------------------------------------------------------------------------------------

        if(activeEvent.getName().equals("Lightning Strike")&& Option.equals("option1")){
            String Race = Players[activeEvent.getLeadChar()].getRace();
            String PClass = Players[activeEvent.getLeadChar()].getCharclass();

            if(PClass.equals("Thief")||PClass.equals("Fighter")||Race.equals("Wolfborn")||Race.equals("Goblin")||Race.equals("Elf")){
                Modifier+=20;
            }

            if(PClass.equals("Wizard")){
                Modifier+=50;
            }

            if(Race.equals("Ogre")||Race.equals("Dwarf")){
                Modifier-=20;
            }
        }
        if(activeEvent.getName().equals("Lightning Strike")&& Option.equals("option2")){
            String Race = Players[activeEvent.getLeadChar()].getRace();
            String PClass = Players[activeEvent.getLeadChar()].getCharclass();

            if(Race.equals("Dwarf")){
                Modifier+=20;
            }

            if(PClass.equals("Tinkerer")){
                Modifier+=50;
            }

            if(PClass.equals("Spiritualist")||Race.equals("Lizard")||Race.equals("Ogre")){
                Modifier-=20;
            }
        }
        if(activeEvent.getName().equals("Lightning Strike")&& Option.equals("option3")){
            String PClass = Players[activeEvent.getLeadChar()].getCharclass();

            if(PClass.equals("Ranger")){
                Modifier+=20;
            }

        }

        //------------------------------------------------------------------------------------------

        if(activeEvent.getName().equals("Rock Slide")&& Option.equals("option1")){
            String Race = Players[activeEvent.getLeadChar()].getRace();
            String PClass = Players[activeEvent.getLeadChar()].getCharclass();

            if(Race.equals("Elf")||Race.equals("Goblin")||PClass.equals("Thief")){
                Modifier+=20;
            }

            if(PClass.equals("Cannoneer")||Race.equals("Ogre")){
                Modifier-=20;
            }
        }
        if(activeEvent.getName().equals("Rock Slide")&& Option.equals("option2")){
            String Race = Players[activeEvent.getLeadChar()].getRace();
            String PClass = Players[activeEvent.getLeadChar()].getCharclass();
            String Weakness = Players[activeEvent.getLeadChar()].getWeakness();

            if(Weakness.equals("Death")){
                Modifier-=70;
            }

            if(Race.equals("Ogre")||PClass.equals("Cannoneer")||PClass.equals("Fighter")||PClass.equals("Wizard")){
                Modifier+=20;
            }

            if(PClass.equals("Cleric")||Race.equals("Elf")||Race.equals("Goblin")){
                Modifier-=20;
            }
        }
        if(activeEvent.getName().equals("Rock Slide")&& Option.equals("option3")){
            String Race = Players[activeEvent.getLeadChar()].getRace();
            String PClass = Players[activeEvent.getLeadChar()].getCharclass();

            if(Race.equals("Goblin")){
                Modifier+=20;
            }

            if(PClass.equals("Wizard")){
                Modifier+=50;
            }

            if(Race.equals("Ogre")){
                Modifier-=20;
            }
        }

        //------------------------------------------------------------------------------------------

        if(activeEvent.getName().equals("Cliff walk")&& Option.equals("option1")){
            String Race = Players[activeEvent.getLeadChar()].getRace();
            String PClass = Players[activeEvent.getLeadChar()].getCharclass();
            String Weakness = Players[activeEvent.getLeadChar()].getWeakness();

            if(PClass.equals("Ranger")||Race.equals("Goblin")||Race.equals("Wolfborn")){
                Modifier+=20;
            }

            if(Weakness.equals("Heights")){
                Modifier-=70;
            }
        }
        if(activeEvent.getName().equals("Cliff walk")&& Option.equals("option2")){
            String Race = Players[activeEvent.getLeadChar()].getRace();
            String PClass = Players[activeEvent.getLeadChar()].getCharclass();

            if(PClass.equals("Tinkerer")||PClass.equals("Cannoneer")||Race.equals("Lizard")){
                Modifier+=20;
            }

            if(Race.equals("Dwarf")){
                Modifier+=50;
            }

            if(PClass.equals("Cleric")){
                Modifier-=20;
            }
        }
        if(activeEvent.getName().equals("Cliff walk")&& Option.equals("option3")){
            String PClass = Players[activeEvent.getLeadChar()].getCharclass();

            if(PClass.equals("Ranger")){
                Modifier+=20;
            }
        }

        //------------------------------------------------------------------------------------------

        if(activeEvent.getName().equals("Bar Antics")&& Option.equals("option1")){
            String Race = Players[activeEvent.getLeadChar()].getRace();

            if(Race.equals("Dwarf")||Race.equals("Ogre")||Race.equals("Lizard")){
                Modifier+=20;
            }

            if(Race.equals("Goblin")){
                Modifier-=20;
            }
        }
        if(activeEvent.getName().equals("Bar Antics")&& Option.equals("option2")){
            String PClass = Players[activeEvent.getLeadChar()].getCharclass();
            String Weakness = Players[activeEvent.getLeadChar()].getWeakness();

            if(PClass.equals("Wizard")){
                Modifier+=20;
            }

            if(PClass.equals("Cleric")||Weakness.equals("Social Situations")){
                Modifier-=20;
            }

        }
        if(activeEvent.getName().equals("Bar Antics")&& Option.equals("option3")){
            String Race = Players[activeEvent.getLeadChar()].getRace();
            String Weakness = Players[activeEvent.getLeadChar()].getWeakness();

            if(Race.equals("Human")||Race.equals("Changeling")){
                Modifier+=20;
            }

            if(Race.equals("Goblin")||Race.equals("Ogre")||Race.equals("Wolfborn")){
                Modifier-=20;
            }

            if(Weakness.equals("Social Situations")){
                Modifier-=70;
            }
        }

        //------------------------------------------------------------------------------------------

        if(activeEvent.getName().equals("The Djinn")&& Option.equals("option1")){
        }
        if(activeEvent.getName().equals("The Djinn")&& Option.equals("option2")){
        }
        if(activeEvent.getName().equals("The Djinn")&& Option.equals("option3")){
            String Weakness = Players[activeEvent.getLeadChar()].getWeakness();

            if(Weakness.equals("Alcohol")){
                Modifier-=50;
            }
        }

        //------------------------------------------------------------------------------------------

        if(activeEvent.getName().equals("The Holy Coaster")&& Option.equals("option1")){
            Modifier-=20;
        }
        if(activeEvent.getName().equals("The Holy Coaster")&& Option.equals("option2")){

        }
        if(activeEvent.getName().equals("The Holy Coaster")&& Option.equals("option3")){

        }

        //------------------------------------------------------------------------------------------

        if(activeEvent.getName().equals("Attacked by a rogue Squirrel")&& Option.equals("option1")){
            Modifier+=30;
        }
        if(activeEvent.getName().equals("Attacked by a rogue Squirrel")&& Option.equals("option2")){
            Modifier-=20;
        }
        if(activeEvent.getName().equals("Attacked by a rogue Squirrel")&& Option.equals("option3")){
            Modifier-=20;
        }

        //------------------------------------------------------------------------------------------

        if(activeEvent.getName().equals("Bandit attack")&& Option.equals("option1")){
            Modifier-=10;
        }
        if(activeEvent.getName().equals("Bandit attack")&& Option.equals("option2")){

        }
        if(activeEvent.getName().equals("Bandit attack")&& Option.equals("option3")){
            Modifier+=10;
        }

        //------------------------------------------------------------------------------------------

        if(activeEvent.getName().equals("Attacked by the Evil Knight")&& Option.equals("option1")){
            Modifier-=40;
        }
        if(activeEvent.getName().equals("Attacked by the Evil Knight")&& Option.equals("option2")){
            Modifier-=20;
        }
        if(activeEvent.getName().equals("Attacked by the Evil Knight")&& Option.equals("option3")){
            Modifier-=30;
        }

        //------------------------------------------------------------------------------------------

        if(activeEvent.getName().equals("Attacked by a Swarm of Beetles")&& Option.equals("option1")){
            String Weakness = Players[activeEvent.getLeadChar()].getWeakness();
            Modifier-=10;
            if(Weakness.equals("Insects")){
                Modifier-=40;
            }
        }
        if(activeEvent.getName().equals("Attacked by a Swarm of Beetles")&& Option.equals("option2")){
            String Weakness = Players[activeEvent.getLeadChar()].getWeakness();
            Modifier-=30;
            if(Weakness.equals("Insects")){
                Modifier-=20;
            }
        }
        if(activeEvent.getName().equals("Attacked by a Swarm of Beetles")&& Option.equals("option3")){
            Modifier+=20;
        }

        //------------------------------------------------------------------------------------------

        if(activeEvent.getName().equals("Crossing the rope bridge")&& Option.equals("option1")){
            String Race = Players[activeEvent.getLeadChar()].getRace();
            String Weakness = Players[activeEvent.getLeadChar()].getWeakness();

            if(Race.equals("Goblin")){
                Modifier+=20;
            }

            if(Race.equals("Ogre")){
                Modifier-=20;
            }

            if(Weakness.equals("Heights")){
                Modifier-=70;
            }
        }
        if(activeEvent.getName().equals("Crossing the rope bridge")&& Option.equals("option2")){
            String Race = Players[activeEvent.getLeadChar()].getRace();

            if(Race.equals("Ogre")){
                Modifier+=20;
            }

        }
        if(activeEvent.getName().equals("Crossing the rope bridge")&& Option.equals("option3")){
            String PClass = Players[activeEvent.getLeadChar()].getCharclass();

            if(PClass.equals("Cleric")){
                Modifier+=20;
            }

            if(PClass.equals("Spiritualist")){
                Modifier+=50;
            }

            if(!(PClass.equals("Cleric")||PClass.equals("Spiritualist"))){
                Modifier-=50;
            }
        }

        //------------------------------------------------------------------------------------------

        if(activeEvent.getName().equals("Entering the Deep")&& Option.equals("option1")){
            Modifier-=10;

        }
        if(activeEvent.getName().equals("Entering the Deep")&& Option.equals("option2")){
            String PClass = Players[activeEvent.getLeadChar()].getCharclass();
            Modifier+=20;
            if(PClass.equals("Ranger")){
                Modifier+=20;
            }
        }
        if(activeEvent.getName().equals("Entering the Deep")&& Option.equals("option3")){
            String Race = Players[activeEvent.getLeadChar()].getRace();

            if(Race.equals("Changeling")){
                Modifier+=20;
            }
        }

        //------------------------------------------------------------------------------------------

        if((rand+Modifier)>50){
            return "Pass";
        }else{
            return "Fail";
        }
    }

    @Override
    public void OverloadResult(Events activeEvent, String PassOrFail, String Option, String Difficulty) {
        String newRes;
        String SingleDrink;
        String LightDrink;
        String MedDrink;
        String HeavyDrink;

        if(Difficulty.equals("Sober")){
            SingleDrink = Integer.toString(1);
            LightDrink = Integer.toString(1);
            MedDrink = Integer.toString(2);
            HeavyDrink = Integer.toString(3);
        }else if(Difficulty.equals("Tipsy")){
            SingleDrink = Integer.toString(1);
            LightDrink = Integer.toString(2);
            MedDrink = Integer.toString(4);
            HeavyDrink = Integer.toString(6);
        }else{
            SingleDrink = Integer.toString(2);
            LightDrink = Integer.toString(3);
            MedDrink = Integer.toString(5);
            HeavyDrink = Integer.toString(8);
        }


        if(activeEvent.getName().equals("startingTown")&&PassOrFail.equals("Pass")){
            newRes = "The Party leaves optimistically. All players drink "+LightDrink+".";
        }else if(activeEvent.getName().equals("startingTown")&&PassOrFail.equals("Fail")) {
            newRes = "The Party eventually leaves town after one final stop at the Tavern. All players drink "+MedDrink+".";
        }
        else if(activeEvent.getName().equals("Volcano")&&PassOrFail.equals("Pass") && Option.equals("option1")) {
            newRes = "You successfully navigated the volcano!";
        }else if(activeEvent.getName().equals("Volcano")&&PassOrFail.equals("Fail") && Option.equals("option1")) {
            newRes = "You made it past the volcano but took some nasty burns, take a drink of healing potion.";
        }else if(activeEvent.getName().equals("Volcano")&&PassOrFail.equals("Pass") && Option.equals("option2")) {
            newRes = "You just barely navigated the volcano, take "+SingleDrink+" drink.";
        }else if(activeEvent.getName().equals("Volcano")&&PassOrFail.equals("Fail") && Option.equals("option2")) {
            newRes = "You slip on the rock and bang your knee, drink "+MedDrink+" drinks.";
        }else if(activeEvent.getName().equals("Volcano")&&PassOrFail.equals("Pass") && Option.equals("option3")) {
            newRes = "You heroically navigated the volcano, give out "+SingleDrink+" drink!";
        }else if(activeEvent.getName().equals("Volcano")&&PassOrFail.equals("Fail") && Option.equals("option3")) {
            newRes = "You were gravely injured by the lava.  Take a double drink of healing potion!";
        }
        else if(activeEvent.getName().equals("Lightning Strike")&&PassOrFail.equals("Pass") && Option.equals("option1")) {
            newRes = "You just about avoided the lightning strikes, however it has drained your energy. You must drink "+MedDrink+" to feel well again.";
        }else if(activeEvent.getName().equals("Lightning Strike")&&PassOrFail.equals("Fail") && Option.equals("option1")) {
            newRes = "You try and dodge the strikes but one hits you, take "+HeavyDrink+" drinks and you take time to recuperate.";
        }else if(activeEvent.getName().equals("Lightning Strike")&&PassOrFail.equals("Pass") && Option.equals("option2")) {
            newRes = "You successfully craft a lightning rod and use it to shield yourself, feeling proud you take "+SingleDrink+" celebratory drink!";
        }else if(activeEvent.getName().equals("Lightning Strike")&&PassOrFail.equals("Fail") && Option.equals("option2")) {
            newRes = "You craft the lighting rod but forget to attach it to anything, the lightning homes in on you! Take a drink of healing potion as you recover!";
        }else if(activeEvent.getName().equals("Lightning Strike")&&PassOrFail.equals("Pass") && Option.equals("option3")) {
            newRes = "You manage to find an alternate route, all other party members take "+SingleDrink+" drink as the honour your success!";
        }else if(activeEvent.getName().equals("Lightning Strike")&&PassOrFail.equals("Fail") && Option.equals("option3")) {
            newRes = "You are unable to find another route, eventually you head through the lightning field a day behind schedule.  You take "+SingleDrink+" embarressed drink for every other member of your party.";
        }
        else if(activeEvent.getName().equals("Rock Slide")&&PassOrFail.equals("Pass") && Option.equals("option1")) {
            newRes = "You dodge the big rocks although some small rocks still clip you, take "+LightDrink+" drinks.";
        }else if(activeEvent.getName().equals("Rock Slide")&&PassOrFail.equals("Fail") && Option.equals("option1")) {
            newRes = "You fail to dodge the rocks and are knocked unconcious.  You awake with a nasty headache, take "+HeavyDrink+" drinks to feel better.";
        }else if(activeEvent.getName().equals("Rock Slide")&&PassOrFail.equals("Pass") && Option.equals("option2")) {
            newRes = "The Rocks are no match for you! You smash them aside.  Your impressed party members all take "+MedDrink+" drinks!";
        }else if(activeEvent.getName().equals("Rock Slide")&&PassOrFail.equals("Fail") && Option.equals("option2")) {
            newRes = "The rocks get the better of you and you are hit squarly in the face.  Take "+LightDrink+" drinks and a drink of healing potion.";
        }else if(activeEvent.getName().equals("Rock Slide")&&PassOrFail.equals("Pass") && Option.equals("option3")) {
            newRes = "You manage to take cover. Most of the rocks miss you and you make it out with only a few scratches.  Take "+SingleDrink+" drink.";
        }else if(activeEvent.getName().equals("Rock Slide")&&PassOrFail.equals("Fail") && Option.equals("option3")) {
            newRes = "Your cover collapses on top of you!  Take "+HeavyDrink+" drinks!";
        }
        else if(activeEvent.getName().equals("Cliff walk")&&PassOrFail.equals("Pass") && Option.equals("option1")) {
            newRes = "You manage to scuttle around the edge of the cliff, no drinks for you!";
        }else if(activeEvent.getName().equals("Cliff walk")&&PassOrFail.equals("Fail") && Option.equals("option1")) {
            newRes = "You fall off the cliff, your companion grabs you but you both fall together.  You and the player to your left drinks "+MedDrink+".";
        }else if(activeEvent.getName().equals("Cliff walk")&&PassOrFail.equals("Pass") && Option.equals("option2")) {
            newRes = "You tunnel through the cliff and arrive at your destination.  Well done!";
        }else if(activeEvent.getName().equals("Cliff walk")&&PassOrFail.equals("Fail") && Option.equals("option2")) {
            newRes = "You fail to tunnel into the rock. Your unimpressed party give you distasteful looks.  Drink "+MedDrink+".";
        }else if(activeEvent.getName().equals("Cliff walk")&&PassOrFail.equals("Pass") && Option.equals("option3")) {
            newRes = "You find an alternate route!  One of your party is unimpressed however.  The player to the right of you drinks "+LightDrink+".";
        }else if(activeEvent.getName().equals("Cliff walk")&&PassOrFail.equals("Fail") && Option.equals("option3")) {
            newRes = "You fail to find an alternate route.  Drink "+MedDrink+".";
        }
        else if(activeEvent.getName().equals("Bar Antics")&&PassOrFail.equals("Pass") && Option.equals("option1")) {
            newRes = "You order a few drinks for you and your party.  All Players drink "+LightDrink+".";
        }else if(activeEvent.getName().equals("Bar Antics")&&PassOrFail.equals("Fail") && Option.equals("option1")) {
            newRes = "You order a few drinks, then a few more, then a few more.  At the end of the evening your tab comes through.  Horrified by the amount of money you have to pay you resort to getting drunk again: drink "+HeavyDrink+".";
        }else if(activeEvent.getName().equals("Bar Antics")&&PassOrFail.equals("Pass") && Option.equals("option2")) {
            newRes = "You win the round and use the money to buy drinks for your fellow party members!  All players other than you drink "+LightDrink+".";
        }else if(activeEvent.getName().equals("Bar Antics")&&PassOrFail.equals("Fail") && Option.equals("option2")) {
            newRes = "You lose the round and have to buy drinks for the table.  Drink "+MedDrink+".";
        }else if(activeEvent.getName().equals("Bar Antics")&&PassOrFail.equals("Pass") && Option.equals("option3")) {
            newRes = "The locals take to your charms and pay for your drinks! drink "+LightDrink+".";
        }else if(activeEvent.getName().equals("Bar Antics")&&PassOrFail.equals("Fail") && Option.equals("option3")) {
            newRes = "The locals do not treat you kindly and you are kicked out of the tavern with some nasty bruises!  Take a drink of healing potion to recover.";
        }
        else if(activeEvent.getName().equals("The Djinn")&&PassOrFail.equals("Pass") && Option.equals("option1")) {
            newRes = "You feel extremely powerful.  With your new power you may nominate another player to drink "+HeavyDrink+"!";
        }else if(activeEvent.getName().equals("The Djinn")&&PassOrFail.equals("Fail") && Option.equals("option1")) {
            newRes = "Power comes at a cost... You suddenly feel powerful but also... thirsty... drink "+HeavyDrink+".";
        }else if(activeEvent.getName().equals("The Djinn")&&PassOrFail.equals("Pass") && Option.equals("option2")) {
            newRes = "The Djinn grants you wealth beyond your wildest dreams.  You immediately spend it all on a golden statue and Alcohol.  Everyone drinks "+HeavyDrink+" in celebration!  Also you have a golden statue!";
        }else if(activeEvent.getName().equals("The Djinn")&&PassOrFail.equals("Fail") && Option.equals("option2")) {
            newRes = "The Djinn grants you wealth beyond your wildest dreams. Unfortunately the money is cursed! Every coin you spend makes you more thristy... Take "+SingleDrink+" drink for every future Event that you are not leading!";
        }else if(activeEvent.getName().equals("The Djinn")&&PassOrFail.equals("Pass") && Option.equals("option3")) {
            newRes = "Your wish has been recieved! Drink "+MedDrink+".";
        }else if(activeEvent.getName().equals("The Djinn")&&PassOrFail.equals("Fail") && Option.equals("option3")) {
            newRes = "The Djinn is upset and feels you have wasted your wish.  He strikes out at you then dissappears.  Take "+SingleDrink+" drink of health potion.";
        }

        else if(activeEvent.getName().equals("The Holy Coaster")&&PassOrFail.equals("Pass") && Option.equals("option1")) {
            newRes = "The power of the coaster activates and all players find a ready drink in their hands!  All Players drink "+HeavyDrink+".";
        }else if(activeEvent.getName().equals("The Holy Coaster")&&PassOrFail.equals("Fail") && Option.equals("option1")) {
            newRes = "You try and activate the coaster... but nothing happens.  Maybe the rumors were false, maybe it ran out of power long ago. Drink "+MedDrink+" in frustration!";
        }else if(activeEvent.getName().equals("The Holy Coaster")&&PassOrFail.equals("Pass") && Option.equals("option2")) {
            newRes = "You keep the coaster, maybe it will bring you luck!.";
        }else if(activeEvent.getName().equals("The Holy Coaster")&&PassOrFail.equals("Fail") && Option.equals("option2")) {
            newRes = "You go to sleep one night only to wake the next morning and find the coaster missing! It seems it was cursed and you now feel incredibly thirsty. Drink "+HeavyDrink+" to quench your thirst!";
        }else if(activeEvent.getName().equals("The Holy Coaster")&&PassOrFail.equals("Pass") && Option.equals("option3")) {
            newRes = "Probably best not to risk the Coaster being cursed. You Discard it!";
        }else if(activeEvent.getName().equals("The Holy Coaster")&&PassOrFail.equals("Fail") && Option.equals("option3")) {
            newRes = "You try to discard the coaster but it returns to your hand.  It seems it has chosen you... oh well.";
        }

        else if(activeEvent.getName().equals("Attacked by a rogue Squirrel")&&PassOrFail.equals("Pass") && Option.equals("option1")) {
            newRes = "You successfully fight off the squirrel... you monster, take "+LightDrink+" drinks for animal cruelty!";
        }else if(activeEvent.getName().equals("Attacked by a rogue Squirrel")&&PassOrFail.equals("Fail") && Option.equals("option1")) {
            newRes = "You are defeated by a squirrel... take two drinks of healing potion.  The rest of the party may opt to pity you by taking "+SingleDrink+" drink, if any of them do you must feel ashamed.";
        }else if(activeEvent.getName().equals("Attacked by a rogue Squirrel")&&PassOrFail.equals("Pass") && Option.equals("option2")) {
            newRes = "Event though the squirrel can't talk it seems to respond to your words and stops its attack. The squirrel joins you as a friendly companion";
        }else if(activeEvent.getName().equals("Attacked by a rogue Squirrel")&&PassOrFail.equals("Fail") && Option.equals("option2")) {
            newRes = "The squirrel takes no notice of you and gives you a few minor scratches.  Take "+LightDrink+" drinks to try and ignore the feelings of pain.";
        }else if(activeEvent.getName().equals("Attacked by a rogue Squirrel")&&PassOrFail.equals("Pass") && Option.equals("option3")) {
            newRes = "You look ridiculous, but you escape the squirrel unharmed. No drinks for you."+SingleDrink+" drink!";
        }else if(activeEvent.getName().equals("Attacked by a rogue Squirrel")&&PassOrFail.equals("Fail") && Option.equals("option3")) {
            newRes = "The squirrel chases you down and gives you a nasty scratch, Take "+LightDrink+" drinks to try and ignore the feelings of pain.";
        }

        else if(activeEvent.getName().equals("Bandit attack")&&PassOrFail.equals("Pass") && Option.equals("option1")) {
            newRes = "You successfully fight off the bandits. Huzzah!";
        }else if(activeEvent.getName().equals("Bandit attack")&&PassOrFail.equals("Fail") && Option.equals("option1")) {
            newRes = "You are defeated by the bandits.  As they are taking your equipment you are saved by Sir Chaworth-Musters! He insists you take a drink of healing potion for your wounds before leaving you and continuing his journey.  What a guy!";
        }else if(activeEvent.getName().equals("Bandit attack")&&PassOrFail.equals("Pass") && Option.equals("option2")) {
            newRes = "You offer the bandits a trade and they accept, somehow you actually come out on top having gained a large amount of alcohol.  You and two fellow party members of your choice drink "+MedDrink+" in celebration of the plentiful booze!";
        }else if(activeEvent.getName().equals("Bandit attack")&&PassOrFail.equals("Fail") && Option.equals("option2")) {
            newRes = "There is no negotiation to be had. The bandits attack you and you injure yourself fighting them off. Take a drink of healing potion to recover.";
        }else if(activeEvent.getName().equals("Bandit attack")&&PassOrFail.equals("Pass") && Option.equals("option3")) {
            newRes = "You manage to escape the bandits.";
        }else if(activeEvent.getName().equals("Bandit attack")&&PassOrFail.equals("Fail") && Option.equals("option3")) {
            newRes = "The Bandits catch you but appear to be warn out.  You fight them off and take "+LightDrink+" drinks in order to recouperate";
        }

        else if(activeEvent.getName().equals("Attacked by the Evil Knight")&&PassOrFail.equals("Pass") && Option.equals("option1")) {
            newRes = "Victory, you first cut off his arms, then his legs.  After threatening to bite you to death you take his head.  He will bother no-one again!";
        }else if(activeEvent.getName().equals("Attacked by the Evil Knight")&&PassOrFail.equals("Fail") && Option.equals("option1")) {
            newRes = "None escape the Black Knight! You are left mortally wounded and must take 2 drinks of healing potion to recover.";
        }else if(activeEvent.getName().equals("Attacked by the Evil Knight")&&PassOrFail.equals("Pass") && Option.equals("option2")) {
            newRes = "The Black Knight hears your negotiation.  In the end he forces you to drink "+HeavyDrink+" drinks in his honour.  You think you may have gotten off lucky.";
        }else if(activeEvent.getName().equals("Attacked by the Evil Knight")&&PassOrFail.equals("Fail") && Option.equals("option2")) {
            newRes = "The Black Knight does not negotiate! You are left mortally wounded and must take 2 drinks of healing potion to recover.";
        }else if(activeEvent.getName().equals("Attacked by the Evil Knight")&&PassOrFail.equals("Pass") && Option.equals("option3")) {
            newRes = "You manage to escape the Black Knight.  That was close!";
        }else if(activeEvent.getName().equals("Attacked by the Evil Knight")&&PassOrFail.equals("Fail") && Option.equals("option3")) {
            newRes = "None escape the Black Knight! You are left mortally wounded and must take 2 drinks of healing potion to recover.";
        }

        else if(activeEvent.getName().equals("Attacked by a Swarm of Beetles")&&PassOrFail.equals("Pass") && Option.equals("option1")) {
            newRes = "You fight off the swarm.  They are no match for your might!";
        }else if(activeEvent.getName().equals("Attacked by a Swarm of Beetles")&&PassOrFail.equals("Fail") && Option.equals("option1")) {
            newRes = "You are overpowered by the swarm. Drink 1 drink of healing potion to recover.";
        }else if(activeEvent.getName().equals("Attacked by a Swarm of Beetles")&&PassOrFail.equals("Pass") && Option.equals("option2")) {
            newRes = "Your words affect the swarm in a way none would have thought possible.  The swarm leaves you alone.";
        }else if(activeEvent.getName().equals("Attacked by a Swarm of Beetles")&&PassOrFail.equals("Fail") && Option.equals("option2")) {
            newRes = "The swarm ignores your pleas and attacks.  You are injured and must drink "+MedDrink+" to recover from your wounds.";
        }else if(activeEvent.getName().equals("Attacked by a Swarm of Beetles")&&PassOrFail.equals("Pass") && Option.equals("option3")) {
            newRes = "You manage to escape the swarm.";
        }else if(activeEvent.getName().equals("Attacked by a Swarm of Beetles")&&PassOrFail.equals("Fail") && Option.equals("option3")) {
            newRes = "You cannot escape the swarm. They attack and you are injured and must drink "+MedDrink+" to recover from your wounds";
        }

        else if(activeEvent.getName().equals("Crossing the rope bridge")&&PassOrFail.equals("Pass") && Option.equals("option1")) {
            newRes = "You cross the bridge without worry.  It turns out the bridge was sturdier than it looked.";
        }else if(activeEvent.getName().equals("Crossing the rope bridge")&&PassOrFail.equals("Fail") && Option.equals("option1")) {
            newRes = "While crossing the bridge suddenly snaps and you fall.  The whole party takes a drink of healing potion for your incompetance.";
        }else if(activeEvent.getName().equals("Crossing the rope bridge")&&PassOrFail.equals("Pass") && Option.equals("option2")) {
            newRes = "You climb down into the ravine. It is exhausting work.  Take "+LightDrink+" drinks to get your energy back.";
        }else if(activeEvent.getName().equals("Crossing the rope bridge")&&PassOrFail.equals("Fail") && Option.equals("option2")) {
            newRes = "As you climb down into the ravine you slip. You manage to recover but it takes all your strength.  Take "+MedDrink+" drinks to get your energy back.";
        }else if(activeEvent.getName().equals("Crossing the rope bridge")&&PassOrFail.equals("Pass") && Option.equals("option3")) {
            newRes = "The spirits come to you and help keep the bridge up while you and your party cross.  The rush of summoning the spirits gives you the mysitcal power to give out "+MedDrink+" drinks between the other players.";
        }else if(activeEvent.getName().equals("Crossing the rope bridge")&&PassOrFail.equals("Fail") && Option.equals("option3")) {
            newRes = "The spirits do not answer your call. Disappointed you take "+HeavyDrink+" drinks and cross the bridge... which then breaks, once you have finished your drinks take a drink of healing potion.";
        }

        else if(activeEvent.getName().equals("Entering the Deep")&&PassOrFail.equals("Pass") && Option.equals("option1")) {
            newRes = "You make your way through the Deep.  You encounter no obstacles in your path.  Rumors of the Deep's danger may have been exaggerated.";
        }else if(activeEvent.getName().equals("Entering the Deep")&&PassOrFail.equals("Fail") && Option.equals("option1")) {
            newRes = "You lead your party down into the Deep.  Inside you are attacked by unspeakable horrors.  Take a drink of healing potion.  The rest of your party must take "+MedDrink+" drinks as they try and forget what they have seen.";
        }else if(activeEvent.getName().equals("Entering the Deep")&&PassOrFail.equals("Pass") && Option.equals("option2")) {
            newRes = "You decide to find a safer route.  It will be longer but safer! Take "+MedDrink+" to represent the supplies lost by the extra travel time.";
        }else if(activeEvent.getName().equals("Entering the Deep")&&PassOrFail.equals("Fail") && Option.equals("option2")) {
            newRes = "Your search for a safer route takes an extra week. Take "+HeavyDrink+" to represent the supplies lost by the extra travel time.";
        }else if(activeEvent.getName().equals("Entering the Deep")&&PassOrFail.equals("Pass") && Option.equals("option3")) {
            newRes = "Your party are amused at the reference and spurred on enter the deep spirits high! Together you defeat the foul creatures and proceed unobstructed through the Deep!";
        }else if(activeEvent.getName().equals("Entering the Deep")&&PassOrFail.equals("Fail") && Option.equals("option3")) {
            newRes = "Your party is unamused and knocks you unconscious.  Take 2 drinks of healing potion. You awake on the other side of the Deep.  You have no idea how you got there but the dead look in the eyes of your party tell you that you will not have any friends after this quest!";
        }

        else if(activeEvent.getName().equals("Injury")){
            newRes = "Take a drink of Healing potion. You are now feeling better.";
        }else if(activeEvent.getName().equals("Sickness")){
            newRes = "Take a drink of Healing potion. You are now feeling better.";
        }else if(activeEvent.getName().equals("Curse of the Platypus!")){
            newRes = "Only a double helping of healing potion can save you!  Drink them now before you are a platypus forever!";
        }

        else if(activeEvent.getName().equals("THPG!")&&PassOrFail.equals("Pass")){
            newRes = "You find the grail and everything is good.  Everyone drinks "+LightDrink+" in celebration!";
        }else if(activeEvent.getName().equals("THPG!")&&PassOrFail.equals("Fail")) {
            newRes = "The grail is cursed! Everyone must drink from the healing potion!";
        }else{
            newRes = "Error 404, no Result found.";
        }

        realm.beginTransaction();
        activeEvent.setEventResult(newRes);
        realm.commitTransaction();
    }


}
