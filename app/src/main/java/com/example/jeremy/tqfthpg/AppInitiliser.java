package com.example.jeremy.tqfthpg;

import android.app.Application;

public class AppInitiliser extends Application {

    private DependancyComponent dependencyComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //when writing the below line, put Dagger+[Name of Interface].  The class will then auto-generate.
        dependencyComponent = DaggerDependancyComponent.builder().build();
    }

    public DependancyComponent getAppInjectorDependencyComponent() {
        return dependencyComponent;
    }
}
