package org.mainco.subco;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentDao contentDao;

	@Transactional(readOnly = true)
	public String getContent(String itemId, String pageNumber)
			throws IOException {
		Item item = contentDao.findItemById(itemId);
		StringBuilder sb = new StringBuilder();
		for (Category category : item.getParent().getProduct().getCategories()) {
			sb.append(category.getName());
			sb.append(" ");
		}
		return sb.toString();
	}

}
