package it.bytener.carbuddy.dagger;


import android.content.Context;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    static Picasso providePicasso(){
        return Picasso.get();
    }
}
