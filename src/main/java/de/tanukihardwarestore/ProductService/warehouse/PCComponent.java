package de.tanukihardwarestore.ProductService.warehouse;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class PCComponent {

    @Id
    @GeneratedValue()
    private Long id;

    private String type;

    private String model;

    private String description;

    private String manufacturer;

    private String releaseDate;

    private String uvp;

    private String weight;

    private String productName;

    private String ean;

    private String imageURL;



    public PCComponent() { }

    public PCComponent(Long id, String type, String model, String manufacturer, String uvp, String description,
                       String releaseDate, String weight, String productName, String ean, String imageURL) {
        this.id = id;
        this.model = model;
        this.description = description;
        this.releaseDate = releaseDate;
        this.manufacturer = manufacturer;
        this.uvp = uvp;
        this.type = type;
        this.weight = weight;
        this.productName = productName;
        this.ean = ean;
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "PCComponent{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", uvp='" + uvp + '\'' +
                ", weight='" + weight + '\'' +
                ", productName='" + productName + '\'' +
                ", ean='" + ean + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getWeight() { return weight; }

    public String getProductName() { return productName; }

    public String getEan() { return ean; }

    public String getImageURL() { return imageURL; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUvp() {
        return uvp;
    }

    public void setUvp(String uvp) {
        this.uvp = uvp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
