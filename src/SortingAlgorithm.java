/**
 * @author <pre>
 *     Szymon Lenart
 *     Damian Bugaj
 *     Bartlomiej Szpila
 *     </pre>
 * @version 6.06.2019
 */

import java.util.ArrayList;

/**
 * The <code>SortingAlgorithm</code> object to sort product's details
 */

public class SortingAlgorithm {

    private static final Double MAX_COST = Double.MAX_VALUE;
    private Double firstPrice;
    private Double secondPrice;
    private Double thirdPrice;

    private ArrayList<String> productNames1;
    private ArrayList<String> sellerLinks1;

    private ArrayList<String> productNames2;
    private ArrayList<String> sellerLinks2;

    private ArrayList<String> productNames3;
    private ArrayList<String> sellerLinks3;

    /**
     * The conctructor <code>SortingAlgorithm</code> class
     * @param products1 list of 3 cheapest alternatives of item
     * @param count1 number of product
     */
    public SortingAlgorithm(ArrayList<Product> products1, int count1) {
        this.firstPrice = products1.get(0).getPrice() * count1;
        this.secondPrice = products1.get(1).getPrice() * count1;
        this.thirdPrice = products1.get(2).getPrice() * count1;

        this.productNames1 = new ArrayList<>();
        this.sellerLinks1 = new ArrayList<>();

        this.productNames2 = new ArrayList<>();
        this.sellerLinks2 = new ArrayList<>();

        this.productNames3 = new ArrayList<>();
        this.sellerLinks3 = new ArrayList<>();


        productNames1.add(products1.get(0).getProductName());
        sellerLinks1.add(products1.get(0).getSellerLink());

        productNames2.add(products1.get(1).getProductName());
        sellerLinks2.add(products1.get(1).getSellerLink());

        productNames3.add(products1.get(2).getProductName());
        sellerLinks3.add(products1.get(2).getSellerLink());
    }

    /**
     * As above
     * @param products1
     * @param count1
     * @param products2
     * @param count2
     */

