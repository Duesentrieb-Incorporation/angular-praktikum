var homeModule = angular.module('demoApp.home', ['ngRoute']);

/***
 * Modulkonfiguration.
 */
homeModule.config(['$routeProvider', function ($routeProvider)
{
    "use strict";

    $routeProvider.when('/home',
    {
        templateUrl: 'home/home.html',
        controller: 'HomeController as ctrl'
    });
}]);
