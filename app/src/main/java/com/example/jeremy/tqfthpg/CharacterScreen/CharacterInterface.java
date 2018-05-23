package com.example.jeremy.tqfthpg.CharacterScreen;

import android.app.Fragment;

import com.example.jeremy.tqfthpg.CharacterScreen.Model.PCharacter;

public interface CharacterInterface {
    interface viewInterface{

    }
    interface presenterInterface{

        CharacterFragment getFragment();
        PCharacter[] genChars(int NoOfChars, String[] FirstNames);
        String genSurName(String race);
        String genRace();
        String genClass();
        String genWeakness();
        String genDescription(String race, String PClass,String Name,String Weakness);
        void SaveCharacters(PCharacter[] chars);
        void DeleteCharacters();
    }
}
