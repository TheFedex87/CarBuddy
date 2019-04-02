package it.bytener.carbuddy.dagger;

import android.content.Context;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent {
    Picasso getPicasso();
}
