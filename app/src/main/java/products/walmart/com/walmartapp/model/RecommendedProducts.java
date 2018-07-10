package products.walmart.com.walmartapp.model;

import com.squareup.moshi.Json;

import java.util.List;

public class RecommendedProducts {

    @Json(name = "items")
    private List<Product> items = null;

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> products) {
        this.items = products;
    }
}
