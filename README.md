# NewMovieApp implemented DDD & Android Architecture Component..
New Movie App with Latest Anroid Navigation Component.
Library, Frameworks and Design patterns:

# Tools & Library
Mvvm data binding - Live Data- Navigation graph - Room ORM - Android Paging Library -
Retrofit - Kotlin Flow - Corotuines - Koin Di - Mockito - parameterized unit testing - Espresso
Read more text view library.

# Steps To Run 

1- Open https://www.themoviedb.org/documentation/api and create a Key
2- Open Gradle Scripts -> Local.Properties -> tmdb_api_key= INSERT_KEY_HERE
3- Run The Project


# Architeture-Design

1- According to Domain-Deiven-Design http://www.zankavtaskin.com/2014/12/applied-domain-driven-design-ddd-part-0.html
2- ![Android-Architeture-Component](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

Inrastruture -> Domain -> Presentation


# Infrastructure ->

## Currently:
1-DTO (transfer from Backend Modle into our Entity Domain Model) doesn't contain any logic
2-Implementation of Repository Patterns (Remote Repo (MoviesRemoteRepository) - Cash Repo (MoviesLocalRepository) )  & handle communication between 2 Repos (MovieProxyRepository) 

## Later:
1- add mapper layer between (Entity Domain Model to Prisist DB Model)
2- add relational DB 
3- external web services for Box-Office Movies
4- validation factory for Infrastructure exception like https://github.com/YassinELhadedy/Build-Android-App-Sample1-CompleteCycle/blob/master/app/src/main/java/com/transporter/streetglide/infrastructure/InfrastructureException.kt . 


# Domain ->

## Currently:

##### A- Models 
1- Entity and Value Objects  (Movies, Trailer - Reviews - Casts) and we need to add a relation between them.
2- Abstraction Repos Agreed with interface segregation  Solid Principle.

##### B-Service 
1- According to DDD, we refer to domain services that orchestrate between entity models and doing some logic independent for UI Layer.(DetailMovieService)
Note:
Domain Service allows you to capture logic that doesn't belong in the Domain Entity.
Domain Service allows you to orchestrate between different Domain Entities.
Tips:
Don't create too many Domain Services, most of the logic should reside in the domain entities, event handlers, etc. 

2-all logic doesn't exist only in domain services but we need balance with application services

## Later: 
1- add more domain services.
2- add a relation between Entity and VO.
3- validation factory for Domain exception.



# Prsentation -> 

## Currently:


##### A- Fragment

1-PagingMovieListFragment (show list of movies with supporting paging library and kotlin flow).
2-PopularMovieListFragment (show list of movies without supporting paging library but support Live Data).
3-MovieDetailFragment (show detail of movie and trailers and reviews and casts).


##### B- View Models + Data Bindng (depend only Infrastructure (Abstraction Repo) or Domain Service) Apply DIP Solid Principle.


##### C- Dependency injection (Koin Modules)


##### D- Adapter 

1-(reycler view with Data binding)
2-(PagingDataAdapter to handle paingData  Paging Library)



## Later:


1- validation factory that map error and exceptions to Business Messages.
2- Utils for Network State and permission
3- Base Adapter with Data binding
4- Base Fragment to reduce setup code for each fragment and hanle life cycle and navigation.


# Testing 

## Currently


1- Unit test using Mockito (Repo in infrastructure and View Models)
2- UI test and integration Test using Espresso  (PopularMovieListFragment)


## Later

1- add more unit test cases for error factory module.
2- add parameterized Unit test like https://github.com/YassinELhadedy/Build-Android-App-Sample1-CompleteCycle/blob/master/app/src/androidTest/java/com/transporter/streetglide/infrastructure/GetAllRepositoryTest.kt.


3- add E2E Test For full Scenarios using Cucumber and Esspresso. like https://github.com/YassinELhadedy/Build-Android-App-Sample1-CompleteCycle/tree/master/app/src/androidTest/assets/features

![Android-Test-Praymid](https://cdn-images-1.medium.com/max/1563/1*6M7_pT_2HJR-o-AXgkHU0g.jpeg)
