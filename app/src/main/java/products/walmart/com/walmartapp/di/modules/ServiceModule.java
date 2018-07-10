package products.walmart.com.walmartapp.di.modules;




import android.content.Context;

import com.squareup.moshi.Moshi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import okhttp3.OkHttpClient;
import products.walmart.com.walmartapp.dataSource.WalmartContract;
import products.walmart.com.walmartapp.dataSource.WalmartDataSourceImpl;


import products.walmart.com.walmartapp.scopes.ApplicationScope;


@Module
public class ServiceModule {

    @ApplicationScope
    @Provides

    public WalmartContract walmartDataSourceManager(OkHttpClient client) {
        return new WalmartDataSourceImpl("v6t3es5tugbp6jveh37hzy6x", client);
    }






}