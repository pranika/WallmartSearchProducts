package products.walmart.com.walmartapp.di.modules;

import android.content.Context;


import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.readystatesoftware.chuck.ChuckInterceptor;
import com.squareup.moshi.Moshi;
//import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;

import dagger.Module;
import dagger.Provides;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import products.walmart.com.walmartapp.scopes.ApplicationScope;
import timber.log.Timber;

@Module(includes = ContextModule.class)
public class NetworkModule {

    @Provides
    @ApplicationScope
    public HttpLoggingInterceptor loggingInterceptor() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override public void log(String message) {
                Timber.tag("OkHttp").d(message);
            }
        });
        return logging;
    }



    @Provides
    @ApplicationScope
    public Moshi moshi() {
        return new Moshi.Builder().build();
    }

    @Provides
    @ApplicationScope
    public OkHttpClient okHttpClient(Context context, HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new ChuckInterceptor(context))
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }



}