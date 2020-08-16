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
2- ![Android-Architeture-Component](https://drive.google.com/file/d/1uPCac7XxlLTv_UkHlqXmR5E6AGhZIz5d/view?usp=sharing)

Inrastruture -> Domain -> Presentation


# Infrastructure ->

Currently:
1-DTO (transfer from Backend Modle into our Entity Domain Model) doesn't contain any logic
2-Implementation of Repository Patterns (Remote Repo (MoviesRemoteRepository) - Cash Repo (MoviesLocalRepository) )  & handle communication between 2 Repos (MovieProxyRepository) 

Later:
1- add mapper layer between (Entity Domain Model to Prisist DB Model)
2- add relational DB 
3- external web services for Box-Office Movies


# Domain ->

Currently:

A- Models 
1- Entity and Value Objects  (Movies, Trailer - Reviews - Casts) and we need to add a relation between them.
2- Abstraction Repos Agreed with interface segregation  Solid Principle.

B-Service 
1- According to DDD, we refer to domain services that orchestrate between entity models and doing some logic independent for UI Layer.(DetailMovieService)
Note:
Domain Service allows you to capture logic that doesn't belong in the Domain Entity.
Domain Service allows you to orchestrate between different Domain Entities.
Tips:
Don't create too many Domain Services, most of the logic should reside in the domain entities, event handlers, etc. 

2-all logic doesn't exist only in domain services but we need balance with application services

Later: 
1- add more domain services.
2- add a relation between Entity and VO.



# Prsentation -> 
