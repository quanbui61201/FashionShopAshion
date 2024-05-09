<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="shop_sidebar">
    <div class="sidebar_sizes">
	    <div class="section-title">
	        <h4>Shop by name</h4>
	    </div>
	    <input type="text" class="form-control" id="keyword" name="keyword" placeholder="Search keyword" />
    </div>
    <div class="sidebar_sizes">
	    <div class="section-title">
	        <h4>Shop by category</h4>
	    </div>
	    <div class="size_list">
	    	<label for="all_categories">
                <input type="radio" id="all_categories" name="categoryId" value="0">All
                <span class="checkmark"></span>
            </label>
            <c:forEach var="category" items="${categories}" varStatus="loop">
            	<label for="category_${category.id}">
                    <input type="radio" id="category_${category.id}" name="categoryId" value="${category.id}">${category.name}
                    <span class="checkmark"></span>
                </label>
            </c:forEach>
	    </div>
    </div>
    <div class="sidebar_sizes">
	    <div class="section-title">
	        <h4>Shop by price</h4>
	    </div>
	    <div class="size_list">
	    	<label for="all_price">
                <input type="radio" id="all_price" name="productPrice" value="0">All
                <span class="checkmark"></span>
            </label>
	    	<label for="$0-50">
                <input type="radio" id="$0-50" name="productPrice" value="1">$0 - $50
                <span class="checkmark"></span>
            </label>
	    	<label for="$50-200">
                <input type="radio" id="$50-200" name="productPrice" value="2">$50 - $200
                <span class="checkmark"></span>
            </label>
	    	<label for="$200-500">
                <input type="radio" id="$200-500" name="productPrice" value="3">$200 - $500
                <span class="checkmark"></span>
            </label>
	    	<label for="$500-1000">
                <input type="radio" id="$500-1000" name="productPrice" value="4">$500 - $1000
                <span class="checkmark"></span>
            </label>
	    	<label for="$1000+">
                <input type="radio" id="$1000+" name="productPrice" value="5">$1000+
                <span class="checkmark"></span>
            </label>
	    </div>
    </div>
    <div class="sidebar_filter">
        <button type="submit" id="btnSearch" name="btnSearch">Filter</button>
    </div>
    <div class="sidebar_paginate">
	    <input id="currentPage" name="currentPage" class="form-control invisible" value="${productSearch.currentPage }">
    </div>
</div>