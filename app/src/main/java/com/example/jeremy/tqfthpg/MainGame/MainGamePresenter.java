package com.example.jeremy.tqfthpg.MainGame;

import com.example.jeremy.tqfthpg.CharacterScreen.CharacterFragment;

public class MainGamePresenter implements MainGameInterface.MainPresenterInterface{

    private MainGameFragment mainGameFrag;

    @Override
    public MainGameFragment getFragment() {
        mainGameFrag = new MainGameFragment();
        return mainGameFrag;
    }
}
