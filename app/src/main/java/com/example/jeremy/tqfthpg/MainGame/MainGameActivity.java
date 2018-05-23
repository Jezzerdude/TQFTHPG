package com.example.jeremy.tqfthpg.MainGame;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jeremy.tqfthpg.AppInitiliser;
import com.example.jeremy.tqfthpg.CharacterScreen.CharacterInterface;
import com.example.jeremy.tqfthpg.R;

import javax.inject.Inject;

public class MainGameActivity extends AppCompatActivity implements MainGameInterface.MainViewInterface, MainGameFragment.OnFragmentInteractionListener {

    @Inject
    MainGameInterface.MainPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        ((AppInitiliser) getApplication()).getAppInjectorDependencyComponent().inject(this);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragMainGame, presenter.getFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
