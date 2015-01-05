var adminModule = angular.module('demoApp.admin', ['ngRoute']);

/***
 * Modulkonfiguration.
 */
adminModule.config(['$routeProvider', function ($routeProvider)
{
    "use strict";

    $routeProvider

    .when('/admin/articles',
    {
        templateUrl: 'admin/admin.html',
        controller: 'AdminController as ctrl'
    })
    .when('/admin/articles/create',
    {
        templateUrl: 'admin/admin-create.html',
        controller: 'AdminCreateController as ctrl'
    })
    .when('/admin/articles/edit/:articleID',
    {
        templateUrl: 'admin/admin-edit.html',
        controller: 'AdminEditController as ctrl'
    });
}]);
