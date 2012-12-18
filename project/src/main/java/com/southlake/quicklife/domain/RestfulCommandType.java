package com.southlake.quicklife.domain;

/*
 * rest/db/all-category
 * rest/db/category-items
 * POST rest/db/search-items
 * rest/db/all-community
 * rest/db/community-info 在装载页面的时候就把当前楼盘的信息一次性获取到
 * 
 */
public enum RestfulCommandType {
        // GET commands
		GET_ALL_CATEGORY, 
        GET_CATEGORY_ITEMS, 
        GET_ALL_COMMUNITY, 
        GET_COMMUNITY_INFO,
        
        // POST commands
        POST_SEARCH_ITEMS,
        
        // Invalid command
        INVALID_POST_GET;
}
