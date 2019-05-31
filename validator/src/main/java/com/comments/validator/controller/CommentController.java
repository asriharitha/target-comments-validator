package com.comments.validator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		
		return new ResponseEntity<List<ProductComment>>(commentService.getByProduct(id), HttpStatus.OK);
	}
	
	@RequestMapping(value="/product/{id}/comment", method=RequestMethod.POST)
	public ResponseEntity<String> postComments(@RequestBody ProductComment prodComment) {
		
		return new ResponseEntity<>(commentService.postComment(prodComment), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/product/{id}/comment/{cId}", method=RequestMethod.GET)
	public ResponseEntity<List<ProductComment>> getCommentsByProdAndCommnent(
			@PathVariable(value="id") Long id, @PathVariable(value="cId") Long cId) {
		
		return new ResponseEntity<List<ProductComment>>(commentService.getByProductAndComment(id,cId),HttpStatus.OK);
		
	}
}
