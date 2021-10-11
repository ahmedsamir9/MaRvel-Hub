# Marvel Hub Android App

* sample app (single activity app) that marvel characters Data 
## 🎥 Watch demo on [youtube](https://youtu.be/jPT40HjXRlI)
## 🧐 About
* main purpose for building this project is to achieve Single source of truth(SSOT) principle(which is local Db) , handle all states for every screen and implement clean architecture also it is a challenge

## Features
- [1] Get All Character
- [2] Get Character data (description, comics, stories. series and events )
- [3] Search For specific character
## 📱 Screenshots 
<img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/pics/s1.PNG"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/pics/s2.PNG"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/pics/s7.PNG"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/pics/s3.PNG"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/pics/s4.PNG"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/pics/s5.PNG"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/pics/s6.PNG"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/pics/s8.PNG"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/pics/s9.PNG"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/pics/s10.PNG"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/pics/s11.PNG">

## Requirements
 * android Lollipop or higher

## ⚡ Technologies and libraries
* hilt for di
* kotlin Coroutine and flows for (threading and backgroud)
* Room DataBase (the main source of data)
* recyclerview
* paging 3 (for handling paging)
* workManger (for deleting cached data if there is strong internet connection and no low battery )
* material design
* navigation component
* mvvm architecture
* retrofit (for network calls)
* repository pattern
* Glide (for handling images)
* motionlayout (for animation)
* Timber (for Logging)
* Kotlin Extention function
* ViewBinding
## architecture : 
<img alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/pics/final-architecture.png">

## Navigation graph :
<img alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/pics/navgraph.PNG">


## Resources
- [Motionlayout](https://developer.android.com/training/constraint-layout/motionlayout)
- [Workmanger](https://developer.android.com/topic/libraries/architecture/workmanager)
- [dagger Hilt](https://www.youtube.com/watch?v=nfazwQFQjAM)
- [architecture](https://developer.android.com/jetpack/guide)
- [ssot](https://medium.com/@sina.rahimi/single-source-of-truth-with-mvvm-retrofit2-livedata-rxjava-and-room-in-repository-pattern-f5304f39175)
- [navigation](https://developer.android.com/guide/navigation)
- [paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
