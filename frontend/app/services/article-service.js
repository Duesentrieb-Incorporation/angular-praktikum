/***
 * Vorkonfigurierter Resource Service.
 * 
 * Kann zum versenden von Create, Read, Update, Destroy (CRUD) Operationen
 * an das Backend verwendet werden.
 */
demoApp.factory('Article', ['$resource', function ($resource)
{
    'use strict';

    return $resource
    (
        // parametrized URL
        'http://localhost:8000/articles/:id',

        // default values for parameters
        {
            "id": "@id"
        },

        // declaration of custom actions
        {
            update:
            {
                method: "PUT"
            }
        },

        // custom settings (not used)
        {

        }
    );
}]);
