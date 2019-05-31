package com.comments.validator.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.comments.validator.model.ProductComment;
import com.comments.validator.repository.ProductCommentRepository;
import com.comments.validator.service.CommentService;
import com.comments.validator.util.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value=CommentController.class)
public class CommentsControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired 
	ObjectMapper mapper;
	
	@Autowired 
	ProductCommentRepository productCommentRepository;
	
	@Autowired
	private CommentService commentService;
	
	@BeforeClass
	public static void setUpBeforeClass() {

	}
	
	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	
	}
	
	@Test
	public void createComment() throws Exception {
		
		ProductComment mockProductComment = TestUtils.generateComment(1L);
		Mockito.when(commentService.getById(mockProductComment.getCommentId())).thenReturn(mockProductComment);
		
		ResultActions resultActions = mockMvc.perform(post("/comment/post")).andExpect(status().isOk());
		String prodCom = mapper.readValue(resultActions.andReturn().getResponse()
															.getContentAsString(),String.class);
		
		String expected = "Comments cannot be posted due to objectionable content";
		assertEquals(prodCom,expected);
	}
	
	@Test
	public void getComment() throws Exception {
		
		ProductComment mockProductComment = TestUtils.generateComment(1L);
	}
	

}
