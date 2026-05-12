import java.util.ArrayList;
import java.util.List;

public class Product {
    private int productId;
    private String productName;
    private String category;
    private List<Rating> ratings;

    /**
     * Constructor for Product class.
     * 
     * @param productId   The unique ID of the product
     * @param productName The name of the product
     * @param category    The category the product belongs to
     */
    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.ratings = new ArrayList<>();
    }

    /**
     * Adds a rating to the product.
     * 
     * @param r The Rating object to add
     */
    public void addRating(Rating r) {
        if (r != null) {
            ratings.add(r);
        }
    }

    /**
     * Gets the list of ratings for this product.
     * 
     * @return List of Rating objects
     */
    public List<Rating> getRatings() {
        return ratings;
    }

    /**
     * Calculates the average score from all ratings.
     * 
     * @return The average rating score as a double, or 0.0 if no ratings exist.
     */
    public double calculateAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0;
        }

        int totalScore = 0;
        for (Rating r : ratings) {
            totalScore += r.getScore();
        }

        return (double) totalScore / ratings.size();
    }

    /**
     * Gets the total number of ratings for this product.
     * 
     * @return The count of ratings
     */
    public int getRatingCount() {
        return ratings.size();
    }

    /**
     * Filters ratings based on a minimum score.
     * 
     * @param minScore The minimum score to include (inclusive)
     * @return A list of ratings that meet the criteria
     */
    public List<Rating> filterRatingsByScore(int minScore) {
        List<Rating> filtered = new ArrayList<>();
        for (Rating r : ratings) {
            if (r.getScore() >= minScore) {
                filtered.add(r);
            }
        }
        return filtered;
    }

    /**
     * Displays complete product information including average rating
     * and individual reviews.
     */
    public void displayProductDetails() {
        System.out.println("========================================");
        System.out.println("PRODUCT INFORMATION");
        System.out.println("========================================");
        System.out.println("ID:            " + productId);
        System.out.println("Name:          " + productName);
        System.out.println("Category:      " + category);
        System.out.printf("Avg Rating:    %.2f / 5.0\n", calculateAverageRating());
        System.out.println("Total Ratings: " + getRatingCount());
        System.out.println("----------------------------------------");
        
        if (ratings.isEmpty()) {
            System.out.println("No ratings available for this product.");
        } else {
            System.out.println("INDIVIDUAL REVIEWS:");
            for (Rating r : ratings) {
                System.out.println("\n" + r.toString());
            }
        }
        System.out.println("========================================");
    }

    // Additional Getters for completeness
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }
}
