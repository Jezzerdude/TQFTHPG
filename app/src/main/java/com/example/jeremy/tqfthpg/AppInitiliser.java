package com.example.jeremy.tqfthpg;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AppInitiliser extends Application {

    private DependancyComponent dependencyComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //when writing the below line, put Dagger+[Name of Interface].  The class will then auto-generate.
        dependencyComponent = DaggerDependancyComponent.builder().build();

        // The default RealmClass file is "default.realm" in Context.getFilesDir();
        // we'll change it to "myrealm.realm"
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm").build();
        Realm.setDefaultConfiguration(config);
    }

    public DependancyComponent getAppInjectorDependencyComponent() {
        return dependencyComponent;
    }
}
