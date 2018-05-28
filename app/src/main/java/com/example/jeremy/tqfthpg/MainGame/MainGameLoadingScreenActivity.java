package com.example.jeremy.tqfthpg.MainGame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.jeremy.tqfthpg.AppInitiliser;
import com.example.jeremy.tqfthpg.CharacterScreen.Model.PCharacter;
import com.example.jeremy.tqfthpg.MainGame.Model2.Events;
import com.example.jeremy.tqfthpg.R;

import java.util.Arrays;

import javax.inject.Inject;

public class MainGameLoadingScreenActivity extends AppCompatActivity implements MainGameInterface.MainViewInterface {

    @Inject
    MainGameInterface.MainPresenterInterface presenter;

    SharedPreferences app_pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_loading_screen);
        ((AppInitiliser) this.getApplication()).getAppInjectorDependencyComponent().inject(this);

        app_pref = PreferenceManager.getDefaultSharedPreferences(this);
        int PlayerNo = Integer.parseInt(app_pref.getString("PlayerNo", "Null"));

        PCharacter[] players = presenter.getPlayers(PlayerNo);

        final Events[] listOfEvents = presenter.AddEvents(players);

        editor = app_pref.edit();
        editor.putInt("noOfEvents", listOfEvents.length).apply();


        for (int i = 0; i < listOfEvents.length; i++) {
            Log.d("Temp Debug", (Integer.toString(listOfEvents[i].getEventId())));
            Log.d("Temp Debug", listOfEvents[i].getName());
        }

        presenter.DeleteEvents();
        presenter.SaveEvents(listOfEvents);

        Intent intent = new Intent(this, MainGameActivity.class);
        startActivity(intent);
    }
}
