/**
 * @author <pre>
 *     Szymon Lenart
 *     Damian Bugaj
 *     Bartlomiej Szpila
 *     </pre>
 * @version 6.06.2019
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The <code>Scrapper</code> object used to scrap desired information from <a href="http://skapiec.pl" >skapiec</a>
 */
public class Scrapper {

    private ArrayList<Double> ratings;
    private ArrayList<Double> ratingsCount;
    private ArrayList<Double> prices;
    private ArrayList<String> productNames;
    private ArrayList<String> sellers;
    private ArrayList<String> sellerLinks;
    private ArrayList<Double> deliveryCosts;

    /**
     *  The constructor  <code>Scrapper</code> class to get parameters from web
     * @param searchString GUI input text
     * @throws IOException
     */
    public Scrapper(String searchString) throws IOException {
        String baseUrl = parseURL(searchString);

        ArrayList<String> urls = new ArrayList<>();
        ratings = new ArrayList<>();
        ratingsCount = new ArrayList<>();
        ArrayList<Double> ratingsInit = new ArrayList<>();
        ArrayList<String> pricesInit = new ArrayList<>();
        prices = new ArrayList<>();
        ArrayList<String> urlsDeeper = new ArrayList<>();
        productNames = new ArrayList<>();
        sellers = new ArrayList<>();
        sellerLinks = new ArrayList<>();
        deliveryCosts = new ArrayList<>();
        ArrayList<String> deliveryCostsInit = new ArrayList<>();

        Document doc = Jsoup.connect(baseUrl).get();

        Elements links = doc.select("a");
        for (Element link : links) {
            String absHref = link.attr("abs:href");
            if (absHref.startsWith("https://www.skapiec.pl/site/cat")) {
                if (!urls.contains(absHref)) {
                    urls.add(absHref);
                }
            }
        }
        if (urls.size() >= 10) {
            for (String url : urls.subList(0, 10)) {
                Document docDeeper = Jsoup.connect(url).get();
                ArrayList<String> stringRatings = new ArrayList<>();

                for (Element e : docDeeper.getElementsByClass("shop-rating gtm_stars")) {
                    stringRatings.add(e.attr("data-description"));
                }

                for (Element e : docDeeper.getElementsByClass("why-no-opinion")) {
                    stringRatings.add(e.text());
                }

                for (Element e : docDeeper.getElementsByClass("price gtm_or_price")) {
                    pricesInit.add(e.getElementsByClass("price gtm_or_price").text());
                    productNames.add(docDeeper.select("span.title").attr("title"));
                }

                for (Element e : docDeeper.getElementsByClass("offer-dealer-logo")) {
                    sellers.add(e.getElementsByAttribute("alt").text());
                }

                for (Element e : docDeeper.select("a.offer-row-item")) {
                    sellerLinks.add(e.attr("abs:href"));
                }

                Pattern ratingPattern = Pattern.compile(":(\\d+\\.?\\d*),");
                for (String stringRating : stringRatings) {
                    Matcher ratingMatcher = ratingPattern.matcher(stringRating);
                    if (!stringRating.equals("Brak opinii")) {
                        while (ratingMatcher.find()) {
                            ratingsInit.add(Double.parseDouble(ratingMatcher.group(1)));
                        }
                    } else {
                        for (int i = 0; i < 6; i++) {
                            ratingsInit.add(0.0);
                        }
                    }
                }

                for (Element e : docDeeper.getElementsByClass("delivery-cost")) {
                    if (!e.attr("abs:href").isEmpty()) {
                        urlsDeeper.add(e.attr("abs:href"));
                    } else {
                        urlsDeeper.add(url);
                    }
                }

            }
        } else {
            for (String url : urls.subList(0, 6)) {
                Document docDeeper = Jsoup.connect(url).get();
                ArrayList<String> stringRatings = new ArrayList<>();

                for (Element e : docDeeper.getElementsByClass("shop-rating gtm_stars")) {
                    stringRatings.add(e.attr("data-description"));
                }

                for (Element e : docDeeper.getElementsByClass("why-no-opinion")) {
                    stringRatings.add(e.text());
                }

                for (Element e : docDeeper.getElementsByClass("price gtm_or_price")) {
                    pricesInit.add(e.getElementsByClass("price gtm_or_price").text());
                    productNames.add(docDeeper.select("span.title").attr("title"));
                }

                for (Element e : docDeeper.getElementsByClass("offer-dealer-logo")) {
                    sellers.add(e.getElementsByAttribute("alt").text());
                }

                for (Element e : docDeeper.select("a.offer-row-item")) {
                    sellerLinks.add(e.attr("abs:href"));
                }

                Pattern ratingPattern = Pattern.compile(":(\\d+\\.?\\d*),");
                for (String stringRating : stringRatings) {
                    Matcher ratingMatcher = ratingPattern.matcher(stringRating);
                    if (!stringRating.equals("Brak opinii")) {
                        while (ratingMatcher.find()) {
                            ratingsInit.add(Double.parseDouble(ratingMatcher.group(1)));
                        }
                    } else {
                        for (int i = 0; i < 6; i++) {
                            ratingsInit.add(0.0);
                        }
                    }
                }

                for (Element e : docDeeper.getElementsByClass("delivery-cost")) {
                    if (!e.attr("abs:href").isEmpty()) {
                        urlsDeeper.add(e.attr("abs:href"));
                    } else {
                        urlsDeeper.add(url);
                    }
                }

            }
        }

        for (String urlDeeper : urlsDeeper) {
            Document docDeepest = Jsoup.connect(urlDeeper).get();
            if (!docDeepest.select("#deliveryRulesets > tbody > tr.even > td:nth-child(2) > div:nth-child(1) > b").text().isEmpty()) {
                Elements deliveryPrice = docDeepest.select("#deliveryRulesets > tbody > tr.even > td:nth-child(2) > div:nth-child(1) > b");
                Element element = deliveryPrice.first();
                deliveryCostsInit.add(element.text());
            } else {
                deliveryCostsInit.add("0.0 zł");
            }
        }

        for (int i = 0; i < ratingsInit.size() - 1; i += 6) {
            ratings.add(ratingsInit.get(i));
            ratingsCount.add(ratingsInit.get(i + 1));
        }

        for (String price : pricesInit) {
            String parsedPrice = price.replaceAll(" ", "").replaceAll(",", ".").replaceAll("zł", "");
            prices.add(Double.parseDouble(parsedPrice));
        }

        for (String cost : deliveryCostsInit) {
            String parsedDeliveryCost = cost.replaceAll(" zł", "");
            deliveryCosts.add(Double.parseDouble(parsedDeliveryCost));
        }
    }


