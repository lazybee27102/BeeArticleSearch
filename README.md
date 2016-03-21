# Project 2 - *BeeArticleSearchAndRead*

**BeeArticleSearch** is an android app that allows a user to search for images on web using simple filters and read the top nes of new york times. The app utilizes [New York Times Search API](http://developer.nytimes.com/docs/read/article_search_api_v2).

Time spent: **40** hours spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can **search for news article** by specifying a query and launching a search. Search displays a grid of image results from the New York Times Search API.
* [X] User can click on "settings" which allows selection of **advanced search options** to filter results.(Filter Function)
* [X] User can configure advanced search filters such as:
  * [X] Choose Date(All time,this week,this month,this year)
  * [X] News desk values (Arts, Fashion & Style, Sports,Education,Business,.....)
  * [X] Sort order (oldest or newest)
* [X] Subsequent searches have any filters applied to the search results
* [X] User can tap on any image in results to see the full text of article **full-screen**
* [X] User can **scroll down to see more articles**. The maximum number of articles is limited by the API search.

The following **optional** features are implemented:

* [ ] Implements robust error handling, [check if internet is available](http://guides.codepath.com/android/Sending-and-Managing-Network-Requests#checking-for-network-connectivity), handle error cases, network failures
* [X] Used the **ActionBar SearchView** or custom layout as the query box instead of an EditText
* [X] User can **share an article link** to their friends or email it to themselves
* [X] Replaced Filter Settings Activity with a lightweight modal overlay
* [X] Improved the user interface and experiment with image assets and/or styling and coloring

The following **bonus** features are implemented:

* [X] Use the [RecyclerView](http://guides.codepath.com/android/Using-the-RecyclerView) with the `StaggeredGridLayoutManager` to display improve the grid of image results
* [X] For different news articles that only have text or only have images, use [Heterogenous Layouts](http://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView) with RecyclerView
* [ ] Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce view boilerplate.
* [ ] Use Parcelable instead of Serializable using the popular [Parceler library](http://guides.codepath.com/android/Using-Parceler).(I don't need to use Parcelable in this App)
* [ ] Leverage the popular [GSON library](http://guides.codepath.com/android/Using-Android-Async-Http-Client#decoding-with-gson-library) to streamline the parsing of JSON data.
* [X] Replace Picasso with [Glide](http://inthecheesefactory.com/blog/get-to-know-glide-recommended-by-google/en) for more efficient image rendering.

The following **additional** features are implemented:

* [X] User can read the top news and search results from many sections:home,world,politics,art,fashion,science,techonology,....
* [X] Create a Main Stream UI with CoordinatorLayout,AppBar,CollapsingToolbar,Toolbar,TabView,ViewPager,RecyclerView with 2 item types,FloatingButton)..
* [X] Create a dialog interface to choose multiple news_desk sections
* [X] Article design by new MaterialDesign Support Library,font ROBOTO,Color
* [X] All AsyncHttpClient is pakaged in class
and so on...

## Video Walkthrough

Here's a walkthrough of implemented user stories:
- [Giphy] <img src='http://gph.is/1ZiOoWa' title='Video Walkthrough' width='' alt='Video Walkthrough' />
- [Youtube](https://youtu.be/ynOC0edITtI)


GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

- I don't know the search algorithm of NYT API,so I can get the expected result 
- Peformance is not higt because I always set image by fetching url

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android

## License

    Copyright [1995] [Nguyen Hoang Phat]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
