I have designed the app using MVVM (Model-View-ViewModel) architecture pattern with a repository layer and Dagger Hilt for dependency injection.

**Model(data):** Represents the data and business logic of the application.

**View(UI):** Handles the UI elements and user interactions.

**ViewModel:** Acts as a mediator between the Model and the View, exposing data and commands to the View and handling the logic and state management.

**Repository(data-repository):** Serves as an abstraction layer between the ViewModel and the data sources (e.g., local database, network). It manages data retrieval, caching, and storage.

**Dagger Hilt(di):** Provides a dependency injection framework that simplifies managing dependencies in the app.  It helps with writing modular, testable, and scalable code by decoupling the creation and injection of dependencies.

This architecture promotes separation of concerns, testability, and code reusability.  and It allows for modular development, easier maintenance, efficient data management, and dependency injection in the app.



_**Gradle Information**_

I have used the following libraries
**Glide:** Necessary for efficient image loading and caching in your application.
Simplifies the process of working with images by handling image resizing, memory and disk caching, and image request management.

**Dagger Hilt:** Provides a dependency injection framework that simplifies managing dependencies in your application.
Helps with writing modular and testable code by decoupling the creation and injection of dependencies.

**Retrofit:** Essential for making HTTP requests and interacting with web services in your application.
Offers a high-level REST client with easy-to-use APIs for handling network requests, serialization/deserialization of JSON, and error handling.

**Kotlin Coroutines:** Enables asynchronous programming in Kotlin by providing lightweight threads (coroutines) 
that can be used to perform non-blocking operations.
Simplifies writing asynchronous code and makes it easier to handle long-running tasks without blocking the main thread.

**Lifecycle:** Offers lifecycle-aware components and utilities for managing the lifecycle of 
Android application components (such as activities and fragments).
Facilitates handling configuration changes, avoiding memory leaks, and managing the lifecycle of UI-related operations.

**Kotlin Bom:** Bill of Materials (BoM) for Kotlin that ensures all Kotlin-related dependencies in your project are compatible with each other.
Simplifies dependency management by providing a single version of Kotlin for all related libraries.




_**MutableStateFlow**_

Additionally I have used MutableStateFlow that provides a way to emit values that can be access anywhere on any screen and along with addional benefits, here are top and main benefits.

Reactive programming: MutableStateFlow enables reactive programming by providing a simple and concise way to observe and react to state changes.

LiveData replacement: MutableStateFlow can be used as a replacement for LiveData in Android development,
offering similar functionality with a more modern and flexible API.

Scoped coroutines integration: It seamlessly integrates with Kotlin coroutines, 
allowing you to work with state updates in a structured and coroutine-friendly manner.

Reduced boilerplate: MutableStateFlow reduces the boilerplate code needed to manage state changes 
compared to traditional event listeners or callbacks, resulting in cleaner and more maintainable code.

Type-safe: MutableStateFlow is type-safe, allowing you to define the type of the state explicitly,which helps catch type-related errors at compile-time.
