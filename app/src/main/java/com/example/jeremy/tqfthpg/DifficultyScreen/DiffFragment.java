package com.example.jeremy.tqfthpg.DifficultyScreen;

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

import com.example.jeremy.tqfthpg.CharacterScreen.CharacterActivity;
import com.example.jeremy.tqfthpg.Instructions.InstructionActivity;
import com.example.jeremy.tqfthpg.NameScreen.NameActivity;
import com.example.jeremy.tqfthpg.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DiffFragment extends Fragment implements DiffInterface.viewInterface{

    @BindView(R.id.startButton)
    Button startButton;
    @BindView(R.id.instButton)
    Button instButton;
    @BindView(R.id.imageView1)
    ImageView img1;
    @BindView(R.id.imageView2)
    ImageView img2;
    @BindView(R.id.imageView3)
    ImageView img3;
    @BindView(R.id.imageView4)
    ImageView img4;
    @BindView(R.id.playerNo)
    TextView playerno;
    @BindView(R.id.difficulty)
    TextView difsetting;

    int players=3;
    int Difficulty = 2;
    int[] PlayerNumberArray = new int[]{3,4,5,6,7,8};
    String[] DifficultyArray = new String[]{"Sober","Tipsy","Gazeboo'ed"};
    SharedPreferences app_pref;
    SharedPreferences.Editor editor;


    @Inject
    DiffInterface.presenterInterface presenter;

    private OnFragmentInteractionListener mListener;

    public DiffFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diff, container, false);
        ButterKnife.bind(this, view);

        startButton.setOnClickListener(onClickListener);
        instButton.setOnClickListener(onClickListener);
        img1.setOnClickListener(onClickListener);
        img2.setOnClickListener(onClickListener);
        img3.setOnClickListener(onClickListener);
        img4.setOnClickListener(onClickListener);

        app_pref = PreferenceManager.getDefaultSharedPreferences(view.getContext());

        playerno.setText(Integer.toString(PlayerNumberArray[players-3]));
        difsetting.setText(DifficultyArray[Difficulty-1]);

        return view;
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
        void onFragmentInteraction(Uri uri);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.startButton:
                    OnButtonPressed();
                    break;
                case R.id.instButton:
                    OnButtonPressed2();
                    break;
                case R.id.imageView1:
                    ScrollPlayersRight();
                    break;
                case R.id.imageView2:
                    ScrollPlayersLeft();
                    break;
                case R.id.imageView3:
                    ScrollDifficultyRight();
                    break;
                case R.id.imageView4:
                    ScrollDifficultyLeft();
                    break;
            }
            editor = app_pref.edit();
            editor.putString("PlayerNo", playerno.getText().toString()).apply();
            editor.putString("Difficulty", difsetting.getText().toString()).apply();
        }

        void OnButtonPressed() {
            if (mListener != null) {
                Intent intent = new Intent(getView().getContext(), NameActivity.class);
                startActivity(intent);
            }
        }
        void OnButtonPressed2() {
            if (mListener != null) {
                Intent intent = new Intent(getView().getContext(), InstructionActivity.class);
                startActivity(intent);
            }
        }
        void ScrollPlayersRight() {
            if (mListener != null) {
                if(players<8){
                    players++;
                    playerno.setText(Integer.toString(PlayerNumberArray[players-3]));
                }
            }
        }
        void ScrollPlayersLeft() {
            if (mListener != null) {
                if(players>3){
                    players--;
                    playerno.setText(Integer.toString(PlayerNumberArray[players-3]));
                }
            }
        }
        void ScrollDifficultyRight() {
            if (mListener != null) {
                if(Difficulty<3){
                    Difficulty++;
                    difsetting.setText(DifficultyArray[Difficulty-1]);
                }
            }
        }
        void ScrollDifficultyLeft() {
            if (mListener != null) {
                if(Difficulty>1){
                    Difficulty--;
                    difsetting.setText(DifficultyArray[Difficulty-1]);
                }
            }
        }
    };
}