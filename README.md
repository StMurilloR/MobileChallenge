
# 📲 MercadoLibre Mobile Challenge

This project is a technical test made for MercadoLibre. It is an app where the user can perform searches, as described below:

* Perform searches of categories domains, where you will be able to get a list of categories available for search.
* If you select any category, you will be able to see the detail of that category, and these specific fields:
  
    * Category Name
    * Total items
    * Path from root
    * Buying modes
    * Item conditions
    * Minimum price
    * Maximum price
    * Shipping Options


## Technologies Used

* Dagger Hilt: Simplifies dependency injection, improving code maintainability and testability.
* Navigation Component fragment: Navigation refers to the interactions that allow users to navigate through, within and outside the different pieces of content in your app.
* Retrofit: is the class through which your API interfaces are turned into callable objects. By default, Retrofit will give you sane defaults for your platform but it allows for customization.
* Kotlin: Modern language for Android development, offering a cleaner syntax and better null safety.

      agp = "8.6.0"
      kotlin = "1.9.0"
      coreKtx = "1.15.0"
      junit = "4.13.2"
      junitVersion = "1.2.1"
      espressoCore = "3.6.1"
      appcompat = "1.7.0"
      material = "1.12.0"
      activity = "1.10.0"
      constraintlayout = "2.2.1"
      navigationFragmentKtx = "2.8.9"
      navigationUiKtx = "2.8.9"
      retrofit = "2.9.0"
      converterGson = "2.5.0"
      lifecycleRuntimeKtx = "2.8.7"
      daggerHilt = "2.51.1"
      kotlinxSerializationJson = "1.6.3"
      glide = "4.16.0"
      mockk = "1.13.8"
      mockito = "4.5.1"
      mockitoKotlin = "4.1.0"
      coroutinesTest = "1.6.4"
      coreTesting = "2.1.0"
      
      [libraries]
      androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
      junit = { group = "junit", name = "junit", version.ref = "junit" }
      androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
      androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
      androidx-appcompat = { group = "androidx.appcompat", name = "appcompat", version.ref = "appcompat" }
      material = { group = "com.google.android.material", name = "material", version.ref = "material" }
      androidx-activity = { group = "androidx.activity", name = "activity", version.ref = "activity" }
      androidx-constraintlayout = { group = "androidx.constraintlayout", name = "constraintlayout", version.ref = "constraintlayout" }
      androidx-navigation-fragment-ktx = { group = "androidx.navigation", name = "navigation-fragment-ktx", version.ref = "navigationFragmentKtx" }
      androidx-navigation-ui-ktx = { group = "androidx.navigation", name = "navigation-ui-ktx", version.ref = "navigationUiKtx" }
      retrofit = { module = "com.squareup.retrofit2:retrofit", version.ref = "retrofit" }
      kotlinx-serialization-json = {group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json", version.ref = "kotlinxSerializationJson"}
      kotlin-serialization = { module = "org.jetbrains.kotlin:kotlin-serialization", version.ref = "kotlin" }
      converter-gson = { module = "com.squareup.retrofit2:converter-gson", version.ref = "converterGson" }
      androidx-lifecycle-livedata-ktx = { module = "androidx.lifecycle:lifecycle-livedata-ktx", version.ref = "lifecycleRuntimeKtx" }
      androidx-lifecycle-viewmodel-ktx = { module = "androidx.lifecycle:lifecycle-viewmodel-ktx", version.ref = "lifecycleRuntimeKtx" }
      dagger-hilt-android = { module = "com.google.dagger:hilt-android", version.ref = "daggerHilt"}
      dagger-hilt-android-compiler = { module = "com.google.dagger:hilt-android-compiler", version.ref = "daggerHilt"}
      glide = { module = "com.github.bumptech.glide:glide", version.ref = "glide" }
      glide-compiler = { module = "com.github.bumptech.glide:compiler", version.ref = "glide" }
      mockk = { module = "io.mockk:mockk", version.ref = "mockk" }
      mockito-core = { module = "org.mockito:mockito-core", version.ref = "mockito" }
      mockito-kotlin = { module = "org.mockito.kotlin:mockito-kotlin", version.ref = "mockitoKotlin" }
      kotlinx-coroutines-test = { module = "org.jetbrains.kotlinx:kotlinx-coroutines-test", version.ref = "coroutinesTest" }
      androidx-core-testing = { module = "androidx.arch.core:core-testing", version.ref = "coreTesting" }

## Installation & Setup

To run the app locally, follow these steps:

Clone the repository:

        git clone https://github.com/your-repository.git

Open the project in Android Studio:

* Make sure you have Android Studio installed (preferably the latest stable version).
* Open Android Studio and select "Open an existing project," then navigate to the project folder.

Sync Gradle files:

* Once the project is opened, Android Studio will automatically sync the Gradle files. Ensure that all dependencies are downloaded.

Run the app:

* Select a device (either a physical device or emulator) and click "Run" to launch the app.

Required SDK versions:

* Minimum SDK version: 24 (or as per the app’s requirements).
* Target SDK version: 35 (or the latest stable SDK version).


## Authors

- [@StMurilloR](https://github.com/StMurilloR)
