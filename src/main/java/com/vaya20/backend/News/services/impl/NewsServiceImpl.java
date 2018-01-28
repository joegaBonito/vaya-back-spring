package com.vaya20.backend.News.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaya20.backend.News.domain.News;
import com.vaya20.backend.News.repositories.NewsRepository;
import com.vaya20.backend.News.services.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
	
	@Autowired
	NewsRepository newsRepository;
	
	NewsServiceImpl(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}

	@Override
	public News getNews(long id) {
		return newsRepository.findOne(id);
	}

	@Override
	public void save(News news) {
		newsRepository.save(news);
	}

}
