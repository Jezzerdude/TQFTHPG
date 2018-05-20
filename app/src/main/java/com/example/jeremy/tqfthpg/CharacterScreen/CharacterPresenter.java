package com.example.jeremy.tqfthpg.CharacterScreen;

import com.example.jeremy.tqfthpg.DifficultyScreen.DiffFragment;

public class CharacterPresenter implements CharacterInterface.presenterInterface {

    CharacterFragment characterFragment;

    @Override
    public CharacterFragment getFragment() {
        characterFragment = new CharacterFragment();
        return characterFragment;
    }
}
