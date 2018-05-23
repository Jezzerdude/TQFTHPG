package com.example.jeremy.tqfthpg.CharacterScreen;

import com.example.jeremy.tqfthpg.CharacterScreen.Model.PCharacter;
import com.example.jeremy.tqfthpg.DifficultyScreen.DiffFragment;

import java.util.Random;

public class CharacterPresenter implements CharacterInterface.presenterInterface {

    private CharacterFragment characterFragment;
    private String[] UsedNames = new String[8];
    private String[] UsedRaces = new String[8];
    private String[] UsedClasses = new String[8];
    private String[] UsedWeaknesses = new String[8];
    private String[] UsedDescription = new String[8];

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
        for (int i = 0; i < NoOfChars; i++) {
            String TempRace = genRace();
            String TempClass = genClass();
            String TempWeakness = genWeakness();
            String TempSur = genSurName(TempRace);
            String TempFullName = FirstNames[Counter] + " " + TempSur;
            String TempDesc = genDescription(TempRace,TempClass,TempFullName,TempWeakness);
            PCharacter character = new PCharacter(TempRace, TempClass, FirstNames[Counter], TempSur, TempFullName, TempWeakness, TempDesc);
            CharacterArray[i] = character;
            Counter++;
        }
        return CharacterArray;
    }

    @Override
    public String genSurName(String race) {
        String[] LastNameArray = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight"};
        String[] LastNameDwarf = {"Dwarf1", "Dwarf2", "Dwarf3", "Dwarf4", "Dwarf5", "Dwarf6", "Dwarf7", "Dwarf8"};
        boolean used = false;
        int rand = (int) (Math.random() * ((7 - 0) + 1)) + 0;
        used = false;
        int Counter = 0;
        String[] ChosenArray = new String[8];
        if (race.equals("Dwarf")) {
            ChosenArray = LastNameDwarf;
        } else {
            ChosenArray = LastNameArray;
        }

        for (int u = 0; u < UsedNames.length; u++) {
            if (UsedNames[u] != null) {
                Counter++;
            }

            if (ChosenArray[rand].equals(UsedNames[u])) {
                used = true;
                u = UsedNames.length;
            } else {
                used = false;
            }
        }
        if (!used) {
            UsedNames[Counter] = ChosenArray[rand];
            return ChosenArray[rand];
        } else {
            return genSurName(race);
        }
    }

    @Override
    public String genRace() {
        String[] RaceArray = {"Human", "Elf", "Dwarf", "Lizard", "Changeling", "Goblin", "Ogre", "Wolfborn"};
        boolean used = false;
        int rand = (int) (Math.random() * 8);
        used = false;
        int Counter = 0;

        for (int u = 0; u < UsedRaces.length; u++) {
            if (UsedRaces[u] != null) {
                Counter++;
            }

            if (RaceArray[rand].equals(UsedRaces[u])) {
                used = true;
                u = UsedRaces.length;
            } else {
                used = false;
            }
        }
        if (!used) {
            UsedRaces[Counter] = RaceArray[rand];
            return RaceArray[rand];
        } else {
            return genRace();
        }
    }

    @Override
    public String genClass() {
        String[] ClassArray = {"Fighter", "Cleric", "Wizard", "Theif", "Tinkerer", "Spiritualist", "Ranger", "Cannoneer"};
        boolean used = false;
        int rand = (int) (Math.random() * 8);
        used = false;
        int Counter = 0;

        for (int u = 0; u < UsedClasses.length; u++) {
            if (UsedClasses[u] != null) {
                Counter++;
            }

            if (ClassArray[rand].equals(UsedClasses[u])) {
                used = true;
                u = UsedClasses.length;
            } else {
                used = false;
            }
        }
        if (!used) {
            UsedClasses[Counter] = ClassArray[rand];
            return ClassArray[rand];
        } else {
            return genClass();
        }
    }

    @Override
    public String genWeakness() {
        String[] WeaknessArray = {"Heights", "Death", "Insects", "Trolls", "Bats", "Lava", "Social Situations", "Alcohol"};
        boolean used = false;
        int rand = (int) (Math.random() * 8);
        used = false;
        int Counter = 0;

        for (int u = 0; u < UsedWeaknesses.length; u++) {
            if (UsedWeaknesses[u] != null) {
                Counter++;
            }

            if (WeaknessArray[rand].equals(UsedWeaknesses[u])) {
                used = true;
                u = UsedWeaknesses.length;
            } else {
                used = false;
            }
        }
        if (!used) {
            UsedWeaknesses[Counter] = WeaknessArray[rand];
            return WeaknessArray[rand];
        } else {
            return genWeakness();
        }
    }

    @Override
    public String genDescription(String Race, String PClass,String Name,String Weakness) {
        String[] TempArray = {"A","B","C","D","E","F","G","H"};
        String[] DescArray = {
                Name + " is a "+Race+" "+PClass+" from the far north.  A long time ago a prized family heirloom was stolen " +
                        "from thier property.  "+Name+" is now seeking to replace the heirloom with a greater relic, the Holy" +
                        " Pint Glass.  Unfortunatly a recent event has caused "+Name+" to develop a crippling fear of "+ Weakness+"!",
                Name + " is a "+Race+" "+PClass+" from the eastern reaches. Deciding to seek adventure and travelling west the " +
                        "hero has found a place in the party seeking glory and the Holy Pint Glass.  They will bravely go wherever" +
                        " is required... well as no as there are no "+Weakness+"!",
                Name + " is a "+Race+" "+PClass+" from the Southlands.  A boggy reagion of swamps and marsh.  Growing up in this " +
                        "difficult land "+Name+" developed a fear of "+Weakness+". However "+Name+" is now seeking to prove themselves" +
                        " and so has journeyed out to find the Holy Pint Glass.",
                Name + " is a "+Race+" "+PClass+" from the West.  A lawless region of scum and villainy.  Using their knowledge of " +
                        "the criminal underworld "+Name+" started moving up the chain of command in a local gang.  However one day " +
                        "a deal with a rival gang went south and "+Name+" was forced to flee.  Looking for a way to seek vengance "+
                        Name+" now seeks the powers only the pint Glass can give them.  Lets hope there are/is no "+Weakness+" along " +
                        "the way!",
                Name + " is a "+Race+" "+PClass+" from the Big Bazaar!  Living the life of a trader was good.  Unfortuntaly after not giving " +
                        "a refund to a riled up customer "+Name+" was left scarred after he was attacked with "+Weakness+". In order to " +
                        "restore themself "+Name+" now seeks the Holy Pint Glass.",
                Name + " is a "+Race+" "+PClass+" from the village of Appleberry.  One day the village was pilaged by bandits leaving " +
                        Name+" with a fear of "+Weakness+" and burning the village to the ground.  Hearing of a mystical Holy Pint Glass " +
                        "that could restore the village "+Name+" has set off to find the answer!",
                Name + " is a "+Race+" "+PClass+" from SkyCity.  In SkyCity size is everything and "+Name+" was a bit on the short size for " +
                        "an "+Race+". Along with that they had a crippling fear of "+Weakness+". Hearing of a Magical Pint Glass that was said " +
                        "to make one taller. "+Name+" set off on a journey to become the tallest "+Race+" in SkyCity!",
                Name + " is a "+Race+" "+PClass+" from Volcano Mountain.  Life on Volcano Mountain was pretty easy except for one thing: " +
                        Weakness+". "+Name+" hated "+Weakness+". So one day they decided to flee Volcano Mountain and see the world. On their " +
                        "travels they heard tales of a Pint Glass with extraudinary powers.  Maybe it could even rid the world of "+Weakness+"!"};
        boolean used = false;
        int rand = (int) (Math.random() * 8);
        used = false;
        int Counter = 0;

        for (int u = 0; u < UsedDescription.length; u++) {
            if (UsedDescription[u] != null) {
                Counter++;
            }

            if (TempArray[rand].equals(UsedDescription[u])) {
                used = true;
                u = UsedDescription.length;
            } else {
                used = false;
            }
        }
        if (!used) {
            UsedDescription[Counter] = TempArray[rand];
            return DescArray[rand];
        } else {
            return genDescription(Race,PClass,Name,Weakness);
        }
    }

    @Override
    public void SaveCharacters(PCharacter[] chars) {

    }

    @Override
    public void DeleteCharacters() {

    }
}
