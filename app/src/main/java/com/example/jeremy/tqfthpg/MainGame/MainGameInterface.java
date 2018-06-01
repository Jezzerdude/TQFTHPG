package com.example.jeremy.tqfthpg.MainGame;

import android.app.Fragment;

import com.example.jeremy.tqfthpg.CharacterScreen.CharacterFragment;
import com.example.jeremy.tqfthpg.CharacterScreen.Model.PCharacter;
import com.example.jeremy.tqfthpg.MainGame.Model2.Events;

public interface MainGameInterface {

    interface MainPresenterInterface{
        MainGameFragment getFragment();
        MainGameFragment2 getFragment2();
        Events[] AddEvents(PCharacter[] players, int EventsNeeded);
        PCharacter[] getPlayers(int PlayerNo);
        int[] GenLeadChar(int PlayerNo, int EventsNeeded);
        void SaveEvents(Events[] events);
        void DeleteEvents();
        Events[] getEvents(int MaxEvents);
        String rollResult(Events activeEvent, String Option, PCharacter[] Players);
        void OverloadResult(Events activeEvent, String PassOrFail, String Option, String Difficulty, PCharacter Player, PCharacter[] Players);
        void AddDrinksToPlayer(PCharacter Player, int DrinksAmount);
        void AddDrinksToAllPlayers(PCharacter[] Players, int DrinkAmount);
        void AddShotsToPlayer(PCharacter Player, int ShotAmount);
        void AddShotsToAllPlayers(PCharacter[] Players, int DrinkAmount);
    }

    interface MainViewInterface{

    }
}
