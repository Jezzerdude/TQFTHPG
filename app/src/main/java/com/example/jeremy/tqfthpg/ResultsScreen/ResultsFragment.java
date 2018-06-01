package com.example.jeremy.tqfthpg.ResultsScreen;

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
import android.widget.TextView;

import com.example.jeremy.tqfthpg.AppInitiliser;
import com.example.jeremy.tqfthpg.CharacterScreen.Model.PCharacter;
import com.example.jeremy.tqfthpg.DifficultyScreen.MainActivity;
import com.example.jeremy.tqfthpg.MainGame.MainGameActivity;
import com.example.jeremy.tqfthpg.MainGame.MainGameInterface;
import com.example.jeremy.tqfthpg.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ResultsFragment extends Fragment implements ResultsInterface.MainViewInterface{

    @BindView(R.id.newGameButton)
    Button newGameButton;
    @BindView(R.id.DrinkAmountTotal)
    TextView drinkTotalAmount;
    @BindView(R.id.P1DrinkAmount)
    TextView p1DrinkAmount;
    @BindView(R.id.P2DrinkAmount)
    TextView p2DrinkAmount;
    @BindView(R.id.P3DrinkAmount)
    TextView p3DrinkAmount;
    @BindView(R.id.P4DrinkAmount)
    TextView p4DrinkAmount;
    @BindView(R.id.P5DrinkAmount)
    TextView p5DrinkAmount;
    @BindView(R.id.P6DrinkAmount)
    TextView p6DrinkAmount;
    @BindView(R.id.P7DrinkAmount)
    TextView p7DrinkAmount;
    @BindView(R.id.P8DrinkAmount)
    TextView p8DrinkAmount;

    private OnFragmentInteractionListener mListener;
    SharedPreferences app_pref;

    @Inject
    ResultsInterface.MainPresenterInterface presenter;

    public ResultsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        ((AppInitiliser) getActivity().getApplication()).getAppInjectorDependencyComponent().inject(this);
        ButterKnife.bind(this, view);
        app_pref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        int PlayerNo = Integer.parseInt(app_pref.getString("PlayerNo", "Null"));

        newGameButton.setOnClickListener(onClickListener);
        PCharacter[] Players = presenter.getPlayers(PlayerNo);

        String P1Name = Players[0].getFullname();
        int P1Drinks = Players[0].getTotalDrinks();
        int P1Shots = Players[0].getTotalShots();
        p1DrinkAmount.setText(P1Name+", Total Drink Amount: "+P1Drinks+", Total Shot amount: "+P1Shots);
        String P2Name = Players[1].getFullname();
        int P2Drinks = Players[1].getTotalDrinks();
        int P2Shots = Players[1].getTotalShots();
        p2DrinkAmount.setText(P2Name+", Total Drink Amount: "+P2Drinks+", Total Shot amount: "+P2Shots);
        String P3Name = Players[2].getFullname();
        int P3Drinks = Players[2].getTotalDrinks();
        int P3Shots = Players[2].getTotalShots();
        p3DrinkAmount.setText(P3Name+", Total Drink Amount: "+P3Drinks+", Total Shot amount: "+P3Shots);

        if(Players.length>3){
            String P4Name = Players[3].getFullname();
            int P4Drinks = Players[3].getTotalDrinks();
            int P4Shots = Players[3].getTotalShots();
            p4DrinkAmount.setText(P4Name+", Total Drink Amount: "+P4Drinks+", Total Shot amount: "+P4Shots);
        }
        if(Players.length>4){
            String P5Name = Players[4].getFullname();
            int P5Drinks = Players[4].getTotalDrinks();
            int P5Shots = Players[4].getTotalShots();
            p5DrinkAmount.setText(P5Name+", Total Drink Amount: "+P5Drinks+", Total Shot amount: "+P5Shots);
        }
        if(Players.length>5){
            String P6Name = Players[5].getFullname();
            int P6Drinks = Players[5].getTotalDrinks();
            int P6Shots = Players[5].getTotalShots();
            p6DrinkAmount.setText(P6Name+", Total Drink Amount: "+P6Drinks+", Total Shot amount: "+P6Shots);
        }
        if(Players.length>6){
            String P7Name = Players[6].getFullname();
            int P7Drinks = Players[6].getTotalDrinks();
            int P7Shots = Players[6].getTotalShots();
            p7DrinkAmount.setText(P7Name+", Total Drink Amount: "+P7Drinks+", Total Shot amount: "+P7Shots);
        }
        if(Players.length>7){
            String P8Name = Players[7].getFullname();
            int P8Drinks = Players[7].getTotalDrinks();
            int P8Shots = Players[7].getTotalShots();
            p8DrinkAmount.setText(P8Name+", Total Drink Amount: "+P8Drinks+", Total Shot amount: "+P8Shots);
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
                case R.id.newGameButton:
                    OnButtonPressed();
                    break;

            }

        }

        void OnButtonPressed() {
            if (mListener != null) {
                    Intent intent = new Intent(getView().getContext(), MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
            }
        }
    };
}
