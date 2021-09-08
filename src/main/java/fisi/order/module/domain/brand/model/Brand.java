package fisi.order.module.domain.brand.model;

import fisi.order.module.domain.product.model.Product;

public class Brand {
    private int id;
    private String name;

    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
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

    public static BrandBuilder builder() {
        return new BrandBuilder();
    }

    public static class BrandBuilder {
        private int id;
        private String name;

        public BrandBuilder id(int id) {
            this.id = id;
            return this;
        }

        public BrandBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Brand build() {
            return new Brand(id, name);
        }

    }
}
