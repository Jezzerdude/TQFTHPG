package com.example.jeremy.tqfthpg.ResultsScreen;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jeremy.tqfthpg.AppInitiliser;
import com.example.jeremy.tqfthpg.MainGame.MainGameInterface;
import com.example.jeremy.tqfthpg.R;

import javax.inject.Inject;

public class ResultsActivity extends AppCompatActivity implements ResultsInterface.MainViewInterface, ResultsFragment.OnFragmentInteractionListener {

    @Inject
    ResultsInterface.MainPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ((AppInitiliser) getApplication()).getAppInjectorDependencyComponent().inject(this);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragResults, presenter.getFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
