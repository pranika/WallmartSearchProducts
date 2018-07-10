package products.walmart.com.walmartapp.dataSource;

import android.net.Uri;
import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Single;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import products.walmart.com.walmartapp.error.NoRecommendationsFound;
import products.walmart.com.walmartapp.model.Product;
import products.walmart.com.walmartapp.model.RecommendedProducts;
import products.walmart.com.walmartapp.model.SearchResponse;

public class WalmartDataSourceImpl implements WalmartContract {

    OkHttpClient client;

    String apiKey;
    JsonAdapter<SearchResponse> productResponseAdapter;
    JsonAdapter<Product> productJsonAdapter;
    JsonAdapter<List<Product>> recommendationListAdapter;

    public WalmartDataSourceImpl(String apiKey, OkHttpClient client){

        this.apiKey = apiKey;
        this.client = client;
        Moshi moshi = new Moshi.Builder().build();
        productResponseAdapter = moshi.adapter(SearchResponse.class);
        productJsonAdapter=moshi.adapter(Product.class);

        Type type = Types.newParameterizedType(List.class, Product.class);
        recommendationListAdapter = moshi.adapter(type);
    }


    @Override
    public Single<SearchResponse> getRecentProducts(String prod_name) {
        // String url = "http://api.walmartlabs.com/v1/search?apiKey=eahh6ej8jmz8q3dzx5m572z3&query=" + search;

        String url = Uri.parse("http://api.walmartlabs.com/v1/search")
                .buildUpon()
                .appendQueryParameter("apiKey", apiKey)
                .appendQueryParameter("query", prod_name)
                .build().toString();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .get()
                .build();
        return Single.create(emitter -> {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("response", e.toString());
                    e.printStackTrace();
                    if(e != null) emitter.onError(e);
                }

                @Override
                public void onResponse(Call call, final okhttp3.Response response) {
                    try {
                        if (response != null && response.isSuccessful()) {
                            String resp = response.body().string();
                            Log.d("Test", resp);
                            emitter.onSuccess(
                                    productResponseAdapter.fromJson(resp)
                            );
                        } else {
                            emitter.onError(new IllegalStateException());
                        }
                    } catch(IOException e) {
                        emitter.onError(e);
                    }
                }
            });
        });
    }

    @Override
    public Single<Product> getProduct(int prod_id) {
        //http://api.walmartlabs.com/v1/items/12417832?apiKey=v6t3es5tugbp6jveh37hzy6x
        String url = Uri.parse("http://api.walmartlabs.com/v1/items/"+prod_id)
                .buildUpon()
                .appendQueryParameter("apiKey", apiKey)
                .build().toString();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .get()
                .build();
        return Single.create(emitter -> {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("response", e.toString());
                    e.printStackTrace();
                    if(e != null) emitter.onError(e);
                }

                @Override
                public void onResponse(Call call, final okhttp3.Response response) {
                    try {
                        if (response != null && response.isSuccessful()) {
                            String resp = response.body().string();
                            Log.d("Test", resp);
                            emitter.onSuccess(
                                    productJsonAdapter.fromJson(resp)
                            );
                        } else {
                            emitter.onError(new IllegalStateException());
                        }
                    } catch(IOException e) {
                        emitter.onError(e);
                    }
                }
            });
        });
    }

    @Override
    public Single<List<Product>> getRecommendations(String prod_id) {

        // http://api.walmartlabs.com/v1/nbp?apiKey=v6t3es5tugbp6jveh37hzy6x&itemId=36904791
        String url = Uri.parse("http://api.walmartlabs.com/v1/nbp")
                .buildUpon()
                .appendQueryParameter("apiKey", apiKey)
                .appendQueryParameter("itemId",prod_id)
                .build().toString();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .get()
                .build();
        return Single.create(emitter -> {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("response", e.toString());
                    e.printStackTrace();
                    if(e != null) emitter.onError(e);
                }

                @Override
                public void onResponse(Call call, final okhttp3.Response response) {
                    try {
                        if (response != null && response.isSuccessful()) {
                            String resp = response.body().string();
                            Log.d("Test", resp);

                            if (resp!=null && !resp.contains("errors")) {
                                List<Product> productList = recommendationListAdapter.fromJson(resp);
                                emitter.onSuccess(productList);
                                return;
                            }
                            else{
                                emitter.onError(new NoRecommendationsFound());
                                return;
                            }
                        }
                        emitter.onError(new IllegalStateException());
                    } catch(IOException e) {
                        emitter.onError(e);
                    }
                }
            });
        });
    }
}
