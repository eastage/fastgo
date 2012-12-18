package com.southlake.quicklife.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/commodity")
public class CommodityController {
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String read(@PathVariable("id") Long id) {
    	System.out.println("CommodityController.doGet");
    	return "test";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(@PathVariable("id") Long id) {
    	System.out.println("CommodityController.doPut");
    	return "test";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public String create(@PathVariable("id") Long id) {
    	System.out.println("CommodityController.doPost");
    	return "test";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id) {
    	System.out.println("CommodityController.doDelete");
    	return "test";
	}
    
    /* 
     * request a list of items
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
	public String readAll() {
    	System.out.println("CommodityController.list");
    	return "test";
	}
}
