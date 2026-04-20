package api.models;

/**
 * Product Data Model (POJO)
 * Used to represent the Product object for API requests and responses.
 * This class allows Rest Assured to automatically convert Java objects to JSON (Serialization).
 */
public class Product {
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;

    // Required by Jackson library for deserialization
    public Product() {}

    /**
     * Constructor to initialize a new Product object for POST/PUT requests.
     */
    public Product(String title, double price, String description, String category, String image) {
        this.title = title;
        this.price = price;
        this.description = description;
        this.category = category;
        this.image = image;
    }

    // Standard Getters and Setters
    public String getTitle() { return title; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public String getImage() { return image; }
}