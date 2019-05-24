package com.comments.validator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.comments.validator.model.ProductComment;
import com.comments.validator.repository.ProductCommentRepository;

@Service
public class CommentService {
	
	@Autowired
	ProductCommentRepository productCommentRepository;

	public ResponseEntity<List<ProductComment>> getByProduct(Long id) {
		
		List<ProductComment> proCom = productCommentRepository.findByProductId(id);
		return ResponseEntity.ok().body(proCom);
	}

	public ResponseEntity<ProductComment> postComment(ProductComment prodComment) {
		return ResponseEntity.ok().body(productCommentRepository.save(prodComment));
	}

	public ResponseEntity<List<ProductComment>> getByProductAndComment(Long id, Long cId) {
		List<ProductComment> proCom = productCommentRepository.findByProductId(id);
		List<ProductComment> result = new ArrayList<>();
		
		for(ProductComment p : proCom) {
			if(p.getCommentId().compareTo(cId)==0) {
				result.add(p);
			}
		}
		
		return ResponseEntity.ok().body(result);
		
	
	}

}
