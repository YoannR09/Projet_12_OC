package fr.yr.site.alegia.paypal;

public class OrderDetail {
    private String productName;
    private float subtotal;
    private float shipping;
    private float tax;
    private float total;

    public OrderDetail(String productName, String subtotal,
                       String shipping, String tax, String total) {
        this.productName = productName;
        this.subtotal = Float.parseFloat(subtotal);
        this.shipping = Float.parseFloat(shipping);
        this.tax = Float.parseFloat(tax);
        this.total = Float.parseFloat(total);
    }

    public String getProductName() {
        return productName;
    }

    public String getSubtotal() {
        return String.valueOf(subtotal);
    }

    public String getShipping() {
        return String.valueOf(shipping);
    }

    public String getTax() {
        return String.valueOf(tax);
    }

    public String getTotal() {
        return String.valueOf(total);
    }
}
