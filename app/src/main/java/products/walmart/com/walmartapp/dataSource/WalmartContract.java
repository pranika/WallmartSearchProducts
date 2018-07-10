package products.walmart.com.walmartapp.dataSource;

import java.util.List;

import io.reactivex.Single;
import products.walmart.com.walmartapp.model.Product;
import products.walmart.com.walmartapp.model.SearchResponse;

public interface WalmartContract {

    Single<SearchResponse> getRecentProducts(String prod_name);
    Single<Product> getProduct(int prod_id);
    Single<List<Product>> getRecommendations(String prod_id);
   // Single<SearchResponse> getRecentProducts(String prod_name);
}
