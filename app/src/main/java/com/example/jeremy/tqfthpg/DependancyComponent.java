package com.example.jeremy.tqfthpg;

import com.example.jeremy.tqfthpg.CharacterScreen.CharacterActivity;
import com.example.jeremy.tqfthpg.CharacterScreen.CharacterFragment;
import com.example.jeremy.tqfthpg.DifficultyScreen.DiffFragment;
import com.example.jeremy.tqfthpg.DifficultyScreen.MainActivity;
import com.example.jeremy.tqfthpg.MainGame.MainGameActivity;
import com.example.jeremy.tqfthpg.MainGame.MainGameFragment;
import com.example.jeremy.tqfthpg.MainGame.MainGameFragment2;
import com.example.jeremy.tqfthpg.MainGame.MainGameLoadingScreenActivity;
import com.example.jeremy.tqfthpg.NameScreen.NameActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppInjector.class})
public interface DependancyComponent {
    void inject(MainActivity mainActivity);
    void inject(CharacterActivity characterActivity);
    void inject(MainGameActivity mainGameActivity);
    void inject(DiffFragment mainFragment);
    void inject(NameActivity nameActivity);
    void inject(CharacterFragment characterFragment);
    void inject(MainGameFragment mainGameFragment);
    void inject(MainGameFragment2 mainGameFragment2);
    void inject(MainGameLoadingScreenActivity mainGameLoadingScreenActivity);
}
