package products.walmart.com.walmartapp.model;

import com.squareup.moshi.Json;

import java.util.List;

public class SearchResponse {

    @Json(name = "query")
    private String query;
    @Json(name = "sort")
    private String sort;
    @Json(name = "responseGroup")
    private String responseGroup;
    @Json(name = "totalResults")
    private Integer totalResults;
    @Json(name = "start")
    private Integer start;
    @Json(name = "numItems")
    private Integer numItems;
    @Json(name = "items")
    private List<Product> items = null;
    @Json(name = "facets")
    private List<Object> facets = null;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getResponseGroup() {
        return responseGroup;
    }

    public void setResponseGroup(String responseGroup) {
        this.responseGroup = responseGroup;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getNumItems() {
        return numItems;
    }

    public void setNumItems(Integer numItems) {
        this.numItems = numItems;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> products) {
        this.items = products;
    }

    public List<Object> getFacets() {
        return facets;
    }

    public void setFacets(List<Object> facets) {
        this.facets = facets;
    }

}
