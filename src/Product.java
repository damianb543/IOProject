/**
 * @author <pre>
 *     Szymon Lenart
 *     Damian Bugaj
 *     Bartlomiej Szpila
 *     </pre>
 * @version 6.06.2019
 */

/**
 * The <code>Product</code> object used to systematize product details
 */

public class Product {

    private Double rating;
    private Double ratingCount;
    private Double price;
    private String productName;
    private String seller;
    private String sellerLink;
    private Double deliveryCost;

    /**
     * The constructor  <code>Product</code> class
     * @param scrapper <code>Scrapper</code> object to scrap product details
     * @param index the number of product details
     */
    public Product(Scrapper scrapper, int index) {
        this.rating = scrapper.getRatings().get(index);
        this.ratingCount = scrapper.getRatingsCount().get(index);
        this.price = scrapper.getPrices().get(index);
        this.productName = scrapper.getProductNames().get(index);
        this.seller = scrapper.getSellers().get(index);
        this.sellerLink = scrapper.getSellerLinks().get(index);
        this.deliveryCost = scrapper.getDeliveryCosts().get(index);
    }

    /**
     *
     * @return the product's price
     */

    public Double getPrice() {
        return price;
    }

    /**
     *
     * @return product's name
     */
    public String getProductName() {
        return productName;
    }

    /**
     *
     * @return product's seller
     */

    public String getSeller() {
        return seller;
    }

    /**
     *
     * @return link to seller's shop
     */
    public String getSellerLink() {
        return sellerLink;
    }

    /**
     *
     * @return product's delivery cost
     */
    public Double getDeliveryCost() {
        return deliveryCost;
    }
}