package com.example.jeremy.tqfthpg.CharacterScreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jeremy.tqfthpg.AppInitiliser;
import com.example.jeremy.tqfthpg.CharacterScreen.Model.PCharacter;
import com.example.jeremy.tqfthpg.NameScreen.NameInterface;
import com.example.jeremy.tqfthpg.R;

import javax.inject.Inject;


public class CharacterFragment extends Fragment implements CharacterInterface.viewInterface {


    @Inject
    CharacterInterface.presenterInterface presenter;

    private OnFragmentInteractionListener mListener;

    SharedPreferences app_pref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character, container, false);
        ((AppInitiliser) getActivity().getApplication()).getAppInjectorDependencyComponent().inject(this);
        app_pref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        String PlayerNo = app_pref.getString("PlayerNo", "Null");
        String Difficulty = app_pref.getString("Difficulty", "Null");
        Toast.makeText(view.getContext(), "Players "+PlayerNo, Toast.LENGTH_SHORT).show();
        Toast.makeText(view.getContext(), "Dif "+Difficulty, Toast.LENGTH_SHORT).show();

        String[] firstnames = new String[Integer.parseInt(PlayerNo)];
        for(int i =0;i<Integer.parseInt(PlayerNo);i++){
            firstnames[i]= app_pref.getString("Name"+(i+1), "Null");
        }

        PCharacter[] newTemp = presenter.genChars(Integer.parseInt(PlayerNo), firstnames);

        for(int i =0; i<newTemp.length;i++){
            Log.e("tag","Lastname: "+newTemp[i].getFullname()+", Race: "+newTemp[i].getRace());
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
}
