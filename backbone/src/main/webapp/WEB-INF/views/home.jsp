<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<c:set var="root" value="" scope="request" />
<c:set var="img" value="${root}/resources/images" scope="request" />
<c:set var="css" value="${root}/resources/css" scope="request" />
<c:set var="js" value="${root}/resources/js" scope="request" />
<c:set var="lib" value="${root}/resources/lib" scope="request" />

<html lang="en">
<head>

<!-- Mobile specific metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- Force IE9 to render in normal mode -->
<!--[if IE]><meta http-equiv="x-ua-compatible" content="IE=9" /><![endif]-->
<meta name="author" content="DmitryShanko" />

<meta name="keywords" content="" />
<meta name="application-name" content="Backbone Bookmark App" />
<meta charset="utf-8">
<title>Backbone Bookmark App</title>

<link rel="stylesheet" href="<c:url value="${css}/bootstrap/css/bootstrap.css"/>" type="text/css">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col=xs-12">
				<h1>Bookmarking App</h1>
			</div>
		</div>
		<div>
			<div class="col-xs-6" id="formAppender">
				
			</div>
		</div>
		<div class="row">
			<!-- Bookmark list -->
			<div class="col-xs-10">
				<div id="bookmarkListContainer">
					<h2>
						Filtered by Tag: <span id="bookmarkTagFilter">None</span> | <a
							id="clearFilter" href="#">Clear Filter</a>
					</h2>
					<ul id="bookmarkList">
						
					</ul>
				</div>
			</div>
			<!-- Tag list   -->
			<div class="col-xs-2">
				<h4>Bookmark's Tags</h4>
				<ul id="tagCountList">
				</ul>
			</div>
		</div>
	</div>
	
	<script src="<c:url value="${lib}/jquery.js"/>"></script>
	<script src="<c:url value="${lib}/underscore.js"/>"></script>
	<script src="<c:url value="${lib}/backbone.js"/>"></script>
	<script src="<c:url value="${js}/app.js"/>"></script>
	<script type="text/template" id="tag-count-template">
		<a href="{{=link}}">{{=tag}}({{=count}})</a>
	</script>
	<script type="text/template" id="bookmark-template">
		<a href="#/edit/{{=id}}" style="margin-right: 5px;" class="btn btn-warning">Edit</a><a href="#/delete/{{=id}}" style="margin-right: 5px;" class="btn btn-danger">Delete</a>{{=title}}({{=url}}){{ if(tags.length > 0) { }} | {{ for (var i = 0; i < tags.length; i++) { }}{{ var tag = tags[i]; }}{{= tag.tag }}{{ if (i < tags.length - 1) { }}, {{ } }}{{ }; }}{{ }; }} 
	</script>
	<script type="text/template" id="bookmark-tag-list-template">
		{{ _.each(tags, function(tagModel) { }} {{= tagModel.tag }}, {{ }); }}
	</script>
	<script type="text/template" id="bookmark-form-template">
		<form role="form" id="bookmark-form">
			<div class="alert alert-danger" style="display: none;">
				<p></p>
			</div>
			{{ if(obj.id) { }}
			<input type="hidden" id="id" name="id" value="{{= id }}">
			{{ }; }}
			<div class="form-group">
				<label for="bookmarkURL">Bookmark URL</label> 
				<input type="url" name="url" class="form-control" id="url" placeholder="Enter bookmark URL" value="{{= url }}">
			</div>
			<div class="form-group">
				<label for="bookmarkTitle">Bookmark Title</label> 
				<input type="text" class="form-control" id="title" name="title"	placeholder="Enter bookmark title" value="{{= title }}">
			</div>
			<div class="form-group">
				<label for="bookmarkTags">Tags: (separated by commas)</label> 
				<input type="text" class="form-control" id="tags" name="tags" placeholder="Enter bookmark tags" value="{{ if(tags.length > 0) { }}{{ for (var i = 0; i < tags.length; i++) { }}{{ var tag = tags[i]; }}{{= tag.tag }}{{ if (i < tags.length - 1) { }}, {{ } }}{{ }; }}{{ }; }}">
			</div>
			<button id="btnSave" class="btn btn-success">Save</button>
			<button id="btnClear" class="btn btn-default">Clear</button>
		</form>
	</script>
</body>
</html>