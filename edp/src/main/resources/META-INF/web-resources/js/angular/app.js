'use strict';


// Declare app level module which depends on filters, and services
angular.module('myApp', [
                         'ngRoute', 
                         'ui.bootstrap', 
                         'ngAnimate',
                         'ngDragDrop', 
                         'myApp.filters',
                         'myApp.services',
                         'myApp.directives',
                         'myApp.controllers'
                       ]).

                       
config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {templateUrl: 'pages/partial1.html', controller: 'MyCtrl1'});
  $routeProvider.when('/view2', {templateUrl: 'pages/partial2.html', controller: 'MyCtrl2'});
  $routeProvider.when('/view3', {templateUrl: 'pages/partial3.html', controller: 'taskCtrl'});
  $routeProvider.otherwise({redirectTo: '/'});
}]);


