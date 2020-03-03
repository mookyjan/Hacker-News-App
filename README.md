# Hacker-News-App

### Structure of the code ###
Simple Android Application written in Kotlin.
This project follows Clean Architecture with MVVM with Clean Architecture Design


# Main libraries used

* Data Binding
* Dagger2
* RxJava2
* Retrofit2
* Timber
* Room
* Navigation Component
* Groupie RecyclerView Library
* Android Architecture component Jetpack


# Modules


* `data/` : contains the code to access to the data (repository pattern)
* `domain/` : contains the business logic and the usecases
* `app` : Presentation layer, contains the UI 

this project consist of three screens. on the first screen showing the list of news and on clicking the go
 to the Detail screen where there are two screen one is for comments and the other one is for 
 Articles screen which shows url of the news in App.
 In Comment section by default the first comment is shown and by clicking on the number of comments will open
 the replies for that comment.

and for the simplicity of this project many things have been kept simple
like 
* ErrorHandling, 
* Internet connectivity and 
* local db to store data locally (Room). basic structure is added but due to time shortage did not completed it
* Design of the app is also kept sample and can be improved much more

comments are written with the function that what it will do.

also TODO are given in the area which we can improve more.


##New changes done
 fix issue of loading the comments when coming back to details screen
add shimmer effect at start of the loading screen
add unit test cases 
add design for the App architecture
every time load the news list when comeback to the newslist screen or refresh
improve design of comments list to show a proper link between child comments




