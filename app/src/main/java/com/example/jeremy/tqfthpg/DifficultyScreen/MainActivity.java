package com.example.jeremy.tqfthpg.DifficultyScreen;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jeremy.tqfthpg.AppInitiliser;
import com.example.jeremy.tqfthpg.R;

import javax.inject.Inject;



public class MainActivity extends AppCompatActivity implements DiffInterface.mainActInterface, DiffFragment.OnFragmentInteractionListener {

    @Inject
    DiffInterface.presenterInterface presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((AppInitiliser) getApplication()).getAppInjectorDependencyComponent().inject(this);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragDiff, presenter.getFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
