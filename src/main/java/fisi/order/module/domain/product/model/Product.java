package fisi.order.module.domain.product.model;

public class Product {

    private int id;
    private String name;
    private String brand;
    private double price;
    private String image;
    private int stock;
    private String description;
    private int state;

    public Product(int id, String name, String brand, double price,
                   String image, int stock, String description, int state) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.image = image;
        this.stock = stock;
        this.description = description;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public String getImage() {
        return image;
    }

    public int getStock() {
        return stock;
    }

    public String getDescription() {
        return description;
    }

    public int getState() {
        return state;
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public static class ProductBuilder {
        private int id;
        private String name;
        private String brand;
        private double price;
        private String image;
        private int stock;
        private String description;
        private int state;

        public ProductBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public ProductBuilder price(double price) {
            this.price = price;
            return this;
        }

        public ProductBuilder image(String image) {
            this.image = image;
            return this;
        }

        public ProductBuilder stock(int stock) {
            this.stock = stock;
            return this;
        }

        public ProductBuilder description(String description) {
            this.description = description;
            return this;
        }

        public ProductBuilder state(int state) {
            this.state = state;
            return this;
        }

        public Product build() {
            return new Product(id, name, brand, price,
                    image, stock, description, state);
        }

    }
}