    public SortingAlgorithm(ArrayList<Product> products1, int count1,
                            ArrayList<Product> products2, int count2) {
        this.firstPrice = MAX_COST;
        this.secondPrice = MAX_COST;
        this.thirdPrice = MAX_COST;

        this.productNames1 = new ArrayList<>();
        this.sellerLinks1 = new ArrayList<>();

        this.productNames2 = new ArrayList<>();
        this.sellerLinks2 = new ArrayList<>();

        this.productNames3 = new ArrayList<>();
        this.sellerLinks3 = new ArrayList<>();

        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                Double priceTemp = products1.get(a).getPrice() * count1 +
                        products2.get(b).getPrice() * count2 +
                        products1.get(a).getDeliveryCost();
                if (!products2.get(b).getSeller().equals(products1.get(a).getSeller())) {
                    priceTemp += products2.get(b).getDeliveryCost();
                }
                if (priceTemp < this.firstPrice) {
                    this.firstPrice = priceTemp;
                    this.productNames1.clear();
                    this.productNames1.add(products1.get(a).getProductName());
                    this.productNames1.add(products2.get(b).getProductName());
                    this.sellerLinks1.clear();
                    this.sellerLinks1.add(products1.get(a).getSellerLink());
                    this.sellerLinks1.add(products2.get(b).getSellerLink());
                } else if (priceTemp >= this.firstPrice && priceTemp < this.secondPrice) {
                    this.secondPrice = priceTemp;
                    this.productNames2.clear();
                    this.productNames2.add(products1.get(a).getProductName());
                    this.productNames2.add(products2.get(b).getProductName());
                    this.sellerLinks2.clear();
                    this.sellerLinks2.add(products1.get(a).getSellerLink());
                    this.sellerLinks2.add(products2.get(b).getSellerLink());
                } else if (priceTemp >= this.firstPrice && priceTemp >= this.secondPrice && priceTemp < this.thirdPrice) {
                    this.thirdPrice = priceTemp;
                    this.productNames3.clear();
                    this.productNames3.add(products1.get(a).getProductName());
                    this.productNames3.add(products2.get(b).getProductName());
                    this.sellerLinks3.clear();
                    this.sellerLinks3.add(products1.get(a).getSellerLink());
                    this.sellerLinks3.add(products2.get(b).getSellerLink());
                }

            }
        }
    }

    /**
     * As above
     * @param products1
     * @param count1
     * @param products2
     * @param count2
     * @param products3
     * @param count3
     */

    public SortingAlgorithm(ArrayList<Product> products1, int count1,
                            ArrayList<Product> products2, int count2,
                            ArrayList<Product> products3, int count3) {
        this.firstPrice = MAX_COST;
        this.secondPrice = MAX_COST;
        this.thirdPrice = MAX_COST;

        this.productNames1 = new ArrayList<>();
        this.sellerLinks1 = new ArrayList<>();

        this.productNames2 = new ArrayList<>();
        this.sellerLinks2 = new ArrayList<>();

        this.productNames3 = new ArrayList<>();
        this.sellerLinks3 = new ArrayList<>();

        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                for (int c = 0; c < 3; c++) {
                    Double priceTemp = products1.get(a).getPrice() * count1 +
                            products2.get(b).getPrice() * count2 +
                            products3.get(c).getPrice() * count3 +
                            products1.get(a).getDeliveryCost();
                    if (!products2.get(b).getSeller().equals(products1.get(a).getSeller())) {
                        priceTemp += products2.get(b).getDeliveryCost();
                    }
                    if (!products3.get(c).getSeller().equals(products1.get(a).getSeller()) &&
                            !products3.get(c).getSeller().equals(products2.get(b).getSeller())) {
                        priceTemp += products3.get(c).getDeliveryCost();
                    }
                    if (priceTemp < this.firstPrice) {
                        this.firstPrice = priceTemp;
                        this.productNames1.clear();
                        this.productNames1.add(products1.get(a).getProductName());
                        this.productNames1.add(products2.get(b).getProductName());
                        this.productNames1.add(products3.get(c).getProductName());
                        this.sellerLinks1.clear();
                        this.sellerLinks1.add(products1.get(a).getSellerLink());
                        this.sellerLinks1.add(products2.get(b).getSellerLink());
                        this.sellerLinks1.add(products3.get(c).getSellerLink());
                    } else if (priceTemp >= this.firstPrice && priceTemp < this.secondPrice) {
                        this.secondPrice = priceTemp;
                        this.productNames2.clear();
                        this.productNames2.add(products1.get(a).getProductName());
                        this.productNames2.add(products2.get(b).getProductName());
                        this.productNames2.add(products3.get(c).getProductName());
                        this.sellerLinks2.clear();
                        this.sellerLinks2.add(products1.get(a).getSellerLink());
                        this.sellerLinks2.add(products2.get(b).getSellerLink());
                        this.sellerLinks2.add(products3.get(c).getSellerLink());
                    } else if (priceTemp >= this.firstPrice && priceTemp >= this.secondPrice && priceTemp < this.thirdPrice) {
                        this.thirdPrice = priceTemp;
                        this.productNames3.clear();
                        this.productNames3.add(products1.get(a).getProductName());
                        this.productNames3.add(products2.get(b).getProductName());
                        this.productNames3.add(products3.get(c).getProductName());
                        this.sellerLinks3.clear();
                        this.sellerLinks3.add(products1.get(a).getSellerLink());
                        this.sellerLinks3.add(products2.get(b).getSellerLink());
                        this.sellerLinks3.add(products3.get(c).getSellerLink());
                    }
                }
            }
        }
    }

    /**
     * As above
     * @param products1
     * @param count1
     * @param products2
     * @param count2
     * @param products3
     * @param count3
     * @param products4
     * @param count4
     */
    public SortingAlgorithm(ArrayList<Product> products1, int count1,
                            ArrayList<Product> products2, int count2,
                            ArrayList<Product> products3, int count3,
                            ArrayList<Product> products4, int count4) {
        this.firstPrice = MAX_COST;
        this.secondPrice = MAX_COST;
        this.thirdPrice = MAX_COST;

        this.productNames1 = new ArrayList<>();
        this.sellerLinks1 = new ArrayList<>();

        this.productNames2 = new ArrayList<>();
        this.sellerLinks2 = new ArrayList<>();

        this.productNames3 = new ArrayList<>();
        this.sellerLinks3 = new ArrayList<>();

        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                for (int c = 0; c < 3; c++) {
                    for (int d = 0; d < 3; d++) {
                        Double priceTemp = products1.get(a).getPrice() * count1 +
                                products2.get(b).getPrice() * count2 +
                                products3.get(c).getPrice() * count3 +
                                products4.get(d).getPrice() * count4 +
                                products1.get(a).getDeliveryCost();
                        if (!products2.get(b).getSeller().equals(products1.get(a).getSeller())) {
                            priceTemp += products2.get(b).getDeliveryCost();
                        }
                        if (!products3.get(c).getSeller().equals(products1.get(a).getSeller()) &&
                                !products3.get(c).getSeller().equals(products2.get(b).getSeller())) {
                            priceTemp += products3.get(c).getDeliveryCost();
                        }
                        if (!products4.get(d).getSeller().equals(products1.get(a).getSeller()) &&
                                !products4.get(d).getSeller().equals(products2.get(b).getSeller()) &&
                                !products4.get(d).getSeller().equals(products3.get(c).getSeller())) {
                            priceTemp += products4.get(d).getDeliveryCost();
                        }
                        if (priceTemp < this.firstPrice) {
                            this.firstPrice = priceTemp;
                            this.productNames1.clear();
                            this.productNames1.add(products1.get(a).getProductName());
                            this.productNames1.add(products2.get(b).getProductName());
                            this.productNames1.add(products3.get(c).getProductName());
                            this.productNames1.add(products4.get(d).getProductName());
                            this.sellerLinks1.clear();
                            this.sellerLinks1.add(products1.get(a).getSellerLink());
                            this.sellerLinks1.add(products2.get(b).getSellerLink());
                            this.sellerLinks1.add(products3.get(c).getSellerLink());
                            this.sellerLinks1.add(products4.get(d).getSellerLink());
                        } else if (priceTemp > this.firstPrice && priceTemp < this.secondPrice) {
                            this.secondPrice = priceTemp;
                            this.productNames2.clear();
                            this.productNames2.add(products1.get(a).getProductName());
                            this.productNames2.add(products2.get(b).getProductName());
                            this.productNames2.add(products3.get(c).getProductName());
                            this.productNames2.add(products4.get(d).getProductName());
                            this.sellerLinks2.clear();
                            this.sellerLinks2.add(products1.get(a).getSellerLink());
                            this.sellerLinks2.add(products2.get(b).getSellerLink());
                            this.sellerLinks2.add(products3.get(c).getSellerLink());
                            this.sellerLinks2.add(products4.get(d).getSellerLink());
                        } else if (priceTemp >= this.firstPrice && priceTemp >= this.secondPrice && priceTemp < this.thirdPrice) {
                            this.thirdPrice = priceTemp;
                            this.productNames3.clear();
                            this.productNames3.add(products1.get(a).getProductName());
                            this.productNames3.add(products2.get(b).getProductName());
                            this.productNames3.add(products3.get(c).getProductName());
                            this.productNames3.add(products4.get(d).getProductName());
                            this.sellerLinks3.clear();
                            this.sellerLinks3.add(products1.get(a).getSellerLink());
                            this.sellerLinks3.add(products2.get(b).getSellerLink());
                            this.sellerLinks3.add(products3.get(c).getSellerLink());
                            this.sellerLinks3.add(products4.get(d).getSellerLink());
                        }
                    }
                }
            }
        }
    }


    /**
     * As above
     * @param products1
     * @param count1
     * @param products2
     * @param count2
     * @param products3
     * @param count3
     * @param products4
     * @param count4
     * @param products5
     * @param count5
     */
    public SortingAlgorithm(ArrayList<Product> products1, int count1,
                            ArrayList<Product> products2, int count2,
                            ArrayList<Product> products3, int count3,
                            ArrayList<Product> products4, int count4,
                            ArrayList<Product> products5, int count5) {
        this.firstPrice = MAX_COST;
        this.secondPrice = MAX_COST;
        this.thirdPrice = MAX_COST;

        this.productNames1 = new ArrayList<>();
        this.sellerLinks1 = new ArrayList<>();

        this.productNames2 = new ArrayList<>();
        this.sellerLinks2 = new ArrayList<>();

        this.productNames3 = new ArrayList<>();
        this.sellerLinks3 = new ArrayList<>();

        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                for (int c = 0; c < 3; c++) {
                    for (int d = 0; d < 3; d++) {
                        for (int e = 0; e < 3; e++) {
                            Double priceTemp = products1.get(a).getPrice() * count1 +
                                    products2.get(b).getPrice() * count2 +
                                    products3.get(c).getPrice() * count3 +
                                    products4.get(d).getPrice() * count4 +
                                    products5.get(e).getPrice() * count5 +
                                    products1.get(a).getDeliveryCost();
                            if (!products2.get(b).getSeller().equals(products1.get(a).getSeller())) {
                                priceTemp += products2.get(b).getDeliveryCost();
                            }
                            if (!products3.get(c).getSeller().equals(products1.get(a).getSeller()) &&
                                    !products3.get(c).getSeller().equals(products2.get(b).getSeller())) {
                                priceTemp += products3.get(c).getDeliveryCost();
                            }
                            if (!products4.get(d).getSeller().equals(products1.get(a).getSeller()) &&
                                    !products4.get(d).getSeller().equals(products2.get(b).getSeller()) &&
                                    !products4.get(d).getSeller().equals(products3.get(c).getSeller())) {
                                priceTemp += products4.get(d).getDeliveryCost();
                            }
                            if (!products5.get(e).getSeller().equals(products1.get(a).getSeller()) &&
                                    !products5.get(e).getSeller().equals(products2.get(b).getSeller()) &&
                                    !products5.get(e).getSeller().equals(products3.get(c).getSeller()) &&
                                    !products5.get(e).getSeller().equals(products4.get(d).getSeller())) {
                                priceTemp += products5.get(e).getDeliveryCost();
                            }
                            if (priceTemp < this.firstPrice) {
                                this.firstPrice = priceTemp;
                                this.productNames1.clear();
                                this.productNames1.add(products1.get(a).getProductName());
                                this.productNames1.add(products2.get(b).getProductName());
                                this.productNames1.add(products3.get(c).getProductName());
                                this.productNames1.add(products4.get(d).getProductName());
                                this.productNames1.add(products5.get(e).getProductName());
                                this.sellerLinks1.clear();
                                this.sellerLinks1.add(products1.get(a).getSellerLink());
                                this.sellerLinks1.add(products2.get(b).getSellerLink());
                                this.sellerLinks1.add(products3.get(c).getSellerLink());
                                this.sellerLinks1.add(products4.get(d).getSellerLink());
                                this.sellerLinks1.add(products5.get(e).getSellerLink());
                            } else if (priceTemp > this.firstPrice && priceTemp < this.secondPrice) {
                                this.secondPrice = priceTemp;
                                this.productNames2.clear();
                                this.productNames2.add(products1.get(a).getProductName());
                                this.productNames2.add(products2.get(b).getProductName());
                                this.productNames2.add(products3.get(c).getProductName());
                                this.productNames2.add(products4.get(d).getProductName());
                                this.productNames2.add(products5.get(e).getProductName());
                                this.sellerLinks2.clear();
                                this.sellerLinks2.add(products1.get(a).getSellerLink());
                                this.sellerLinks2.add(products2.get(b).getSellerLink());
                                this.sellerLinks2.add(products3.get(c).getSellerLink());
                                this.sellerLinks2.add(products4.get(d).getSellerLink());
                                this.sellerLinks2.add(products5.get(e).getSellerLink());
                            } else if (priceTemp >= this.firstPrice && priceTemp >= this.secondPrice && priceTemp < this.thirdPrice) {
                                this.thirdPrice = priceTemp;
                                this.productNames3.clear();
                                this.productNames3.add(products1.get(a).getProductName());
                                this.productNames3.add(products2.get(b).getProductName());
                                this.productNames3.add(products3.get(c).getProductName());
                                this.productNames3.add(products4.get(d).getProductName());
                                this.productNames3.add(products5.get(e).getProductName());
                                this.sellerLinks3.clear();
                                this.sellerLinks3.add(products1.get(a).getSellerLink());
                                this.sellerLinks3.add(products2.get(b).getSellerLink());
                                this.sellerLinks3.add(products3.get(c).getSellerLink());
                                this.sellerLinks3.add(products4.get(d).getSellerLink());
                                this.sellerLinks3.add(products5.get(e).getSellerLink());
                            }
                        }
                    }
                }
            }
        }
    }



    /**
     *
     * @return the first summary price of products
     */
    public Double getFirstPrice() {
        return firstPrice;
    }

    /**
     *
     * @return the second summary price of products
     */
    public Double getSecondPrice() {
        return secondPrice;
    }

    /**
     *
     * @return the third summary price of products
     */

    public Double getThirdPrice() {
        return thirdPrice;
    }


    /**
     *
     * @return the first summary product's names
     */

    public ArrayList<String> getProductNames1() {
        return productNames1;
    }

    /**
     *
     * @return the first summary product's links
     */

    public ArrayList<String> getSellerLinks1() {
        return sellerLinks1;
    }

    /**
     *
     * @return the second summary product's names
     */

    public ArrayList<String> getProductNames2() {
        return productNames2;
    }

    /**
     *
     * @return the second product's links
     */
    public ArrayList<String> getSellerLinks2() {
        return sellerLinks2;
    }

    /**
     *
     * @return the third summary product's names
     */
    public ArrayList<String> getProductNames3() {
        return productNames3;
    }

    /**
     *
     * @return the third product's links
     */

    public ArrayList<String> getSellerLinks3() {
        return sellerLinks3;
    }

}