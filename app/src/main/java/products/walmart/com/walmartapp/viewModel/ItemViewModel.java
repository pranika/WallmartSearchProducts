package products.walmart.com.walmartapp.viewModel;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import products.walmart.com.walmartapp.WalmartApplication;
import products.walmart.com.walmartapp.dataSource.WalmartContract;
import products.walmart.com.walmartapp.disposeLeaks.DisposableManager;
import products.walmart.com.walmartapp.error.NoRecommendationsFound;
import products.walmart.com.walmartapp.model.Product;
import products.walmart.com.walmartapp.model.RecommendedProducts;
import products.walmart.com.walmartapp.model.SearchResponse;

public class ItemViewModel extends AndroidViewModel {

    @Inject
    WalmartContract itemDataContract;

    private MutableLiveData<List<Product>> allItems;
    private MutableLiveData<Product> returnProduct;
    private MutableLiveData<List<Product>> returnRecommendations;
    List<Product> tenItems;
    List<Product> tenRecommendations;



    public ItemViewModel(Application application) {
        super(application);
        ((WalmartApplication) application).mDaggerComponent.inject(this);

        allItems = new MutableLiveData<>();
        returnProduct=new MutableLiveData<>();
        returnRecommendations=new MutableLiveData<>();
        returnRecommendations.setValue(new ArrayList<>());
        allItems.setValue(new ArrayList<>());

    }


    public LiveData<List<Product>> getAllItems() {
        return allItems;
    }

    public LiveData<Product> getProduct() {
        return returnProduct;
    }
    public LiveData<List<Product>> getRecommendations() {
        return returnRecommendations;
    }


    public void loadProducts(String prod_name) {
        itemDataContract.getRecentProducts(prod_name)
               .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<SearchResponse>() {
            @Override
            public void onSubscribe(Disposable s) {
                DisposableManager.add(s);

            }

            @Override
            public void onSuccess(SearchResponse item) {
                int count=0;
                tenItems=new ArrayList<>();
                for(Product p: item.getItems()){
                    if(count++<10) tenItems.add(p);
                }

               allItems.setValue(tenItems);

            }

            @Override
            public void onError(Throwable e) {

            }
        });


    }

    public void returnProduct(int prod_id) {
        itemDataContract.getProduct(prod_id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Product>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        DisposableManager.add(d);
                    }

                    @Override
                    public void onSuccess(Product item) {

                        returnProduct.setValue(item);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });


    }


    public void loadRecommendations(String prod_id) {
        itemDataContract.getRecommendations(prod_id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Product>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        DisposableManager.add(d);

                    }

                    @Override
                    public void onSuccess(List<Product> items) {
                        int count=0;
                        tenRecommendations=new ArrayList<>();
                        for(Product p: items){
                            if(count++<10) tenRecommendations.add(p);
                        }

                        returnRecommendations.setValue(tenRecommendations);

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d("no recommendation found","No Recommendation found");

                    }
                });


    }

    @Override
    protected void onCleared() {
        super.onCleared();
        DisposableManager.dispose();
    }
}


