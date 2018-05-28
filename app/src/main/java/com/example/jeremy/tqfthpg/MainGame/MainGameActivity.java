package com.example.jeremy.tqfthpg.MainGame;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.jeremy.tqfthpg.AppInitiliser;
import com.example.jeremy.tqfthpg.CharacterScreen.CharacterInterface;
import com.example.jeremy.tqfthpg.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainGameActivity extends AppCompatActivity implements MainGameInterface.MainViewInterface, MainGameFragment.OnFragmentInteractionListener, MainGameFragment2.OnFragmentInteractionListener {

    @BindView(R.id.fragMainGame)
    RelativeLayout frag1;
    @BindView(R.id.fragMainGame2)
    RelativeLayout frag2;

    SharedPreferences app_pref;

    @Inject
    MainGameInterface.MainPresenterInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        ((AppInitiliser) getApplication()).getAppInjectorDependencyComponent().inject(this);
        ButterKnife.bind(this, this);

        app_pref = PreferenceManager.getDefaultSharedPreferences(this);
        int Results = app_pref.getInt("Result", 0);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragMainGame, presenter.getFragment());
        fragmentTransaction.add(R.id.fragMainGame2, presenter.getFragment2());
        fragmentTransaction.commit();

        if(Results == 0){
            frag1.setVisibility(View.VISIBLE);
            frag2.setVisibility(View.GONE);
        }else{
            frag1.setVisibility(View.GONE);
            frag2.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
