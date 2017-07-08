APM_Alert
=========

APM_Alert is a tool to send text mesagges using a mysql database, an Android phone and a PHP server.


Dependencies
------------

For the android device:
* An Android device running an Android version compatible with the API 8 or higher.

For the server:
* A MySQL server
* Apache with PHP plugins


Motivation
----------

APM_Alert provides a not difficult method to send SMS alerts from any application using only an Android phone and a MySQL database. It's like extending the phone capabilities to an aplication. Also another benefit it's that the doesn't need to be in the same network, it only needs an access to internet or to the server (and some cash for sending messages). Since is built using PHP and MySQL it's easy to integrate on any PHP application or Desktop application.



How to use it
-------------

Read the README.MD provided for each folder in the project.


How it works
------------

### Serverside

There are two .php and one .sql files.

###### get.php
Provides an interface to get the actual alert to send if exists.
###### post.php
Provides an interface to set an alert as sended.
###### create_alert_table.sql
Creates the MySQL table used for providing alerts.

Alerts are sent when a new field on the table is created. The read mark must be left on 0 if this alert has to be sended or 1 if it is already. Both .php files have to be accesible from the Android device.


### AndroidAlertSystem

The aplication does a continue polling each 5 seconds to check for new alerts. You have to provide the actual direction of the previously mentioned get.php and post.php files. When an alert is recived, the alert is marked as read and the phone sends a message to the specified number. Each alert is procesed one by one, so, if there are 5 alerts, sending the 5 alerts take 25 seconds. That way, the operator doesn't block the message sending process.

This runs on an android service both as an application and as a service, and the main cycle must be launched by the user.



TODO
----

* Improve the performance of the polling
* Add verification capabilities (to both Android and PHP)
* Fully implement sending checks
* Fix stop issue (currently, the stop option doesn't work)
* Add "Start on boot" option (to be evaluated yet)


