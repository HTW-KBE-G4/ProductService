package de.tanukihardwarestore.ProductService.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long product_id;

    private String name;

    @ManyToMany
    @JoinColumn(name = "component_id")
    private Set<PCComponent> components;

    public Product() {}

    public Product(Long id, String name, Set<PCComponent> components) {
        super();
        this.product_id = id;
        this.name = name;
        this.components = components;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + product_id +
                ", name='" + name + '\'' +
                ", components=" + components +
                '}';
    }

    public Long getProduct_id() {
        return product_id;
    }

    public String getName() {
        return name;
    }

    public Set<PCComponent> getComponents() { return components; }

}
