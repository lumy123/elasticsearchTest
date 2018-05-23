/**
 * 
 */
package com.lzu.elasticSearchTest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lzu.elasticSearchTest.domain.es.EsBlog;
import com.lzu.elasticSearchTest.repository.es.EsBlogRepository;

/**
 * Blog控制类
 * @author lum
 *
 */
@RestController
@RequestMapping("/blogs")
public class BlogController {
	@Autowired
	private EsBlogRepository esBlogRepository;
	@GetMapping
	public List<EsBlog> list(@RequestParam(value="title") String title,
			@RequestParam(value="summary") String summary,
			@RequestParam(value="content") String content,
			@RequestParam(value="pageIndex",defaultValue="0") int pageIndex,
			@RequestParam(value="pageSize",defaultValue="10") int pageSize){
		//数据在Test初始化
		Pageable pageable=PageRequest.of(pageIndex, pageSize);
		Page<EsBlog> page=esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(title, summary, content, pageable);
		return page.getContent();
		
	}

}
