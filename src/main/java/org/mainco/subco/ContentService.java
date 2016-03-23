package org.mainco.subco;

import java.io.IOException;

public interface ContentService {

	String getContent(String itemId, String pageNumber) throws IOException;

}
