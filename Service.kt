Services are of three type
1. Foreground service - A foreground service performs some operation that is noticeable to the user. For example, an audio app would use a foreground service to play an audio track. Foreground services must display a Notification. Foreground services continue running even when the user isn't interacting with the app.
2. Background service - A background service performs an operation that isn't directly noticed by the user. For example, if an app used a service to compact its storage, that would usually be a background service.
3. Bound service - A service is bound when an application component binds to it by calling bindService().
A bound service only runs as long as at least one application component is connected (bound) to it. When the last component disconnects, the service is destroyed.
Any component, even from a separate application, can use a service by sending it an Intent. However, you can make the service private in the app's manifest file to block access from other apps.


IMP Use-cases for Bound - 
When you want to securely expose some of your app's functionality to be used by other applications.
Scenario: You've built a weather app with accurate forecasting data. Another app, like a calendar or a home screen widget, wants to display today's weather from your app.
How it works: Your weather app exposes a bound service with a clear interface (e.g., a getTodaysForecast() method). The calendar app can then bind to your service to call that method and receive the weather data. The Android system handles the secure communication between the two separate applications.


When we call startService passing a intent with some data in some cases) it invokes onStarCommand
When we call bindService it invokes onBind

Easy Lifecycle explanation -> https://developer.android.com/static/images/service_lifecycle.png


Service run on which thread -> https://www.linkedin.com/posts/amit-shekhar-iitbhu_softwareengineer-androiddev-android-activity-7283717741130215424-Vn39/
