package products.walmart.com.walmartapp.model;

import com.squareup.moshi.Json;

public class ImageEntity {

    @Json(name = "thumbnailImage")
    private String thumbnailImage;
    @Json(name = "mediumImage")
    private String mediumImage;
    @Json(name = "largeImage")
    private String largeImage;
    @Json(name = "entityType")
    private String entityType;

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

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

}
