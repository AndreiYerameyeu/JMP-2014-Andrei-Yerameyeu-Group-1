'use strict';


// Declare app level module which depends on filters, and services
angular.module('myApp', [
                         'ngRoute', 
                         'ui.bootstrap', 
                         'myApp.filters',
                         'myApp.services',
                         'myApp.directives',
                         'myApp.controllers'
                       ]).

                       
config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view1', {templateUrl: 'pages/partial1.html', controller: 'MyCtrl1'});
  $routeProvider.otherwise({redirectTo: '/'});
}]);


