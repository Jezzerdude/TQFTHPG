package com.example.jeremy.tqfthpg.ResultsScreen;

import com.example.jeremy.tqfthpg.CharacterScreen.Model.PCharacter;

import io.realm.Realm;

public class ResultsPresenter implements ResultsInterface.MainPresenterInterface {

    private ResultsFragment resultsFrag;
    Realm realm;

    @Override
    public ResultsFragment getFragment() {
        resultsFrag = new ResultsFragment();
        return resultsFrag;
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
}
