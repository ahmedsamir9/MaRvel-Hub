# Marvel Hub Android App

* sample app (single activity app) that marvel characters Data 
## 🎥 Watch demo on [youtube](https://youtu.be/yh9xiSPLA14)
## 🧐 About
* main purpose for building this project is to achieve Single source of truth(SSOT) principle(which is local Db) , handle all states for every screen and implement clean architecture also it is a challenge

## Features
- [1] Get All Character
- [2] Get Character data (description, comics, stories. series and events )
- [3] Search For specific character
## 📱 Screenshots 
<img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/ui/p1.jpeg"> <img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/ui/p3.jpeg"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/ui/p2.jpeg"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/ui/p4.jpeg"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/ui/p5.jpeg"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/ui/p6.jpeg"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/ui/p7.jpeg"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/ui/p8.jpeg"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/ui/p9.jpeg"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/ui/p10.jpeg"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/ui/p11.jpeg"><img width="150" alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/ui/p12.jpeg">

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
<img alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/ui/arch.jpg">

## Navigation graph :
<img alt="Screen Shot 2020-09-04 at 2 48 55 PM" src="/ui/nav.PNG">


## Resources
- [Motionlayout](https://developer.android.com/training/constraint-layout/motionlayout)
- [Workmanger](https://developer.android.com/topic/libraries/architecture/workmanager)
- [dagger Hilt](https://www.youtube.com/watch?v=nfazwQFQjAM)
- [architecture](https://developer.android.com/jetpack/guide)
- [ssot](https://medium.com/@sina.rahimi/single-source-of-truth-with-mvvm-retrofit2-livedata-rxjava-and-room-in-repository-pattern-f5304f39175)
- [navigation](https://developer.android.com/guide/navigation)
- [paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
