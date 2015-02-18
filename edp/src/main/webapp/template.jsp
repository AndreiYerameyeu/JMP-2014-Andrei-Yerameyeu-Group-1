<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>


<html lang="en" ng-app="myApp">
<head> 
  <meta charset="utf-8">
  <title>Ticket Application</title>
	<meta name="author" content="Dmitry Shanko" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <!-- Loading Bootstrap -->
    <link href="resources/css/vendor/developer/bootstrap.css" rel="stylesheet">

    <!-- Loading Flat UI Pro -->
    <link href="resources/css/vendor/developer/flat-ui-pro.css" rel="stylesheet">

    <link rel="shortcut icon" href="resources/img/favicon.ico">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="resources/js/vendor/html5shiv.js"></script>
      <script src="resources/js/vendor/respond.min.js"></script>
    <![endif]-->
    
  <script src="resources/js/vendor/developer/require.js"></script>
  <script src="resources/js/vendor/developer/jquery.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
  <script src="resources/js/vendor/developer/flat-ui-pro.js"></script>
  <script src="resources/js/vendor/developer/application.js"></script>
 
  <script src="resources/js/vendor/developer/angular.js"></script>
  <script src="resources/js/vendor/developer/angular-route.js"></script>
  <script src="resources/js/vendor/developer/angular-animate.js"></script>
  <script src="resources/js/vendor/developer/angular-dragdrop.js"></script>
 
  <script src="resources/js/ui-bootstrap-tpls-0.12.0.min.js"></script>

  <script src="resources/js/load.js" type="text/javascript"></script>
  
  <script src="resources/js/angular/app.js" type="text/javascript"></script>
  <script src="resources/js/angular/services.js"></script>
  <script src="resources/js/angular/controllers.js"></script>
  <script src="resources/js/angular/filters.js"></script>
  <script src="resources/js/angular/directives.js"></script>


</head>
<body>

  <style>
      html {
          position: relative;
          min-height: 100%;
      }

      body {
          margin-top: 50px;
          margin-bottom: 108px;
      }

      .footer {
          height: 108px;
          width: 100%;
          position: absolute;
          bottom: 0;
      }
  </style>

  <div class="container">
  <!--NAV BEGIN-->
  <div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="sr-only">Toggle navigation</span>
        </button>
        <a class="navbar-brand" href="/">Ticket App</a>
      </div>
      <div class="navbar-collapse collapse" aria-expanded="false">

        <ul class="nav navbar-nav">
          <li><a href="<c:url value="/"/>">Home</a></li>
        </ul>

      </div>
    </div>
  </div>
  <!--NAV END-->

  <div class="container">
    <div class="row clearfix">
      <div class="col-md-12 column">
        <!--PAGE CONTENT BEGIN-->
          <div ng-view></div>
        <!--PAGE CONTENT END-->
      </div>
    </div>
  </div>
 </div>

  <!--FOOTER BEGIN-->
  <footer class="footer">
    <div class="bottom-menu">
      <div class="container">
        <div class="row">
          <div class="col-md-2 col-sm-2">
            <a href="<c:url value="/"/>" class="bottom-menu-brand">Ticket App</a>
          </div>
          <div class="col-md-8 col-sm-8">
            <ul class="bottom-menu-list">
              <li><a href="#fakelink">Links</a></li>
            </ul>
          </div>
          <ul>
            <li>Footer app: v<span app-version></span></a></li>
          </ul>
        </div>
      </div>
    </div>
  </footer>
 <!--FOOTER END -->





</body>
</html>
