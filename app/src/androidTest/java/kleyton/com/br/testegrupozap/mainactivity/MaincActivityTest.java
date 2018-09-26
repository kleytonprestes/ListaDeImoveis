package kleyton.com.br.testegrupozap.mainactivity;

import android.content.Intent;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import mainactivity.MainActivity;

@RunWith(AndroidJUnit4.class)
public class MaincActivityTest {

    MaincActivityRobots robots = new MaincActivityRobots();

    @Rule
    public IntentsTestRule<MainActivity> activityRule = new IntentsTestRule<>(MainActivity.class,
            true, false);

    @Test
    public void testLayout() {
        launcActivity();
        robots.checkLayout();
    }

    @Test
    public void checkClickBtnZap() {
        launcActivity();
        robots.clickButtonZap()
                .checkIntentButtonZap();
    }

    @Test
    public void checkClickBtnVivaReal() {
        launcActivity();
        robots.clickButtonVivaReal()
                .checkIntentButtonVivaReal();
    }

    private void launcActivity() {
        activityRule.launchActivity(new Intent());
    }

}
