package com.comments.validator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.comments.validator.model.ProductComment;
import com.comments.validator.service.CommentService;

@Controller
@RequestMapping(value = "/target")
public class CommentController {

	@Autowired
	CommentService commentService;

	@RequestMapping(value = "/product/comment/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<ProductComment>> getComments(@PathVariable(value = "id") Long id) {

		return new ResponseEntity<List<ProductComment>>(commentService.getByProduct(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/product/comment/", method = RequestMethod.GET)
	public ResponseEntity<List<ProductComment>> getComments() {

		return new ResponseEntity<List<ProductComment>>(commentService.getAllComments(), HttpStatus.OK);
	}

	@RequestMapping(value = "/comment/post/", method = RequestMethod.POST)
	public ResponseEntity<String> postComments(@RequestBody ProductComment prodComment) {

		return new ResponseEntity<>(commentService.postComment(prodComment), HttpStatus.OK);

	}

	@RequestMapping(value = "/product/{id}/comment/{cId}", method = RequestMethod.GET)
	public ResponseEntity<List<ProductComment>> getCommentsByProdAndCommnent(@PathVariable(value = "id") Long id,
			@PathVariable(value = "cId") Long cId) {

		return new ResponseEntity<List<ProductComment>>(commentService.getByProductAndComment(id, cId), HttpStatus.OK);
	}

	@RequestMapping(value = "/comment/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ProductComment> updateComment(@PathVariable(value = "id") Long id,
			@RequestBody ProductComment prodComm) {

		ProductComment comment = commentService.getById(id);
		if (comment.getCommentId() == null) {
			return new ResponseEntity<ProductComment>(HttpStatus.NOT_FOUND);
		}

		comment.setComment(prodComm.getComment());
		commentService.saveComment(comment);

		return new ResponseEntity<ProductComment>(comment, HttpStatus.OK);
	}

	@RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ProductComment> deleteComment(@PathVariable(value = "id") Long id) {

		ProductComment comment = commentService.getById(id);
		if (comment.getCommentId() != null) {
			commentService.deleteComment(id);
		}
		return new ResponseEntity<ProductComment>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/delete/", method = RequestMethod.DELETE)
	public ResponseEntity<ProductComment> deleteComments() {

		commentService.deleteAllComments();
		return new ResponseEntity<ProductComment>(HttpStatus.NO_CONTENT);
	}

}
