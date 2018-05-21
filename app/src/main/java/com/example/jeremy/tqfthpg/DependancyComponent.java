package com.example.jeremy.tqfthpg;

import com.example.jeremy.tqfthpg.CharacterScreen.CharacterActivity;
import com.example.jeremy.tqfthpg.DifficultyScreen.DiffFragment;
import com.example.jeremy.tqfthpg.DifficultyScreen.MainActivity;
import com.example.jeremy.tqfthpg.NameScreen.NameActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppInjector.class})
public interface DependancyComponent {
    void inject(MainActivity mainActivity);
    void inject(CharacterActivity characterActivity);
    void inject(DiffFragment mainFragment);
    void inject(NameActivity nameActivity);
}
