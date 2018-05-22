package com.example.jeremy.tqfthpg.CharacterScreen;

import com.example.jeremy.tqfthpg.CharacterScreen.Model.PCharacter;
import com.example.jeremy.tqfthpg.DifficultyScreen.DiffFragment;

import java.util.Random;

public class CharacterPresenter implements CharacterInterface.presenterInterface {

    CharacterFragment characterFragment;
    String[] UsedNames = new String[8];
    String[] UsedRaces = new String[8];

    @Override
    public CharacterFragment getFragment() {
        characterFragment = new CharacterFragment();
        return characterFragment;
    }

    @Override
    public PCharacter[] genChars(int NoOfChars, String[] FirstNames) {
        PCharacter[] CharacterArray = new PCharacter[NoOfChars];
        int Counter = 0;

        //Surname
        for(int i = 0; i < NoOfChars; i++){
            String TempRace = genRace();
            String TempSur = genSurName(TempRace);
                PCharacter character = new PCharacter(TempRace, "a", FirstNames[Counter], TempSur, FirstNames[Counter]+" "+TempSur, "a", "a");
                CharacterArray[i] = character;
                Counter++;
        }
        return CharacterArray;
    }

    @Override
    public String genSurName(String race) {
        String[] LastNameArray = {"One","Two","Three","Four","Five","Six","Seven","Eight"};
        String[] LastNameDwarf = {"Dwarf1","Dwarf2","Dwarf3","Dwarf4","Dwarf5","Dwarf6","Dwarf7","Dwarf8"};
        boolean used = false;
        int rand = (int)(Math.random() * ((7 - 0) + 1)) + 0;
        used = false;
        int Counter = 0;
        String[] ChosenArray = new String[8];
        if(race.equals("Dwarf")){
            ChosenArray = LastNameDwarf;
        }else{
            ChosenArray = LastNameArray;
        }

        for(int u=0; u<UsedNames.length;u++){
            if(UsedNames[u]!=null){
                Counter++;
            }

            if(ChosenArray[rand].equals(UsedNames[u])){
                used = true;
                u=UsedNames.length;
            }else{
                used = false;
            }
        }
        if(!used) {
            UsedNames[Counter] = ChosenArray[rand];
            return ChosenArray[rand];
        }else {
            return genSurName(race);
        }
    }

    @Override
    public String genRace() {
        String[] RaceArray = {"Human","Elf","Dwarf","Lizard","Changeling","Goblin","Ogre","Wolfborn"};
        boolean used = false;
        int rand = (int)(Math.random() * 8);
        used = false;
        int Counter = 0;

        for(int u=0; u<UsedRaces.length;u++){
            if(UsedRaces[u]!=null){
                Counter++;
            }

            if(RaceArray[rand].equals(UsedRaces[u])){
                used = true;
                u=UsedRaces.length;
            }else{
                used = false;
            }
        }
        if(!used) {
            UsedRaces[Counter] = RaceArray[rand];
            return RaceArray[rand];
        }else {
            return genRace();
        }
    }
}
