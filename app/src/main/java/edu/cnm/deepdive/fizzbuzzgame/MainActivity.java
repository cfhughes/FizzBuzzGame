package edu.cnm.deepdive.fizzbuzzgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

  private static final int START_DELAY = 1000;
  private static final int UPDATE_INTERVAL = 3000;
  private static final int UPPER_LIMIT = 100;

  private Random rng;
  private Timer timer;
  private TextView valueDisplay;
  private TextView correctTally;
  private TextView lazyTally;
  private TextView incorrectTally;
  private ToggleButton fizzToggle;
  private ToggleButton buzzToggle;
  private int value;
  private int correct = 0;
  private int incorrect = 0;
  private int lazy = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    correctTally = findViewById(R.id.correct_tally);
    lazyTally = findViewById(R.id.lazy_tally);
    incorrectTally = findViewById(R.id.incorrect_tally);
    valueDisplay = findViewById(R.id.value_display);
    fizzToggle = findViewById(R.id.fizz_toggle);
    buzzToggle = findViewById(R.id.buzz_toggle);
  }

  @Override
  protected void onStart() {
    super.onStart();
    rng = new Random();
    timer = new Timer();
    timer.scheduleAtFixedRate(new UpdateTask(), START_DELAY, UPDATE_INTERVAL);
  }

  @Override
  protected void onStop() {
    timer.cancel();
    timer.purge();
    timer = null;
    super.onStop();
  }

  void refresh() {
    boolean fizz = (value % 3 == 0);
    boolean buzz = (value % 5 == 0);
    if (value != 0) {
      if ((fizz ^ fizzToggle.isChecked()) || (buzz ^ buzzToggle.isChecked())) {
        incorrect++;
      } else if (fizz || buzz) {
        correct++;
      } else {
        lazy++;
      }
    }
    correctTally.setText(getString(R.string.correct_tally_format, correct));
    lazyTally.setText(getString(R.string.lazy_tally_format, lazy));
    incorrectTally.setText(getString(R.string.incorrect_tally_format, incorrect));
    value = 1 + rng.nextInt(UPPER_LIMIT);
    valueDisplay.setText(getString(R.string.value_display_format, value));
    fizzToggle.setChecked(false);
    buzzToggle.setChecked(false);
  }

  private class UpdateTask extends TimerTask {

    @Override
    public void run() {
      runOnUiThread(new Updater());
    }

  }

  private class Updater implements Runnable {

    @Override
    public void run() {
      refresh();
    }

  }

}
