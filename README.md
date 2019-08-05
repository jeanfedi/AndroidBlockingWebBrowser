# AndroidBlockingWebBrowser
A simple application to block tracking services over web (in this example, Facebook)
This example blocks FacebookAnalytics tracking, but can easily extender to every tracker.

## How to use it
The core of the project is the **BrowserBlockerClient**.
This client has two public properties: **blockTracking** and **filters**. The first is used to enable/disable the url filter, the second is a filter container. Every filter is a simple _Regex_ list matching the URLs to block.
Here a simple configuration:
```
val client = BrowserBlockerClient().apply {
    blockTracking = true
    filters.add(BrowserBlockerClient.Filter().apply {
        urlListToAvoid.addAll(
            listOf(                             
                "https://connect.facebook.net/*".toRegex(),
                "https://www.facebook.com/tr/*".toRegex()
                )
            )
        })
    }
```
