/***
 * Demonstriert die Nutzung eines einfachen Services.
 * Dieser wird im Article- und ArticleDetailController benötigt um
 * jeweils die selben Artikel zugreifen zu können.
 *
 * Der Service wird verwendet um die Master- und Detailansicht zu testen.
 * In einer vollständigen Anwendung simuliert man mit Mocks komplette Services.
 * Dies ist hier nicht notwendig und es reichen zwei "Leseoperationen".
 */
demoApp.factory('ArticleMock', [function ()
{
    // TODO 3.5
    
    'use strict';

    var articles = [];

    return (
    {
        "getArticles": function ()
        {
        },
        "getArticle": function (index)
        {
        }
    });
}]);
