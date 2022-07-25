package de.tanukihardwarestore.ProductService.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Long product_id;

    private String name;

    private String user_id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "component_id")
    private Set<PCComponent> components;

    public Product() {}

    private String image_url;

    public Product(Long id, String name, String image_url, Set<PCComponent> components, String user_id) {
        super();
        this.product_id = id;
        this.name = name;
        this.components = components;
        this.image_url = image_url;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + product_id +
                "user_id=" + user_id +
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

    public String getImage_url() {
        return image_url;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComponents(Set<PCComponent> components) {
        this.components = components;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

}
