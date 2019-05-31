package com.comments.validator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comments.validator.model.ProductComment;
import com.comments.validator.repository.ProductCommentRepository;

@Service
public class CommentService {

	@Autowired
	ProductCommentRepository productCommentRepository;
	@Autowired
	ProfanityCheckService profanityCheckService;

	public List<ProductComment> getByProduct(Long id) {

		return productCommentRepository.findByProductId(id);

	}

	public String postComment(ProductComment prodComment) {
		if (profanityCheckService.checkBadWords(prodComment.getComment())) {
			return "Comments cannot be posted due to objectionable content";
		} else
			return "Comments posted successfully!!";
	}

	public List<ProductComment> getByProductAndComment(Long id, Long cId) {
		List<ProductComment> proCom = productCommentRepository.findByProductId(id);
		List<ProductComment> result = new ArrayList<>();

		for (ProductComment p : proCom) {
			if (p.getCommentId().compareTo(cId) == 0) {
				result.add(p);
			}
		}

		return result;
	}

	public List<ProductComment> getAllComments() {
		return productCommentRepository.findAll();
	}

	public ProductComment getById(Long id) {
		return productCommentRepository.findByCommentId(id);
	}

	public void saveComment(ProductComment comment) {
		productCommentRepository.saveAndFlush(comment);
	}

	public void deleteComment(Long id) {
		productCommentRepository.deleteById(id);
	}

	public void deleteAllComments() {
		productCommentRepository.deleteAll();
	}

}
