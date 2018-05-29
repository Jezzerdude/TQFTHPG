package com.example.jeremy.tqfthpg.MainGame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeremy.tqfthpg.AppInitiliser;
import com.example.jeremy.tqfthpg.CharacterScreen.Model.PCharacter;
import com.example.jeremy.tqfthpg.MainGame.Model2.Events;
import com.example.jeremy.tqfthpg.R;
import com.example.jeremy.tqfthpg.ResultsScreen.ResultsActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainGameFragment2 extends Fragment implements MainGameInterface.MainViewInterface {

    @BindView(R.id.eventImg)
    ImageView eventImg;
    @BindView(R.id.passOrFailText)
    TextView passOrFailText;
    @BindView(R.id.passOrFailResult)
    TextView passOrFailResult;
    @BindView(R.id.resultText)
    TextView resultText;
    @BindView(R.id.continueButton)
    Button continueButton;

    @Inject
    MainGameInterface.MainPresenterInterface presenter;

    private OnFragmentInteractionListener mListener;

    SharedPreferences app_pref;
    SharedPreferences.Editor editor;

    public MainGameFragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_game_fragment2, container, false);
        ((AppInitiliser) getActivity().getApplication()).getAppInjectorDependencyComponent().inject(this);

        ButterKnife.bind(this, view);

        app_pref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        int PlayerNo = Integer.parseInt(app_pref.getString("PlayerNo", "Null"));
        int GameState = app_pref.getInt("State",0);
        String OptionSelected = app_pref.getString("OptionSelected","option1");
        String Difficulty = app_pref.getString("Difficulty", "Null");

        Events[] gameEvents = presenter.getEvents(PlayerNo*2);

        String PF = presenter.rollResult(gameEvents[GameState]);

        eventImg.setImageResource(R.drawable.arrow);
        continueButton.setOnClickListener(onClickListener);

        PCharacter[] playerList = presenter.getPlayers(PlayerNo);
        presenter.OverloadResult(gameEvents[GameState],PF,OptionSelected, Difficulty);
        String LeaderForThisEvent = playerList[gameEvents[GameState].getLeadChar()].getFullname();

        if(gameEvents[GameState].getEventType().equals("Multiple")||gameEvents[GameState].getEventType().equals("SingleWithPlayer")) {
            resultText.setText(LeaderForThisEvent + ", " + gameEvents[GameState].getEventResult());
        }else{
            resultText.setText(gameEvents[GameState].getEventResult());
        }

        if(PF.equals("Pass")){
            passOrFailResult.setText("Passed!");
        }else{
            passOrFailResult.setText("Failed!");
        }

        if(gameEvents[GameState].getEventType().equals("Single")||gameEvents[GameState].getEventType().equals("SingleWithPlayer")){
            passOrFailText.setVisibility(View.GONE);
            passOrFailResult.setVisibility(View.GONE);
        }else{
            passOrFailText.setVisibility(View.VISIBLE);
            passOrFailResult.setVisibility(View.VISIBLE);
        }

        return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.continueButton:
                    OnButtonPressed();
                    break;

            }

        }

        void OnButtonPressed() {
            if (mListener != null) {

                app_pref = PreferenceManager.getDefaultSharedPreferences(getView().getContext());
                int GameState = app_pref.getInt("State",0);
                int EventNo = app_pref.getInt("noOfEvents", 0);

                if(EventNo-1 != GameState) {
                    int NewState = GameState + 1;
                    editor = app_pref.edit();
                    editor.putInt("State", NewState).apply();
                    editor.putInt("Result", 0).apply();
                    Intent intent = new Intent(getView().getContext(), MainGameActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getView().getContext(), ResultsActivity.class);
                    startActivity(intent);
                }
            }
        }
    };
}
