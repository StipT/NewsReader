package com.example.factorynews.di.component;


import com.example.factorynews.MyApplication;
import com.example.factorynews.di.module.ActivityModule;
import com.example.factorynews.di.module.AppModule;
import com.example.factorynews.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;


@Singleton
@Component(modules = {
        AppModule.class,
        NetModule.class,
        ActivityModule.class,
        AndroidInjectionModule.class

})

public interface AppComponent {

    void inject(MyApplication myApplication);

}