package com.comments.validator.util;

import com.comments.validator.model.ProductComment;

public class TestUtils {
	
	public static ProductComment generateComment(Long id) {
		
		ProductComment mockProductComment = null;
		mockProductComment.setComment("This product is really useful!!");
		mockProductComment.setCommentId(id);
		mockProductComment.setCustomerId(123L);
		mockProductComment.setProductId(111L);
		
		return mockProductComment;
	}

}
