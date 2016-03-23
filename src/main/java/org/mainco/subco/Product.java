package org.mainco.subco;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
public class Product {

	private String id = UUID.randomUUID().toString();
	private List<Category> categories = new ArrayList<Category>();

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "product_category", joinColumns = {
			@JoinColumn(name = "PRODUCT_ID") }, inverseJoinColumns = {
					@JoinColumn(name = "CATEGORY_ID") })
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
