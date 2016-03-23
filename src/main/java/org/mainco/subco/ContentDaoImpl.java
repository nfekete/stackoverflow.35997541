package org.mainco.subco;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class ContentDaoImpl implements ContentDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Item findItemById(String id) {
		Item find = entityManager.find(Item.class, id);
		return find;
	}

}
