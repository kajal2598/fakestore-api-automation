package api.endpoints;

/**
 * Routes Class
 * Maintains all API endpoints in one place for easy maintenance.
 * If the Base URL changes, we only need to update it here.
 */
public class Routes {
    // Base URI for the API
    public static String base_url = "https://fakestoreapi.com";

    // Endpoints for Product Module
    public static String products = base_url + "/products";
    public static String single_product = base_url + "/products/{id}";
}