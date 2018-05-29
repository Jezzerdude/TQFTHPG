package com.example.jeremy.tqfthpg.MainGame;

import android.app.Fragment;

import com.example.jeremy.tqfthpg.CharacterScreen.CharacterFragment;
import com.example.jeremy.tqfthpg.CharacterScreen.Model.PCharacter;
import com.example.jeremy.tqfthpg.MainGame.Model2.Events;

public interface MainGameInterface {

    interface MainPresenterInterface{
        MainGameFragment getFragment();
        MainGameFragment2 getFragment2();
        Fragment GetSpecificEvent(Events event);
        Events[] AddEvents(PCharacter[] players);
        PCharacter[] getPlayers(int PlayerNo);
        int[] GenLeadChar(int PlayerNo);
        void SaveEvents(Events[] events);
        void DeleteEvents();
        Events[] getEvents(int MaxEvents);
        String rollResult(Events activeEvent);
        void OverloadResult(Events activeEvent, String PassOrFail, String Option, String Difficulty);
    }

    interface MainViewInterface{

    }
}
