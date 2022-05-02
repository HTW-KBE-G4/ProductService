package de.tanukihardwarestore.ProductService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Long cpu_id;

    private Long gpu_id;

    private Long psu_id;

    private Long mb_id;

    private Long ram_id;

    private Long storage_id;

    public Product() {}


    public Product(Long id, Long cpu_id, Long gpu_id, Long psu_id, Long mb_id, Long ram_id, Long storage_id, String name) {
        this.id = id;
        this.cpu_id = cpu_id;
        this.gpu_id = gpu_id;
        this.psu_id = psu_id;
        this.mb_id = mb_id;
        this.ram_id = ram_id;
        this.storage_id = storage_id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cpu_id=" + cpu_id +
                ", gpu_id=" + gpu_id +
                ", psu_id=" + psu_id +
                ", mb_id=" + mb_id +
                ", ram_id=" + ram_id +
                ", storage_id=" + storage_id +
                '}';
    }

    public Long getCpu_id() {
        return cpu_id;
    }

    public Long getGpu_id() {
        return gpu_id;
    }

    public Long getId() {
        return id;
    }

    public Long getMb_id() {
        return mb_id;
    }

    public Long getPsu_id() {
        return psu_id;
    }

    public Long getRam_id() {
        return ram_id;
    }

    public Long getStorage_id() {
        return storage_id;
    }

    public String getName() {
        return name;
    }
}
