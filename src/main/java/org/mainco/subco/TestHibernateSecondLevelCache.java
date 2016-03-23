package org.mainco.subco;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ImportResource(value = "classpath:/spring.xml")
@ComponentScan
public class TestHibernateSecondLevelCache {

	private final static Logger logger = LoggerFactory
			.getLogger(TestHibernateSecondLevelCache.class);
	
	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseFactory factory = new EmbeddedDatabaseFactory();
		factory.setDatabaseType(EmbeddedDatabaseType.H2);
		EmbeddedDatabase database = factory.getDatabase();
		return database;
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource,
			EntityManagerFactory entityManagerFactory) throws IOException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setDataSource(dataSource);
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				TestHibernateSecondLevelCache.class)) {
			TestHelper testHelper = context.getBean(TestHelper.class);
			String itemId = testHelper.createTestItem();
			testHelper.evictCache();
			
			logger.info("\n\n\n\n * * * Hibernate Second Level Cache Test * * * \n\n\n\n");

			ContentService contentService = context.getBean(ContentService.class);

			logger.info("\n\n1st invocation:");
			String content1 = contentService.getContent(itemId, null);
			logger.info("\n\n2nd invocation:");
			String content2 = contentService.getContent(itemId, null);

//			System.out.println(content1);
//			System.out.println(content2);
			
			logger.info("\n\n\n\n * * * End of Hibernate Second Level Cache Test * * * \n\n\n\n");
		}
	}

}
