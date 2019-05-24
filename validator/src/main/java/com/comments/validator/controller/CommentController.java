package com.comments.validator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.comments.validator.model.ProductComment;
import com.comments.validator.service.CommentService;

@RestController
@RequestMapping(value="/target")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value="/product/{id}/comment", method=RequestMethod.GET)
	public ResponseEntity<List<ProductComment>> getComments(@PathVariable(value="id") Long id) {
		
		return commentService.getByProduct(id);
		
	}
	
	@RequestMapping(value="/product/{id}/comment", method=RequestMethod.POST)
	public ResponseEntity<ProductComment> postComments(@RequestBody ProductComment prodComment) {
		
		return commentService.postComment(prodComment);
		
	}
	
	@RequestMapping(value="/product/{id}/comment/{cId}", method=RequestMethod.GET)
	public ResponseEntity<List<ProductComment>> getCommentsByProdAndCommnent(
			@PathVariable(value="id") Long id, @PathVariable(value="cId") Long cId) {
		
		return commentService.getByProductAndComment(id,cId);
		
	}
}