    /**
     *
     * @param searchString GUI input text
     * @return <code>searchString</code> parsed to valid url
     */
    private String parseURL(String searchString) {
        String searchable = searchString.replaceAll(" ", "+");
        return "https://www.skapiec.pl/szukaj/w_calym_serwisie/" + searchable + "/price";
    }


    /**
     *
     * @return <code>ratings</code> of array list double type
     */
    public ArrayList<Double> getRatings() {
        return ratings;
    }

    /**
     *
     * @return <code>ratingsCount</code> of array list double type
     */
    public ArrayList<Double> getRatingsCount() {
        return ratingsCount;
    }

    /**
     *
     * @return <code>prices</code> of array list double type
     */
    public ArrayList<Double> getPrices() {
        return prices;
    }

    /**
     *
     * @return <code>productNames</code> of array list string type
     */
    public ArrayList<String> getProductNames() {
        return productNames;
    }

    /**
     *
     * @return <code>sellers</code> of array list string type
     */
    public ArrayList<String> getSellers() {
        return sellers;
    }

    /**
     *
     * @return <code>sellerLinks</code> of array list string type
     */
    public ArrayList<String> getSellerLinks() {
        return sellerLinks;
    }

    /**
     *
     * @return <code>deliveryCosts</code> of array list double type
     */
    public ArrayList<Double> getDeliveryCosts() {
        return deliveryCosts;
    }
}