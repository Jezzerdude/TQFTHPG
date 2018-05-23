package com.example.jeremy.tqfthpg.CharacterScreen;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jeremy.tqfthpg.AppInitiliser;
import com.example.jeremy.tqfthpg.DifficultyScreen.DiffInterface;
import com.example.jeremy.tqfthpg.R;

import javax.inject.Inject;

public class CharacterActivity extends AppCompatActivity implements CharacterInterface.viewInterface, CharacterFragment.OnFragmentInteractionListener {

    @Inject
    CharacterInterface.presenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        ((AppInitiliser) getApplication()).getAppInjectorDependencyComponent().inject(this);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragChar, presenter.getFragment());
        fragmentTransaction.commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
