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
    public Fragment GetSpecificEvent(Events event) {
        return null;
    }

    @Override
    public Events[] AddEvents(PCharacter[] players) {
        Events[] eventList = new Events[18];
        int MaxPlayers = players.length;
        Events[] questList = new Events[MaxPlayers*2];
        int[] Leads= GenLeadChar(MaxPlayers);


        Events startingTown = new Events(0,"startingTown","Single","","You Leave the town to start your adventure!","Travel onwards: ",players[0].getId(),"Drink!");
        Events volcano = new Events(1,"Volcano","Multiple","","A towering Volcano appears before you.  What will you do?","Build a bridge: ","Climb around it: ","Jump the lava: ",players[0].getId(),"Drink! 2","NA");
        Events lightning = new Events(2,"Ligtning Strike","Multiple","","A vicious storm has broken out in front of you.  How will you proceed?","Run through: ","Build a lighting Rod: ","Find an alternate route: ",players[0].getId(),"Drink! 3","NA");
        Events rocks = new Events(3,"Rock Slide","Multiple","","Your attention is caught by a sudden rock slide.  As the bolders hurtle torwards you, you:","Dodge the rocks: ","Smash through the rocks: ","Take cover: ",players[0].getId(),"Drink! 4","NA");
        Events cliff = new Events(4,"Cliff walk","Multiple","","A perilous cliff walk with a small path lies ahead, what should you do?","Navigate the cliffs: ","Tunnel through: ","Find an alternate route: ",players[0].getId(),"Drink! 5","NA");
        Events THPG = new Events(5,"THPG!","Single","","The Holy Pint Glass stands before you!","Take the grail: ",players[0].getId(),"Drink! 6");

        eventList[0] = startingTown;
        eventList[1] = volcano;
        eventList[2] = lightning;
        eventList[3] = rocks;
        eventList[4] = cliff;
        eventList[5] = THPG;

        boolean used = false;
        int Counter = 0;

        for (int i = 0; i < UsedEvents.length; i++) {
            UsedEvents[i]=0;
        }

        for(int i=0;i<questList.length;i++){
            int rand = (int) (Math.random() * ((4 - 1) + 1)) + 1;
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
                eventList[i].setLeadChar(Leads[i]);
                questList[i]=eventList[5];
            }else if(!used){
                eventList[rand].setLeadChar(Leads[i]);
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
    public int[] GenLeadChar(int PlayerNo) {
        int[] leadCharsList = new int[PlayerNo*2];
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
    public String rollResult(Events activeEvent) {
        int rand = (int) (Math.random() * ((100 - 1) + 1)) + 1;

        if(rand>50){
            return "Pass";
        }else{
            return "Fail";
        }
    }

    @Override
    public void OverloadResult(Events activeEvent, String PassOrFail, String Option) {
        String newRes;

        if(activeEvent.getName().equals("startingTown")&&PassOrFail.equals("Pass")){
            newRes = "Everyone is Happy. All players drink 2";
        }else if(activeEvent.getName().equals("startingTown")&&PassOrFail.equals("Fail")) {
            newRes = "Everyone is sad. All players drink 3.";
        }else if(activeEvent.getName().equals("Volcano")&&PassOrFail.equals("Pass") && Option.equals("option1")) {
            newRes = "You successfully navigated the vlocano!";
        }else if(activeEvent.getName().equals("Volcano")&&PassOrFail.equals("Fail") && Option.equals("option1")) {
            newRes = "You made it past the volcano but took some nasty burns, take a drink of healing potion.";
        }else if(activeEvent.getName().equals("Volcano")&&PassOrFail.equals("Pass") && Option.equals("option2")) {
            newRes = "You just barely navigated the vlocano, take a drink.";
        }else if(activeEvent.getName().equals("Volcano")&&PassOrFail.equals("Fail") && Option.equals("option2")) {
            newRes = "You slip on the rock and bang your knee, drink 3 drinks.";
        }else if(activeEvent.getName().equals("Volcano")&&PassOrFail.equals("Pass") && Option.equals("option3")) {
            newRes = "You heroically navigated the volcano, give out a drink!";
        }else if(activeEvent.getName().equals("Volcano")&&PassOrFail.equals("Fail") && Option.equals("option3")) {
            newRes = "You were gravely injured by the lava.  Take a double drink of healing potion!";
        }else if(activeEvent.getName().equals("Ligtning Strike")&&PassOrFail.equals("Pass") && Option.equals("option1")) {
            newRes = "You just about avoided the lightning strikes, however it has drained your energy. You must drink 4 to feel well again.";
        }else if(activeEvent.getName().equals("Ligtning Strike")&&PassOrFail.equals("Fail") && Option.equals("option1")) {
            newRes = "You try and dodge the strikes but one hits you, take 5 drinks and you take time to recuperate.";
        }else if(activeEvent.getName().equals("Ligtning Strike")&&PassOrFail.equals("Pass") && Option.equals("option2")) {
            newRes = "You successfully craft a lightning rod and use it to shield yourself, feeling proud you take a celebratory drink!";
        }else if(activeEvent.getName().equals("Ligtning Strike")&&PassOrFail.equals("Fail") && Option.equals("option2")) {
            newRes = "You craft the lighting rod but forget to attach it to anything, the lightning homes in on you! Take a drink of healing potion as you recover!";
        }else if(activeEvent.getName().equals("Ligtning Strike")&&PassOrFail.equals("Pass") && Option.equals("option3")) {
            newRes = "You manage to find an alternate route, all other party members take a drink as the honour your success!";
        }else if(activeEvent.getName().equals("Ligtning Strike")&&PassOrFail.equals("Fail") && Option.equals("option3")) {
            newRes = "You are unable to find another route, eventually you head through the lightning field a day behind schedule.  You take an embarressed drink for every other member of your party.";
        }else if(activeEvent.getName().equals("Rock Slide")&&PassOrFail.equals("Pass") && Option.equals("option1")) {
            newRes = "You dodge the big rocks although some small rocks still clip you, take 2 drinks.";
        }else if(activeEvent.getName().equals("Rock Slide")&&PassOrFail.equals("Fail") && Option.equals("option1")) {
            newRes = "You fail to dodge the rocks and are knocked unconcious.  You awake with a nasty headache, take 5 drinks to feel better.";
        }else if(activeEvent.getName().equals("Rock Slide")&&PassOrFail.equals("Pass") && Option.equals("option2")) {
            newRes = "The Rocks are no match for you! You smash them aside.  Your impressed party members all take 3 drinks!";
        }else if(activeEvent.getName().equals("Rock Slide")&&PassOrFail.equals("Fail") && Option.equals("option2")) {
            newRes = "The rocks get the better of you and you are hit squarly in the face.  Take 2 drinks and a drink of healing potion.";
        }else if(activeEvent.getName().equals("Rock Slide")&&PassOrFail.equals("Pass") && Option.equals("option3")) {
            newRes = "You manage to take cover. Most of the rocks miss you and you make it out with only a few scratches.  Take a drink.";
        }else if(activeEvent.getName().equals("Rock Slide")&&PassOrFail.equals("Fail") && Option.equals("option3")) {
            newRes = "Your cover collapses on top of you!  Take 6 drinks!";
        }else if(activeEvent.getName().equals("Cliff walk")&&PassOrFail.equals("Pass") && Option.equals("option1")) {
            newRes = "You manage to scuttle around the edge of the cliff, no drinks for you!";
        }else if(activeEvent.getName().equals("Cliff walk")&&PassOrFail.equals("Fail") && Option.equals("option1")) {
            newRes = "You fall off the cliff, your companion grabs you but you both fall together.  You and the player to your left drinks 4.";
        }else if(activeEvent.getName().equals("Cliff walk")&&PassOrFail.equals("Pass") && Option.equals("option2")) {
            newRes = "You tunnel through the cliff and arrive at your destination.  Well done!";
        }else if(activeEvent.getName().equals("Cliff walk")&&PassOrFail.equals("Fail") && Option.equals("option2")) {
            newRes = "You fail to tunnel into the rock. Your unimpressed party give you distasteful looks.  Drink 4.";
        }else if(activeEvent.getName().equals("Cliff walk")&&PassOrFail.equals("Pass") && Option.equals("option3")) {
            newRes = "You find an alternate route!  One of your party is unimpressed however.  The player to the right of you drinks 2.";
        }else if(activeEvent.getName().equals("Cliff walk")&&PassOrFail.equals("Fail") && Option.equals("option3")) {
            newRes = "You fail to find an alternate route.  Drink 3.";
        }else if(activeEvent.getName().equals("THPG!")&&PassOrFail.equals("Pass")){
            newRes = "You find the grail and everything is good.  Everyone drinks 2 in celebration!";
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
