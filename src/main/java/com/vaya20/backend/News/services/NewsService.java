package com.vaya20.backend.News.services;

import com.vaya20.backend.News.domain.News;

public interface NewsService {
	public News getNews(long id);
	public void save(News news);
}
