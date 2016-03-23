package org.mainco.subco;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TestHelper {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public String createTestItem() {
		Item item = new Item();
		ItemParent parent = new ItemParent();
		Product product = new Product();
		item.setParent(parent);
		parent.setProduct(product);
		for (int i=0; i<10; i++) {
			Category category = new Category("category" + i);
			entityManager.persist(category);
			product.getCategories().add(category);
		}
		
		entityManager.persist(item);
		return item.getId();
	}
	
	@Transactional
	public void evictCache() {
		Session session = (Session) entityManager.getDelegate();
		SessionFactory sessionFactory = session.getSessionFactory();
		sessionFactory.getCache().evictAllRegions();
	}

}
