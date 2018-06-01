package com.example.jeremy.tqfthpg.ResultsScreen;

import com.example.jeremy.tqfthpg.CharacterScreen.Model.PCharacter;
import com.example.jeremy.tqfthpg.MainGame.MainGameFragment;
import com.example.jeremy.tqfthpg.MainGame.MainGameInterface;

public interface ResultsInterface {

    interface MainViewInterface{

    }

    interface MainPresenterInterface{
        ResultsFragment getFragment();
        PCharacter[] getPlayers(int PlayerNo);
    }
}
