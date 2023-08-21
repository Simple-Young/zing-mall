package com.yeeph.search.service;

import com.yeeph.search.vo.SearchParam;
import com.yeeph.search.vo.SearchResult;

public interface SearchService {
    SearchResult getSearchResult(SearchParam searchParam);
}
