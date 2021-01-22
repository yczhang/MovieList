# CheckList
--- project ---

 Navigate to https://api.jikan.moe/v3/search/anime?q=naruto. 
Yichun-> Use Base URL as https://api.jikan.moe/v3/search/anime, use can type in keyword to search different contents

 This is the endpoint, you&#39;d like consume the API, and display the content in the
native app.
Yichun-> Display a portion of content, title, icon, start/end, type, score, and rated, but all fields are in data class

 Plus point: write Unit Tests
Yichun-> Due to the time limitation, only test database and viewmodel

 Plus point: add a page to search by anime characters such as ‘naruto’, ‘kakegurui’,
Yichun->Search Feature is supportted on the top of the page, you will see a search view, default is naruto,  type in others, and click go  will download new contents.
‘goku’, ‘susuke’ etc. (note: you can simply replace query parameter on your search
url)

What are we looking for in an Android project?
 You should be comfortable with Kotlin. 
Yichun-> Fully Kotlin
 You should be comfortable with Android Architecture Components like LiveData,
Room and ViewModel as well as open-source libraries like Retrofit, Picasso, and
OkHttp to build modern, architecturally compliant, and future-ready apps.
Yichun-> MVVM  use livedata, room, viewmodel, retrofiel, picasso, and okhttp. Due to the timing, only viewmodel for mainview, no view model for item in recyclerview.

 Show an understanding of multithreading and memory management in general:
Yichun->use coroutine
 Show an understanding of working with network calls, in particular RESTful API’s
using JSON
Yichun-> fully support
 Show good coding style and comments
 Show an understanding of the MVVM design pattern



Tested on Simulator an Pixel 2 XL
 Show an understanding of object-oriented design patterns

Do your best to make sure the code is error free and compiles.
Also, include a short summary (note) about your approach, and reasons for using that
approach, tools, 3 rd party libraries etc.
