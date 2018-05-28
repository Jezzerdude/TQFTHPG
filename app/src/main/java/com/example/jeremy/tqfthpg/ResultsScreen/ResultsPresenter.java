package com.example.jeremy.tqfthpg.ResultsScreen;

import com.example.jeremy.tqfthpg.MainGame.MainGameFragment;

public class ResultsPresenter implements ResultsInterface.MainPresenterInterface {

    private ResultsFragment resultsFrag;

    @Override
    public ResultsFragment getFragment() {
        resultsFrag = new ResultsFragment();
        return resultsFrag;
    }
}
