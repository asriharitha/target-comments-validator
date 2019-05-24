package com.comments.validator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comments.validator.model.ProductComment;

@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment,Long>{

}
