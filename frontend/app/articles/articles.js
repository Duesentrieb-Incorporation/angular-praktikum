// TODO 3.3

var articlesModule = angular.module('demoApp.articles', ['ngRoute']);

/***
 * Modulkonfiguration.
 */
articlesModule.config(['$routeProvider', function ($routeProvider)
{
    "use strict";

    $routeProvider
    .when('/articles',
    {
        templateUrl: 'articles/articles.html',
        controller: 'ArticleController as ctrl'
    })
    .when('/articles/:articleID',
    {
    });
}]);
