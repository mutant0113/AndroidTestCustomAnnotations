# AndroidTestCustomAnnotations
Run specific Android Espresso tests by creating custom annotations.
We use Kotlin and run tests by command-line via Gradle.

## Run all Tests via Gradle
`./gradlew connectedAndroidTest`

## Run tests with annotation @Rat via Gradle
`./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.mutant.androidtestcustomannotations.annotation.Rat`

## More information
[[Medium article|https://medium.com/@evanfang/run-specific-android-espresso-tests-by-creating-custom-annotations-using-kotlin-and-command-line-6a90e8728e3b]]
