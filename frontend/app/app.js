var demoApp = angular.module("demoApp", [
    "ngRoute",
    "ngResource",
    "demoApp.home",
    "demoApp.articles",
    "demoApp.admin"
]);

demoApp.config(function ($routeProvider)
{
    "use strict";

    $routeProvider.otherwise({redirectTo: "/home"});
});

demoApp.controller("MainController", ['NotificationService',  function (NotificationService)
{
    "use strict";

    this.notifications = NotificationService.getNotifications();

    this.remove = function (notification)
    {
        NotificationService.removeNotification(notification);
    };
}]);
