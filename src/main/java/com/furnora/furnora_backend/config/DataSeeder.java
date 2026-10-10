package com.furnora.furnora_backend.config;

import com.furnora.furnora_backend.entity.Category;
import com.furnora.furnora_backend.entity.Product;
import com.furnora.furnora_backend.repository.CategoryRepository;
import com.furnora.furnora_backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) {
        cleanUp();

        Category sofa = getOrCreateCategory("Sofa");
        Category table = getOrCreateCategory("Table");
        Category bed = getOrCreateCategory("Bed");
        Category chair = getOrCreateCategory("Chair");
        Category door = getOrCreateCategory("Door");
        Category decor = getOrCreateCategory("Home Decor");
        Category kitchen = getOrCreateCategory("Kitchen and Dining");
        Category legs = getOrCreateCategory("Metal Legs");

        // ===== SOFA =====
        add(sofa, "Classic 3-Seater Sofa", "Comfortable 3-seater sofa with soft cushioning and a sturdy wooden frame",
                18999, "Wood + Fabric", "200 x 90 x 85 cm (W x D x H)", "Grey",
                "https://images.unsplash.com/photo-1493663284031-b7e3aefcae8e?w=400&h=300&fit=crop");
        add(sofa, "Luxury Velvet Sofa", "Premium velvet upholstered sofa with tufted back and gold-finish legs",
                32999, "Velvet + Wood", "210 x 95 x 85 cm (W x D x H)", "Blue",
                "https://images.unsplash.com/photo-1540574163026-643ea20ade25?w=400&h=300&fit=crop");
        add(sofa, "Compact 2-Seater Loveseat", "Space-saving 2-seater sofa, perfect for small living rooms",
                12499, "Wood + Fabric", "150 x 80 x 80 cm (W x D x H)", "Beige",
                "https://images.unsplash.com/photo-1586023492125-27b2c045efd7?w=400&h=300&fit=crop");

        // ===== TABLE =====
        add(table, "Sheesham Wood 6-Seater Dining Table", "Solid sheesham wood dining table with a smooth polished finish",
                18999, "Sheesham Wood", "180 x 90 x 75 cm (W x D x H)", "Brown",
                "https://images.unsplash.com/photo-1617104551722-3b2d51366400?w=400&h=300&fit=crop");
        add(table, "Round 4-Seater Dining Table", "Round dining table that fits a small family and saves space",
                14999, "Engineered Wood", "120 x 120 x 75 cm (W x D x H)", "Walnut",
                "https://images.unsplash.com/photo-1538688525198-9b88f6f53126?w=400&h=300&fit=crop");
        add(table, "Glass Top Dining Table", "Modern tempered glass top table with a metal frame",
                16499, "Glass + Metal", "160 x 90 x 75 cm (W x D x H)", "Black",
                "https://images.unsplash.com/photo-1595428774223-ef52624120d2?w=400&h=300&fit=crop");

        // ===== BED =====
        add(bed, "King Size Wooden Bed", "Strong king size bed with a carved headboard in solid wood",
                28999, "Teak Wood", "210 x 190 x 100 cm (L x W x H)", "Dark Brown",
                "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?w=400&h=300&fit=crop");
        add(bed, "Queen Size Upholstered Bed", "Queen size bed with a soft cushioned headboard",
                24999, "Wood + Fabric", "200 x 160 x 110 cm (L x W x H)", "Grey",
                "https://images.unsplash.com/photo-1522771739844-6a9f6d5f14af?w=400&h=300&fit=crop");
        add(bed, "Storage Bed with Drawers", "Bed with spacious drawers underneath for extra storage",
                21999, "Engineered Wood", "200 x 150 x 90 cm (L x W x H)", "Oak",
                "https://images.unsplash.com/photo-1616486338812-3dadae4b4ace?w=400&h=300&fit=crop");

        // ===== CHAIR =====
        add(chair, "Ergonomic Office Chair", "Adjustable office chair with lumbar support and breathable mesh back",
                7999, "Mesh + Metal", "60 x 60 x 110 cm (W x D x H)", "Black",
                "https://images.unsplash.com/photo-1503602642458-232111445657?w=400&h=300&fit=crop");
        add(chair, "Wooden Armchair", "Classic armchair with cushioned seat and solid wood arms",
                5499, "Wood + Fabric", "65 x 65 x 90 cm (W x D x H)", "Brown",
                "https://images.unsplash.com/photo-1567538096630-e0c55bd6374c?w=400&h=300&fit=crop");
        add(chair, "Cushioned Dining Chair", "Comfortable dining chair with padded seat",
                3499, "Wood + Fabric", "45 x 50 x 95 cm (W x D x H)", "Cream",
                "https://images.unsplash.com/photo-1506439773649-6e0eb8cfb237?w=400&h=300&fit=crop");

        // ===== DOOR (navin) - image nahi, placeholder disel =====
        add(door, "Solid Teak Main Door", "Heavy duty teak wood main door with a natural polish finish",
                24999, "Teak Wood", "210 x 90 x 4 cm (H x W x T)", "Natural Teak", "");
        add(door, "Flush Door with Laminate Finish", "Smooth flush door with a laminate finish, ideal for bedrooms",
                8999, "Engineered Wood", "200 x 80 x 3.5 cm (H x W x T)", "Walnut", "");
        add(door, "Carved Wooden Panel Door", "Hand carved panel door in solid sheesham wood",
                19999, "Sheesham Wood", "210 x 90 x 4 cm (H x W x T)", "Dark Brown", "");

        // ===== HOME DECOR (navin) =====
        add(decor, "Decorative Round Wall Mirror", "Round wall mirror with a slim gold metal frame",
                3499, "Metal Frame + Glass", "60 cm diameter", "Gold", "");
        add(decor, "Ceramic Vase Set of 3", "Set of three ceramic vases in different sizes",
                1999, "Ceramic", "Tallest vase 30 cm", "White", "");
        add(decor, "Wooden Wall Clock", "Round wall clock with a solid wood frame",
                1499, "Wood", "40 cm diameter", "Brown", "");

        // ===== KITCHEN AND DINING (navin) =====
        add(kitchen, "Kitchen Trolley with Storage", "Rolling kitchen trolley with shelves and a towel rail",
                5999, "Wood + Metal", "80 x 40 x 85 cm (W x D x H)", "Oak", "");
        add(kitchen, "Crockery Cabinet", "Tall cabinet with glass doors to display and store crockery",
                14999, "Engineered Wood", "90 x 40 x 180 cm (W x D x H)", "White", "");
        add(kitchen, "Bar Stool Set of 2", "Two bar stools with a metal base and wooden seat",
                6499, "Metal + Wood", "40 x 40 x 75 cm (W x D x H)", "Black", "");

        // ===== METAL LEGS (navin) =====
        add(legs, "Hairpin Metal Legs Set of 4", "Iron hairpin legs for tables and benches, screws included",
                1299, "Iron", "40 cm height", "Black", "");
        add(legs, "Adjustable Steel Furniture Legs Set of 4", "Stainless steel legs with adjustable height",
                1799, "Stainless Steel", "15 to 25 cm adjustable height", "Silver", "");
        add(legs, "Gold Finish Sofa Legs Set of 4", "Gold finish metal legs to upgrade your sofa",
                1999, "Metal", "15 cm height", "Gold", "");
    }

    private void cleanUp() {
        // Wardrobe category ani tyache products kadh
        for (Category c : categoryRepository.findAll()) {
            if (c.getName().toLowerCase().startsWith("wardrobe")) {
                for (Product p : productRepository.findByCategoryId(c.getId())) {
                    try {
                        productRepository.delete(p);
                    } catch (Exception e) {
                        System.out.println("Skipped deleting product " + p.getId() + " (cart/order madhe vaparla aahe)");
                    }
                }
                try {
                    categoryRepository.delete(c);
                } catch (Exception e) {
                    System.out.println("Wardrobe category rahili (tyache products ajun vaparat aahet)");
                }
            }
        }

        // Test karataana chukun 'Sofa' category madhe gelele Dining Table kadh
        for (Product p : productRepository.findAll()) {
            if ("Wooden Dining Table".equals(p.getName()) && p.getCategory() != null
                    && "Sofa".equals(p.getCategory().getName())) {
                try {
                    productRepository.delete(p);
                } catch (Exception e) {
                    System.out.println("Skipped deleting product " + p.getId() + " (cart/order madhe vaparla aahe)");
                }
            }
        }
    }

    private Category getOrCreateCategory(String name) {
        return categoryRepository.findAll().stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseGet(() -> {
                    Category c = new Category();
                    c.setName(name);
                    return categoryRepository.save(c);
                });
    }

    private void add(Category category, String name, String description, double price,
                     String material, String dimensions, String color, String imageUrl) {
        boolean exists = productRepository.findAll().stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(name));
        if (exists) return;

        Product p = new Product();
        p.setName(name);
        p.setDescription(description);
        p.setPrice(price);
        p.setStock(8);
        p.setImageUrl(imageUrl == null || imageUrl.isEmpty() ? null : imageUrl);
        p.setMaterial(material);
        p.setDimensions(dimensions);
        p.setColor(color);
        p.setWarranty("1 Year Manufacturer Warranty");
        p.setReturnPolicy("7-day return policy, product must be unused");
        p.setCategory(category);
        productRepository.save(p);
    }
}