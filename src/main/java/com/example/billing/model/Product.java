package com.example.billing.model;

import java.util.Optional;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Product name is required.")
	@Column(name = "name", nullable = false)
	private String name;

	@NotBlank(message = "Product category is required.")
	@Column(name = "category", nullable = false)
	private String category;

	@NotBlank(message = "Product description is required.")
	@Column(name = "description", nullable = false)
	private String description;

	@NotNull(message = "Product material is required.")
	@Column(name = "material", nullable = false)
	private String material;

	@NotNull(message = "Product purity is required.")
	@Column(name = "purity", nullable = false)
	private Double purity;

	@NotNull(message = "Product gross weight is required.")
	@Column(name = "gross_weight", nullable = false)
	private Double grossWeight;

	@NotNull(message = "Product stone weight is required.")
	@Column(name = "stone_weight", nullable = false)
	private Double stoneWeight;

	@NotNull(message = "Product net weight is required.")
	@Column(name = "net_weight", nullable = false)
	private Double netWeight;

	@NotBlank(message = "Product HSNCode is required.")
	@Column(name = "hsncode", nullable = false)
	private String hsnCode;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "store_id", referencedColumnName = "id")
	private Stores stores; 

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPurity() {
		return purity;
	}

	public void setPurity(Double purity) {
		this.purity = purity;
	}

	public Double getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Double getStoneWeight() {
		return stoneWeight;
	}

	public void setStoneWeight(Double stoneWeight) {
		this.stoneWeight = stoneWeight;
	}

	public Double getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

    public Stores getStores() {
        return stores;
    }

    public void setStores(Optional<Stores> storesOptional) {
        Stores defaultStores = getDefaultStores(); 
        Stores stores = storesOptional.orElse(defaultStores); 
        this.stores = stores; 
    }
    
    private Stores getDefaultStores() {
        return new Stores(); 
    }

}
