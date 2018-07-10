package products.walmart.com.walmartapp.di.components;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import products.walmart.com.walmartapp.di.modules.ContextModule;
import products.walmart.com.walmartapp.di.modules.NetworkModule;

import products.walmart.com.walmartapp.di.modules.ServiceModule;
import products.walmart.com.walmartapp.scopes.ApplicationScope;
import products.walmart.com.walmartapp.viewModel.ItemViewModel;
import products.walmart.com.walmartapp.views.ProductFragment;

@ApplicationScope
@Component(modules = {
        NetworkModule.class,
        ServiceModule.class,
        ContextModule.class

})
public interface AppComponent {


    void inject(ItemViewModel itemViewModel);
    void inject(ProductFragment fragment);


}