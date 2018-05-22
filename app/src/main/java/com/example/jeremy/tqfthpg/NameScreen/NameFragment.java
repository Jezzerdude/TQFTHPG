package com.example.jeremy.tqfthpg.NameScreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.jeremy.tqfthpg.CharacterScreen.CharacterActivity;
import com.example.jeremy.tqfthpg.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NameFragment extends Fragment {

    @BindView(R.id.p1text)
    TextView p1text;
    @BindView(R.id.p1Name)
    TextView p1Name;
    @BindView(R.id.p2text)
    TextView p2text;
    @BindView(R.id.p2Name)
    TextView p2Name;
    @BindView(R.id.p3text)
    TextView p3text;
    @BindView(R.id.p3Name)
    TextView p3Name;
    @BindView(R.id.p4text)
    TextView p4text;
    @BindView(R.id.p4Name)
    TextView p4Name;
    @BindView(R.id.p5text)
    TextView p5text;
    @BindView(R.id.p5Name)
    TextView p5Name;
    @BindView(R.id.p6text)
    TextView p6text;
    @BindView(R.id.p6Name)
    TextView p6Name;
    @BindView(R.id.p7text)
    TextView p7text;
    @BindView(R.id.p7Name)
    TextView p7Name;
    @BindView(R.id.p8text)
    TextView p8text;
    @BindView(R.id.p8Name)
    TextView p8Name;
    @BindView(R.id.genChars)
    Button genChars;

    private OnFragmentInteractionListener mListener;
    SharedPreferences app_pref;
    SharedPreferences.Editor editor;

    public NameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_name, container, false);
        ButterKnife.bind(this, view);
        app_pref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        String PlayerNo = app_pref.getString("PlayerNo", "Null");
        hideUneededNames(Integer.parseInt(PlayerNo));
        genChars.setOnClickListener(onClickListener);

        return view;
    }

    private void hideUneededNames(int NoOfPlayers) {
        if(NoOfPlayers<=7){
            p8text.setVisibility(View.GONE);
            p8Name.setVisibility(View.GONE);
        }
        if(NoOfPlayers<=6){
            p7text.setVisibility(View.GONE);
            p7Name.setVisibility(View.GONE);
        }
        if(NoOfPlayers<=5){
            p6text.setVisibility(View.GONE);
            p6Name.setVisibility(View.GONE);
        }
        if(NoOfPlayers<=4){
            p5text.setVisibility(View.GONE);
            p5Name.setVisibility(View.GONE);
        }
        if(NoOfPlayers<=3){
            p4text.setVisibility(View.GONE);
            p4Name.setVisibility(View.GONE);
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
                case R.id.genChars:
                    OnButtonPressed();
                    break;
            }
        }

        void OnButtonPressed() {
            String PlayerNo = app_pref.getString("PlayerNo", "Null");
            if (mListener != null) {
                if(ableToContinue(Integer.parseInt(PlayerNo))){
                    editor = app_pref.edit();
                    editor.putString("Name1", p1Name.getText().toString()).apply();
                    editor.putString("Name2", p2Name.getText().toString()).apply();
                    editor.putString("Name3", p3Name.getText().toString()).apply();
                    if(Integer.parseInt(PlayerNo)>3){
                        editor.putString("Name4", p4Name.getText().toString()).apply();
                    }
                    if(Integer.parseInt(PlayerNo)>4){
                        editor.putString("Name5", p5Name.getText().toString()).apply();
                    }
                    if(Integer.parseInt(PlayerNo)>5){
                        editor.putString("Name6", p6Name.getText().toString()).apply();
                    }
                    if(Integer.parseInt(PlayerNo)>6){
                        editor.putString("Name7", p7Name.getText().toString()).apply();
                    }
                    if(Integer.parseInt(PlayerNo)>7){
                        editor.putString("Name8", p8Name.getText().toString()).apply();
                    }

                    Intent intent = new Intent(getView().getContext(), CharacterActivity.class);
                    startActivity(intent);
                }
            }
        }
    };

    private boolean ableToContinue(int NoOfPlayers) {
        int temp1 = 0;
        if(NoOfPlayers>=8){
            if((!p8Name.getText().toString().equals(""))){
                temp1++;
            }
        }
        if(NoOfPlayers>=7){
            if((!p7Name.getText().toString().equals(""))){
                temp1++;
            }
        }
        if(NoOfPlayers>=6){
            if((!p6Name.getText().toString().equals(""))){
                temp1++;
            }
        }
        if(NoOfPlayers>=5){
            if((!p5Name.getText().toString().equals(""))){
                temp1++;
            }
        }
        if(NoOfPlayers>=4){
            if((!p4Name.getText().toString().equals(""))){
                temp1++;
            }
        }
        if(NoOfPlayers>=3){
            if((!p3Name.getText().toString().equals(""))){
                temp1++;
            }
        }
        if(NoOfPlayers>=2){
            if((!p2Name.getText().toString().equals(""))){
                temp1++;
            }
        }
        if(NoOfPlayers>=1){
            if((!p1Name.getText().toString().equals(""))){
                temp1++;
            }
        }
        if(temp1 == NoOfPlayers){
            return true;
        }
        return false;
    }
}
