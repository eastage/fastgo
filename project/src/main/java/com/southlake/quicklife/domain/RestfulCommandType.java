package com.southlake.quicklife.domain;

/*
 * rest/db/all-category
 * rest/db/category-items
 * POST rest/db/search-items
 * rest/db/all-community
 * rest/db/community-info ��װ��ҳ���ʱ��Ͱѵ�ǰ¥�̵���Ϣһ���Ի�ȡ��
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
