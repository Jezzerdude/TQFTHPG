package com.example.jeremy.tqfthpg.DifficultyScreen;

public class DiffPresenter implements DiffInterface.presenterInterface {

    DiffFragment diffFragment;

    @Override
    public DiffFragment getFragment() {
        diffFragment = new DiffFragment();
        return diffFragment;
    }
}
