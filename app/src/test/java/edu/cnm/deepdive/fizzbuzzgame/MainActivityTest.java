package edu.cnm.deepdive.fizzbuzzgame;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

import android.widget.TextView;
import android.widget.ToggleButton;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

  private TextView valueDisplay;
  private TextView correctTally;
  private TextView lazyTally;
  private TextView incorrectTally;
  private ToggleButton fizzToggle;
  private ToggleButton buzzToggle;
  private MainActivity activity;

  @Before
  public void setUp() {
    activity = Robolectric.setupActivity(MainActivity.class);
    correctTally = activity.findViewById(R.id.correct_tally);
    lazyTally = activity.findViewById(R.id.lazy_tally);
    incorrectTally = activity.findViewById(R.id.incorrect_tally);
    valueDisplay = activity.findViewById(R.id.value_display);
    fizzToggle = activity.findViewById(R.id.fizz_toggle);
    buzzToggle = activity.findViewById(R.id.buzz_toggle);
  }

  @Test
  public void testValueDisplay() {
    activity.refresh();
    assertTrue(valueDisplay.getText().toString().length() > 0);
  }

  @Test
  public void testFizzBuzz() {
    activity.refresh();
    for (int i = 0; i < 15; i++) {
      int value = Integer.parseInt(valueDisplay.getText().toString());
      System.out.println(value);
      if (value != 0) {
        fizzToggle.setChecked(value % 3 != 0);
        buzzToggle.setChecked(value % 5 != 0);
      }
      activity.refresh();
      assertEquals((i + 1) + " incorrect", incorrectTally.getText().toString());
    }
  }


}
