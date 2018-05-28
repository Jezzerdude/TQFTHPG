package com.example.jeremy.tqfthpg;

import com.example.jeremy.tqfthpg.CharacterScreen.CharacterFragment;
import com.example.jeremy.tqfthpg.CharacterScreen.CharacterInterface;
import com.example.jeremy.tqfthpg.CharacterScreen.CharacterPresenter;
import com.example.jeremy.tqfthpg.DifficultyScreen.DiffFragment;
import com.example.jeremy.tqfthpg.DifficultyScreen.DiffInterface;
import com.example.jeremy.tqfthpg.DifficultyScreen.DiffPresenter;
import com.example.jeremy.tqfthpg.MainGame.MainGameFragment;
import com.example.jeremy.tqfthpg.MainGame.MainGameFragment2;
import com.example.jeremy.tqfthpg.MainGame.MainGameInterface;
import com.example.jeremy.tqfthpg.MainGame.MainGameLoadingScreenActivity;
import com.example.jeremy.tqfthpg.MainGame.MainGamePresenter;
import com.example.jeremy.tqfthpg.NameScreen.NameInterface;
import com.example.jeremy.tqfthpg.NameScreen.NamePresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppInjector {
    @Provides
    @Singleton
    DiffInterface.presenterInterface provideMainContractPresenter(){
        return new DiffPresenter();
    }

    @Provides
    @Singleton
    CharacterInterface.presenterInterface providecharContractPresenter(){
        return new CharacterPresenter();
    }

    @Provides
    @Singleton
    MainGameInterface.MainPresenterInterface provideMainGamePresenter(){
        return new MainGamePresenter();
    }

    @Provides
    @Singleton
    NameInterface.presenterInterface providenameContractPresenter(){
        return new NamePresenter();
    }

    @Provides
    @Singleton
    DiffInterface.viewInterface provideMainFragment(){
        return new DiffFragment();
    }

    @Provides
    @Singleton
    CharacterInterface.viewInterface provideCharFragment(){
        return new CharacterFragment();
    }

    @Provides
    @Singleton
    MainGameInterface.MainViewInterface provideMGFragment(){
        return new MainGameFragment();
    }

    @Provides
    @Singleton
    MainGameInterface.MainViewInterface provideMGFragment2(){
        return new MainGameFragment2();
    }

    @Provides
    @Singleton
    MainGameInterface.MainViewInterface provideLoadingActivity() {
        return new MainGameLoadingScreenActivity();
    }


}
