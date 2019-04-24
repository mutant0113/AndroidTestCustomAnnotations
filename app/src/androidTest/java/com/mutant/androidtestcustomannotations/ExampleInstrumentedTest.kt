package com.mutant.androidtestcustomannotations

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.hasErrorText
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import com.mutant.androidtestcustomannotations.annotation.Rat
import com.mutant.androidtestcustomannotations.annotation.UITestLongTime
import org.hamcrest.CoreMatchers.not
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    lateinit var context: Context

    @get:Rule
    var runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
            android.Manifest.permission.GET_ACCOUNTS,
            android.Manifest.permission.READ_CONTACTS)

    @get:Rule
    var activityRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

    @Before
    fun setup() {
        // Context of the app under test.
        context = InstrumentationRegistry.getTargetContext()
    }

    @Test @Rat
    fun runRat() {
        assertEquals("com.mutant.androidtestcustomannotations", context.packageName)
    }

    @Test @UITestLongTime
    fun runLongTimeTest() {
        assertEquals("com.mutant.androidtestcustomannotations", context.packageName)
    }

    @Test
    fun invalidEmail_showError() {
        onView(withId(R.id.email)).check(matches(isDisplayed())).perform(replaceText("123456"))
        onView(withId(R.id.email_sign_in_button)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.email)).check(matches(hasErrorText(context.getString(R.string.error_invalid_email))))
    }

    @Test
    fun incorrectPassword_showError() {
        onView(withId(R.id.email)).check(matches(isDisplayed())).perform(replaceText("foo@example.com"))
        onView(withId(R.id.password)).check(matches(isDisplayed())).perform(replaceText("123456"))
        onView(withId(R.id.email_sign_in_button)).check(matches(isDisplayed())).perform(click())
        onView(withId(R.id.password)).check(matches(hasErrorText(context.getString(R.string.error_incorrect_password))))
    }

    @Test @Rat
    fun validEmailAndPassword_login_successfully() {
        onView(withId(R.id.email)).check(matches(isDisplayed())).perform(replaceText("foo@example.com"))
        onView(withId(R.id.password)).check(matches(isDisplayed())).perform(replaceText("hello"))
        onView(withId(R.id.email_sign_in_button)).check(matches(isDisplayed())).perform(click())
        onView(withText(R.string.login_successfully)).inRoot(withDecorView(not(activityRule.activity.window.decorView)))
                .check(matches((isDisplayed())))
    }
}
