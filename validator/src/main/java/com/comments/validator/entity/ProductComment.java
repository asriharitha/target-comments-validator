package com.comments.validator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProductComment {
	
	@Column(name="customerId")
	private Long customerId;
	
	@Id
	@GeneratedValue
	@Column(name="commentId")
	private Long commentId;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="productId")
	private Long productId;

	public ProductComment() {
		
	}
	
	@Override
	public String toString() {
		
		return customerId + ":(comment=" + comment + ")";
	}
}
