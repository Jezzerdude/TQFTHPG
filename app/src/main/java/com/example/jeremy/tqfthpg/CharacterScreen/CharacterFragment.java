package com.example.jeremy.tqfthpg.CharacterScreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jeremy.tqfthpg.AppInitiliser;
import com.example.jeremy.tqfthpg.CharacterScreen.Model.PCharacter;
import com.example.jeremy.tqfthpg.MainGame.MainGameActivity;
import com.example.jeremy.tqfthpg.NameScreen.NameInterface;
import com.example.jeremy.tqfthpg.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class CharacterFragment extends Fragment implements CharacterInterface.viewInterface {


    @Inject
    CharacterInterface.presenterInterface presenter;

    private OnFragmentInteractionListener mListener;

    SharedPreferences app_pref;

    RecyclerView recyclerView;
    CharacterAdapter characterAdapter;
    private List<PCharacter> charlist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character, container, false);
        ((AppInitiliser) getActivity().getApplication()).getAppInjectorDependencyComponent().inject(this);
        app_pref = PreferenceManager.getDefaultSharedPreferences(view.getContext());
        String PlayerNo = app_pref.getString("PlayerNo", "Null");
        String Difficulty = app_pref.getString("Difficulty", "Null");
        //Toast.makeText(view.getContext(), "Players "+PlayerNo, Toast.LENGTH_SHORT).show();
        //Toast.makeText(view.getContext(), "Dif "+Difficulty, Toast.LENGTH_SHORT).show();

        String[] firstnames = new String[Integer.parseInt(PlayerNo)];
        for(int i =0;i<Integer.parseInt(PlayerNo);i++){
            firstnames[i]= app_pref.getString("Name"+(i+1), "Null");
        }

        PCharacter[] PlayerChars = presenter.genChars(Integer.parseInt(PlayerNo), firstnames);


        //------------------------------------------------------------------------------------------
        //recyclerview
        charlist = new ArrayList<PCharacter>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        characterAdapter = new CharacterAdapter(charlist,view.getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(characterAdapter);

        for(int i =0;i<Integer.parseInt(PlayerNo);i++){
            charlist.add(PlayerChars[i]);
        }
        characterAdapter.notifyDataSetChanged();

        //------------------------------------------------------------------------------------------

        final FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getView().getContext(), MainGameActivity.class);
                startActivity(intent);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    fab.show();
                }
            }
        });

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
