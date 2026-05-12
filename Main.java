public class Main {
    public static void main(String[] args) {
        // 1. Create a RatingManager instance
        RatingManager manager = new RatingManager();

        // 2. Create 3 sample Product objects with different categories
        Product p1 = new Product(101, "AeroBoost Sneakers", "Footwear");
        Product p2 = new Product(102, "Titan Gaming Mouse", "Peripherals");
        Product p3 = new Product(103, "EcoGlow Desk Lamp", "Home Decor");

        // 3. Add all 3 products to RatingManager
        manager.addProduct(p1);
        manager.addProduct(p2);
        manager.addProduct(p3);

        // 4 & 5. Create Rating objects and attach them using rateProduct()
        
        // Ratings for AeroBoost Sneakers (ID: 101)
        manager.rateProduct(101, new Rating(1, "Alice", 5, "Super comfortable for running!", "2026-05-01"));
        manager.rateProduct(101, new Rating(2, "Bob", 4, "Great style, fits well.", "2026-05-02"));

        // Ratings for Titan Gaming Mouse (ID: 102)
        manager.rateProduct(102, new Rating(3, "Charlie", 5, "Precision is unmatched. Love the RGB!", "2026-05-03"));
        manager.rateProduct(102, new Rating(4, "David", 5, "Best mouse I've used in years.", "2026-05-04"));

        // Ratings for EcoGlow Desk Lamp (ID: 103)
        manager.rateProduct(103, new Rating(5, "Eve", 3, "Light is good, but the base is a bit wobbly.", "2026-05-05"));
        manager.rateProduct(103, new Rating(6, "Frank", 4, "Modern look, nice warm light.", "2026-05-06"));

        // 6. Displays full rating details for each product
        System.out.println("--- PRODUCT REVIEWS OVERVIEW ---\n");
        manager.viewProductRatings(101);
        System.out.println();
        manager.viewProductRatings(102);
        System.out.println();
        manager.viewProductRatings(103);

        // 7. Prints the name of the top rated product at the end
        Product top = manager.getTopRatedProduct();
        if (top != null) {
            System.out.println("\n****************************************");
            System.out.println("WINNER: TOP RATED PRODUCT");
            System.out.println("Product Name: " + top.getProductName());
            System.out.printf("Avg Score:    %.2f / 5.0\n", top.calculateAverageRating());
            System.out.println("****************************************");
        }

        // 8. Demo filterRatingsByScore(4)
        System.out.println("\n--- FILTERED REVIEWS (Min Score: 4) for EcoGlow Desk Lamp ---");
        Product lamp = manager.getProduct(103);
        if (lamp != null) {
            java.util.List<Rating> filtered = lamp.filterRatingsByScore(4);
            System.out.println("Total matches: " + filtered.size());
            for (Rating r : filtered) {
                System.out.println("- " + r.getCustomerId() + " gave " + r.getScore() + " stars: \"" + r.getReview() + "\"");
            }
        }
    }
}
