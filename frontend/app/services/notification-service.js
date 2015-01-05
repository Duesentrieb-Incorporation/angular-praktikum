/***
 * Kann verwendet werden um verschiedene Nachrichten im Browser anzuzeigen.
 * Siehe dazu: Bootstrap --> Alerts.
 */
demoApp.factory('NotificationService', [function ()
{
    'use strict';

    var notifications = [];

    return (
    {
        "getNotifications": function ()
        {
            return notifications;
        },
        "addNotification": function (notification)
        {
            notifications.push(
                {
                    "type": notification.type,
                    "title": notification.title,
                    "message": notification.message
                }
            );
        },
        "addNotificationPlain": function (type, title, message)
        {
            notifications.push(
                {
                    "type": type,
                    "title": title,
                    "message": message
                }
            );
        },
        "removeNotification": function (notification)
        {
            notifications.splice(notifications.indexOf(notification), 1);
        }
    });
}]);
