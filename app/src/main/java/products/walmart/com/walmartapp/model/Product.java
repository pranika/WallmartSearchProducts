package products.walmart.com.walmartapp.model;

import java.io.Serializable;
import java.util.List;
import com.squareup.moshi.Json;

public class Product implements Serializable {

    @Json(name = "itemId")
    private Integer itemId;
    @Json(name = "parentItemId")
    private Integer parentItemId;
    @Json(name = "name")
    private String name;
    @Json(name = "msrp")
    private Double msrp;
    @Json(name = "salePrice")
    private Double salePrice;
    @Json(name = "upc")
    private String upc;
    @Json(name = "categoryPath")
    private String categoryPath;
    @Json(name = "shortDescription")
    private String shortDescription;
    @Json(name = "longDescription")
    private String longDescription;
    @Json(name = "thumbnailImage")
    private String thumbnailImage;
    @Json(name = "mediumImage")
    private String mediumImage;
    @Json(name = "largeImage")
    private String largeImage;
    @Json(name = "productTrackingUrl")
    private String productTrackingUrl;
    @Json(name = "standardShipRate")
    private Double standardShipRate;
    @Json(name = "marketplace")
    private Boolean marketplace;
    @Json(name = "modelNumber")
    private String modelNumber;
    @Json(name = "productUrl")
    private String productUrl;
    @Json(name = "customerRating")
    private String customerRating;
    @Json(name = "numReviews")
    private Integer numReviews;
    @Json(name = "customerRatingImage")
    private String customerRatingImage;
    @Json(name = "categoryNode")
    private String categoryNode;
    @Json(name = "rhid")
    private String rhid;
    @Json(name = "bundle")
    private Boolean bundle;
    @Json(name = "stock")
    private String stock;
    @Json(name = "addToCartUrl")
    private String addToCartUrl;
    @Json(name = "affiliateAddToCartUrl")
    private String affiliateAddToCartUrl;
    @Json(name = "giftOptions")
    private GiftOptions giftOptions;
    @Json(name = "imageEntities")
    private List<ImageEntity> imageEntities = null;
    @Json(name = "offerType")
    private String offerType;
    @Json(name = "isTwoDayShippingEligible")
    private Boolean isTwoDayShippingEligible;
    @Json(name = "availableOnline")
    private Boolean availableOnline;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getParentItemId() {
        return parentItemId;
    }

    public void setParentItemId(Integer parentItemId) {
        this.parentItemId = parentItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMsrp() {
        return msrp;
    }

    public void setMsrp(Double msrp) {
        this.msrp = msrp;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public String getMediumImage() {
        return mediumImage;
    }

    public void setMediumImage(String mediumImage) {
        this.mediumImage = mediumImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public String getProductTrackingUrl() {
        return productTrackingUrl;
    }

    public void setProductTrackingUrl(String productTrackingUrl) {
        this.productTrackingUrl = productTrackingUrl;
    }

    public Double getStandardShipRate() {
        return standardShipRate;
    }

    public void setStandardShipRate(Double standardShipRate) {
        this.standardShipRate = standardShipRate;
    }

    public Boolean getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(Boolean marketplace) {
        this.marketplace = marketplace;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(String customerRating) {
        this.customerRating = customerRating;
    }

    public Integer getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(Integer numReviews) {
        this.numReviews = numReviews;
    }

    public String getCustomerRatingImage() {
        return customerRatingImage;
    }

    public void setCustomerRatingImage(String customerRatingImage) {
        this.customerRatingImage = customerRatingImage;
    }

    public String getCategoryNode() {
        return categoryNode;
    }

    public void setCategoryNode(String categoryNode) {
        this.categoryNode = categoryNode;
    }

    public String getRhid() {
        return rhid;
    }

    public void setRhid(String rhid) {
        this.rhid = rhid;
    }

    public Boolean getBundle() {
        return bundle;
    }

    public void setBundle(Boolean bundle) {
        this.bundle = bundle;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getAddToCartUrl() {
        return addToCartUrl;
    }

    public void setAddToCartUrl(String addToCartUrl) {
        this.addToCartUrl = addToCartUrl;
    }

    public String getAffiliateAddToCartUrl() {
        return affiliateAddToCartUrl;
    }

    public void setAffiliateAddToCartUrl(String affiliateAddToCartUrl) {
        this.affiliateAddToCartUrl = affiliateAddToCartUrl;
    }

    public GiftOptions getGiftOptions() {
        return giftOptions;
    }

    public void setGiftOptions(GiftOptions giftOptions) {
        this.giftOptions = giftOptions;
    }

    public List<ImageEntity> getImageEntities() {
        return imageEntities;
    }

    public void setImageEntities(List<ImageEntity> imageEntities) {
        this.imageEntities = imageEntities;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public Boolean getIsTwoDayShippingEligible() {
        return isTwoDayShippingEligible;
    }

    public void setIsTwoDayShippingEligible(Boolean isTwoDayShippingEligible) {
        this.isTwoDayShippingEligible = isTwoDayShippingEligible;
    }

    public Boolean getAvailableOnline() {
        return availableOnline;
    }

    public void setAvailableOnline(Boolean availableOnline) {
        this.availableOnline = availableOnline;
    }

}