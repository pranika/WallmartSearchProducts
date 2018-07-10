package products.walmart.com.walmartapp.model;

import com.squareup.moshi.Json;

public class GiftOptions {

    @Json(name = "allowGiftWrap")
    private Boolean allowGiftWrap;
    @Json(name = "allowGiftMessage")
    private Boolean allowGiftMessage;
    @Json(name = "allowGiftReceipt")
    private Boolean allowGiftReceipt;

    public Boolean getAllowGiftWrap() {
        return allowGiftWrap;
    }

    public void setAllowGiftWrap(Boolean allowGiftWrap) {
        this.allowGiftWrap = allowGiftWrap;
    }

    public Boolean getAllowGiftMessage() {
        return allowGiftMessage;
    }

    public void setAllowGiftMessage(Boolean allowGiftMessage) {
        this.allowGiftMessage = allowGiftMessage;
    }

    public Boolean getAllowGiftReceipt() {
        return allowGiftReceipt;
    }

    public void setAllowGiftReceipt(Boolean allowGiftReceipt) {
        this.allowGiftReceipt = allowGiftReceipt;
    }

}