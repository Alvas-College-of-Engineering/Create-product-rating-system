public class Rating {
    private int ratingId;
    private String customerId;
    private int score;
    private String review;
    private String ratingDate;

    /**
     * Constructor for Rating class.
     * 
     * @param ratingId   The unique ID of the rating
     * @param customerId The ID of the customer who gave the rating
     * @param score      The score (value 1 to 5)
     * @param review     The review text
     * @param ratingDate The date of the rating
     */
    public Rating(int ratingId, String customerId, int score, String review, String ratingDate) {
        this.ratingId = ratingId;
        this.customerId = customerId;
        this.score = score;
        this.review = review;
        this.ratingDate = ratingDate;
    }

    // Getters
    public int getRatingId() {
        return ratingId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public int getScore() {
        return score;
    }

    public String getReview() {
        return review;
    }

    public String getRatingDate() {
        return ratingDate;
    }

    @Override
    public String toString() {
        return String.format(
            "Rating Details:\n" +
            "----------------\n" +
            "ID:          %d\n" +
            "Customer ID: %s\n" +
            "Score:       %d/5\n" +
            "Review:      %s\n" +
            "Date:        %s",
            ratingId, customerId, score, review, ratingDate
        );
    }
}
