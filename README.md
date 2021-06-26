# Dignicate.zero1-Android

## Abstract
The _Dignicate.zero1_ is an example project for native-Android development, created by _[Dignicate, LLC](https://dignicate.com)_ to prescribe in-house standard.
It also is to share our experiences in the form of case studies based on situations we encountered so far.
Please not that this project is created after the [iOS counterpart](https://github.com/dignicate/Dignicate.zero1-iOS) and we make the Android project as resemble it on the whole structure as possible.

## Focus
The project focuses mainly on asynchronous data flow over layered structure such as _Model-View-ViewModel_ and so-called _Clean Architecture_.

## Out-scope
We do not mention:
* Any of network interaction, detailed usage of libraries like Retrofit. Infrastructure such as fetching external data and sending them out of boundary, are all implemented by mock objects.
* User interface and design concerns.
* We do not use libraries such as Dagger, Room and Retrofit which are popular and officially encouraged. In terms especially of Dagger, we emphasize 'what is dependency injection (DI)' instead of 'how to use the DI containers'.
* Detailed explanation of fundamental knowledge such as Kotlin / Java, Object orientation, MVVM and Dependency injection.

## Getting Started
* Just clone this project.
```
git clone https://github.com/dignicate/Dignicate.zero1-iOS.git
```

## Screen Shot

<img src="https://user-images.githubusercontent.com/57293323/123500608-537d3200-d67a-11eb-8050-bd0303c03f26.png" width="320"/>

