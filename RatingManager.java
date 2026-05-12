import java.util.HashMap;
import java.util.Map;

public class RatingManager {
    private Map<Integer, Product> products;

    public RatingManager() {
        this.products = new HashMap<>();
    }

    /**
     * Adds a product to the manager.
     * 
     * @param p The Product object to add
     */
    public void addProduct(Product p) {
        if (p != null) {
            products.put(p.getProductId(), p);
        }
    }

    /**
     * Retrieves a product by its ID.
     * 
     * @param productId The ID of the product
     * @return The Product object, or null if not found
     */
    public Product getProduct(int productId) {
        return products.get(productId);
    }

    /**
     * Finds a product by ID and adds a rating to it.
     * 
     * @param productId The ID of the product to rate
     * @param r         The Rating object to add
     */
    public void rateProduct(int productId, Rating r) {
        Product p = products.get(productId);
        if (p != null) {
            p.addRating(r);
        } else {
            System.out.println("Error: Product with ID " + productId + " not found.");
        }
    }

    /**
     * Displays all details and ratings for a specific product.
     * 
     * @param productId The ID of the product to view
     */
    public void viewProductRatings(int productId) {
        Product p = products.get(productId);
        if (p != null) {
            p.displayProductDetails();
        } else {
            System.out.println("Error: Product with ID " + productId + " not found.");
        }
    }

    /**
     * Finds the product with the highest average rating.
     * 
     * @return The Product with the highest average rating, or null if no products exist.
     */
    public Product getTopRatedProduct() {
        if (products.isEmpty()) {
            return null;
        }

        Product topProduct = null;
        double highestAvg = -1.0;

        for (Product p : products.values()) {
            double currentAvg = p.calculateAverageRating();
            if (currentAvg > highestAvg) {
                highestAvg = currentAvg;
                topProduct = p;
            }
        }

        return topProduct;
    }
}
