# AndroidTestCustomAnnotations
Run specific Android Espresso tests by creating custom annotations.
We use Kotlin and run tests by command-line via Gradle.

# Run all Tests via Gradle
`./gradlew connectedAndroidTest`

# Run tests with annotation @Rat via Gradle
`./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.annotation=com.mutant.androidtestcustomannotations.annotation.Rat`

# More information
