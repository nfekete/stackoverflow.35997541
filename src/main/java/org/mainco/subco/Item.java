package org.mainco.subco;

import java.util.UUID;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Cacheable
public class Item {

	private String id = UUID.randomUUID().toString();
	private ItemParent parent;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public ItemParent getParent() {
		return parent;
	}

	public void setParent(ItemParent itemParent) {
		this.parent = itemParent;
	}

}
