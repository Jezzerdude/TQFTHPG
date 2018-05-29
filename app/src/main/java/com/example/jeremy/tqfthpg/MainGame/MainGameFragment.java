package com.example.jeremy.tqfthpg.MainGame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeremy.tqfthpg.AppInitiliser;
import com.example.jeremy.tqfthpg.CharacterScreen.CharacterInterface;
import com.example.jeremy.tqfthpg.CharacterScreen.Model.PCharacter;
import com.example.jeremy.tqfthpg.Instructions.InstructionActivity;
import com.example.jeremy.tqfthpg.MainGame.Model2.Events;
import com.example.jeremy.tqfthpg.NameScreen.NameActivity;
import com.example.jeremy.tqfthpg.R;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainGameFragment extends Fragment implements MainGameInterface.MainViewInterface {

    @BindView(R.id.eventImg)
    ImageView eventImg;
    @BindView(R.id.desc)
    TextView desc;
    @BindView(R.id.op1)
    TextView op1;
    @BindView(R.id.op2)
    TextView op2;
    @BindView(R.id.op3)
    TextView op3;
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;

    private OnFragmentInteractionListener mListener;
    SharedPreferences app_pref;
    SharedPreferences.Editor editor;

    @Inject
    MainGameInterface.MainPresenterInterface presenter;

    public MainGameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_game, container, false);
        ((AppInitiliser) getActivity().getApplication()).getAppInjectorDependencyComponent().inject(this);
        ButterKnife.bind(this, view);

        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
        button3.setOnClickListener(onClickListener);

        app_pref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        int PlayerNo = Integer.parseInt(app_pref.getString("PlayerNo", "Null"));
        String Difficulty = app_pref.getString("Difficulty", "Null");
        int GameState = app_pref.getInt("State",0);

        Events[] gameEvents = presenter.getEvents(PlayerNo*2);
        PCharacter[] playerList = presenter.getPlayers(PlayerNo);

        if(gameEvents[GameState].getName().equals("startingTown")){
            eventImg.setImageResource(R.drawable.volcano);
        }else if(gameEvents[GameState].getName().equals("Volcano")){
            eventImg.setImageResource(R.drawable.volcano);
        }else if(gameEvents[GameState].getName().equals("Lightning Strike")){
            eventImg.setImageResource(R.drawable.volcano);
        }else if(gameEvents[GameState].getName().equals("Rock Slide")){
            eventImg.setImageResource(R.drawable.volcano);
        }else if(gameEvents[GameState].getName().equals("Cliff walk")){
            eventImg.setImageResource(R.drawable.volcano);
        }else if(gameEvents[GameState].getName().equals("Bar Antics")){
            eventImg.setImageResource(R.drawable.volcano);
        }else if(gameEvents[GameState].getName().equals("The Djinn")){
            eventImg.setImageResource(R.drawable.volcano);
        }else if(gameEvents[GameState].getName().equals("The Holy Coaster")){
            eventImg.setImageResource(R.drawable.volcano);
        }else if(gameEvents[GameState].getName().equals("Attacked by a rogue Squirrel")){
            eventImg.setImageResource(R.drawable.volcano);
        }else if(gameEvents[GameState].getName().equals("Bandit attack")){
            eventImg.setImageResource(R.drawable.volcano);
        }else if(gameEvents[GameState].getName().equals("Attacked by the Evil Knight")){
            eventImg.setImageResource(R.drawable.volcano);
        }else if(gameEvents[GameState].getName().equals("Attacked by a Swarm of Beetles")){
            eventImg.setImageResource(R.drawable.volcano);
        }else if(gameEvents[GameState].getName().equals("Crossing the rope bridge")){
            eventImg.setImageResource(R.drawable.volcano);
        }else if(gameEvents[GameState].getName().equals("Entering the Deep")){
            eventImg.setImageResource(R.drawable.volcano);
        }else if(gameEvents[GameState].getName().equals("Injury")){
            eventImg.setImageResource(R.drawable.volcano);
        }else if(gameEvents[GameState].getName().equals("Sickness")){
            eventImg.setImageResource(R.drawable.volcano);
        }else if(gameEvents[GameState].getName().equals("Curse of the Platypus!")){
            eventImg.setImageResource(R.drawable.volcano);
        }else if(gameEvents[GameState].getName().equals("THPG!")){
            eventImg.setImageResource(R.drawable.volcano);
        }
        else{
            eventImg.setImageResource(R.drawable.arrow);
        }


        if(gameEvents[GameState].getEventType().equals("Multiple")||gameEvents[GameState].getEventType().equals("SingleWithPlayer")) {
            String LeaderForThisEvent = playerList[gameEvents[GameState].getLeadChar()].getFullname();
            desc.setText(LeaderForThisEvent + ", " + gameEvents[GameState].getDescription());
        }else{
            desc.setText(gameEvents[GameState].getDescription());
        }



        op1.setText(gameEvents[GameState].getAct1());
        op2.setText(gameEvents[GameState].getAct2());
        op3.setText(gameEvents[GameState].getAct3());
        if(op2.getText().equals("")){
            op2.setVisibility(View.GONE);
            op3.setVisibility(View.GONE);
            button2.setVisibility(View.GONE);
            button3.setVisibility(View.GONE);
        }

        return view;
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
                case R.id.button1:
                    OnButtonPressed("option1");
                    break;
                case R.id.button2:
                    OnButtonPressed("option2");
                    break;
                case R.id.button3:
                    OnButtonPressed("option3");
                    break;

            }

        }

        void OnButtonPressed(String state) {
            if (mListener != null) {
                editor = app_pref.edit();
                editor.putInt("Result", 1).apply();
                editor.putString("OptionSelected", state).apply();
                Intent intent = new Intent(getView().getContext(), MainGameActivity.class);
                startActivity(intent);
            }
        }
    };
}
