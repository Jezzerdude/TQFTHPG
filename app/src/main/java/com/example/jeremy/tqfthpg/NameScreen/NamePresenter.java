package com.example.jeremy.tqfthpg.NameScreen;

import com.example.jeremy.tqfthpg.CharacterScreen.CharacterFragment;

public class NamePresenter implements NameInterface.presenterInterface{

    NameFragment nameFragment;

    @Override
    public NameFragment getFragment() {
        nameFragment = new NameFragment();
        return nameFragment;
    }

}
