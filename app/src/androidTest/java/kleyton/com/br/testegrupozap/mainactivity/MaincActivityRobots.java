package kleyton.com.br.testegrupozap.mainactivity;

import kleyton.com.br.testegrupozap.R;
import propertylist.view.PropertyListActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MaincActivityRobots {

    public MaincActivityRobots checkLayout() {
        onView(withText(R.string.select)).check(matches(isDisplayed()));
        onView(withId(R.id.zap_button)).check(matches(isDisplayed()));
        onView(withId(R.id.vivareal_button)).check(matches(isDisplayed()));

        return this;
    }

    public MaincActivityRobots clickButtonZap() {
        onView(withId(R.id.zap_button)).perform(click());
        return this;
    }

    public MaincActivityRobots checkIntentButtonZap() {
        intended(hasComponent(PropertyListActivity.class.getName()));
        return this;
    }

    public MaincActivityRobots clickButtonVivaReal() {
        onView(withId(R.id.vivareal_button)).perform(click());
        return this;
    }

    public MaincActivityRobots checkIntentButtonVivaReal() {
        intended(hasComponent(PropertyListActivity.class.getName()));
        return this;
    }
}
