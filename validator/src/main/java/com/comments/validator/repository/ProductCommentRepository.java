package com.comments.validator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comments.validator.model.ProductComment;

@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {

	List<ProductComment> findByProductId(Long id);

	ProductComment findByCommentId(Long id);

}
