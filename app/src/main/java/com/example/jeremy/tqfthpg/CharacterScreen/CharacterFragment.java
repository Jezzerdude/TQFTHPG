package com.example.jeremy.tqfthpg.CharacterScreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jeremy.tqfthpg.R;


public class CharacterFragment extends Fragment implements CharacterInterface.viewInterface {


    private OnFragmentInteractionListener mListener;

    SharedPreferences app_pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character, container, false);

        app_pref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        String PlayerNo = app_pref.getString("PlayerNo", "Null");
        String Difficulty = app_pref.getString("Difficulty", "Null");
        Toast.makeText(view.getContext(), "Players "+PlayerNo, Toast.LENGTH_SHORT).show();
        Toast.makeText(view.getContext(), "Dif "+Difficulty, Toast.LENGTH_SHORT).show();

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
}
