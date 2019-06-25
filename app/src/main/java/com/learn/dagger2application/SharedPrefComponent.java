package com.learn.dagger2application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Safa Amin on 24/06/2019.
 */
@Singleton
@Component(modules = {SharedPrefModule.class})
public interface SharedPrefComponent {

    void inject(MainActivity activity);
}
