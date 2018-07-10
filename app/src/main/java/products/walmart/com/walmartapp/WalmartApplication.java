package products.walmart.com.walmartapp;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import products.walmart.com.walmartapp.di.components.AppComponent;
import products.walmart.com.walmartapp.di.components.DaggerAppComponent;
import products.walmart.com.walmartapp.di.modules.ContextModule;
import timber.log.Timber;

public class WalmartApplication extends Application {

    public AppComponent mDaggerComponent;
    private static Context context;

//    @Override
    public void onCreate() {
        super.onCreate();
        WalmartApplication.context = getApplicationContext();

        Timber.plant(new Timber.DebugTree());

//        if(!LeakCanary.isInAnalyzerProcess(this)) {
//            refWatcher = LeakCanary.install(this);
//        }
        Stetho.initializeWithDefaults(this);

        mDaggerComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .build();

    }

//    public static RefWatcher getRefWatcher(Context context) {
//        WalmartApplication application = (WalmartApplication) context.getApplicationContext();
//        return application.refWatcher;
//    }
//
//    private RefWatcher refWatcher;
}
