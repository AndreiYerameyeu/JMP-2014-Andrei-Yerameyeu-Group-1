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
			<div class="col-xs-6">
				<form role="form" id="form">
					<div class="alert alert-danger" style="display: none;">
						<p></p>
					</div>
					<input type="hidden" id="id">
					<div class="form-group">
						<label for="bookmarkURL">Bookmark URL</label> <input type="url"
							class="form-control" id="url" placeholder="Enter bookmark URL">
					</div>
					<div class="form-group">
						<label for="bookmarkTitle">Bookmark Title</label> <input
							type="text" class="form-control" id="title"
							placeholder="Enter bookmark title">
					</div>
					<div class="form-group">
						<label for="bookmarkTags">Tags: (separated by commas)</label> <input
							type="text" class="form-control" id="tags"
							placeholder="Enter bookmark tags">
					</div>

					<button id="btnSave" class="btn btn-success">Save</button>
					<button id="btnClear" class="btn btn-default">Clear</button>
				</form>
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
	<script type="text/template" id="tagCount_template">
		<a href="{{link}}">{{tag}}({{count}})</a>
	</script>
	<script type="text/template" id="bookmark_template">
		{{title}}({{url}})
	</script>
	<script type="text/template" id="bookmarkTagList_template">
		{{tag}}
	</script>
</body>
</html>