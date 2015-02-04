'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('myApp.services', []).value('version', '0.1');

angular.module('myApp.services', []).factory("services", ['$http', '$location',
                     function ($http, $q, $location) {

                         var serviceBase = '';

                         var obj = {};

                         obj.get = function (q) {
                             return $http.get(serviceBase + q).then(function (results) {
                                 return results;
                             });
                         };
                         obj.post = function (q, object) {
                             return $http.post(serviceBase + q, object).then(function (results) {
                                 return results;
                             });
                         };
                         obj.put = function (q, object) {
                             return $http.put(serviceBase + q, object).then(function (results) {
                                 return results;
                             });
                         };
                         obj.delete = function (q) {
                             return $http.delete(serviceBase + q).then(function (results) {
                                 return results;
                             });
                         };
                         return obj;
                 }]);
