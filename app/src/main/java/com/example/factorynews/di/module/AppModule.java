package com.example.factorynews.di.module;

import com.example.factorynews.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private MyApplication myApplication;

    public AppModule(MyApplication myApplication) {
        this.myApplication = myApplication;
    }


    @Singleton
    @Provides
    MyApplication providesApplication() {
        return myApplication;
    }

}