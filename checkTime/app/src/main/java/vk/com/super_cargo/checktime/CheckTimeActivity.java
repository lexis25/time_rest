package vk.com.super_cargo.checktime;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CheckTimeActivity extends Activity {

    private int[] sum = new int[2];
    private int[] divideTime = new int[2];
    private int[] endTimeUser = new int[2];

    private TextView fullTime;
    private TextView timeOneUser;
    private TextView endTime;

    private EditText time;
    private EditText user;

    private String timeEnter;
    private int numUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_time);
        if (savedInstanceState != null) {
            sum = savedInstanceState.getIntArray("sum");
            divideTime = savedInstanceState.getIntArray("divideTime");
            endTimeUser = savedInstanceState.getIntArray("endTimeUser");
            timeEnter = savedInstanceState.getString("timeEnter");
            numUser = savedInstanceState.getInt("numUser");
            setConnectionView();
        }

    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putIntArray("sum", sum);
        savedInstanceState.putIntArray("divideTime", divideTime);
        savedInstanceState.putIntArray("endTimeUser", endTimeUser);
        savedInstanceState.putString("timeEnter", timeEnter);
        savedInstanceState.putInt("numUser", numUser);
    }

    public void setConnectionView() {
        time = (EditText) findViewById(R.id.timeEnter);
        user = (EditText) findViewById(R.id.user);

        time.setText("" + timeEnter);
        user.setText("" + String.valueOf(numUser));

        fullTime = (TextView) findViewById(R.id.fullTime);
        timeOneUser = (TextView) findViewById(R.id.timeOneUser);
        endTime = (TextView) findViewById(R.id.endTime);

        fullTime.setText(String.format("%02d:%02d", sum[0], sum[1]));
        timeOneUser.setText(String.format("%02d:%02d", divideTime[0], divideTime[1]));
        endTime.setText(String.format("%02d:%02d", endTimeUser[0], endTimeUser[1]));
    }

    public void checkTimeClick(View view) {// error illegalStateException

        time = (EditText) findViewById(R.id.timeEnter);
        user = (EditText) findViewById(R.id.user);

        if (time.getText().length() != 0 & user.getText().length() != 0 & time.getText().length() == 4) {

            timeEnter = String.valueOf(time.getText());
            numUser = Integer.parseInt(user.getText().toString());

            if (TimeControler.validateTime(timeEnter)) {

                fullTime = (TextView) findViewById(R.id.fullTime);
                timeOneUser = (TextView) findViewById(R.id.timeOneUser);
                endTime = (TextView) findViewById(R.id.endTime);

                sum = TimeControler.checkSumTime(Integer.parseInt(timeEnter.substring(0, 2)), Integer.parseInt(timeEnter.substring(2)));
                divideTime = TimeControler.divideTimeUser(sum, numUser);
                endTimeUser = TimeControler.endTimeUser(divideTime[0], divideTime[1]);

                fullTime.setText(String.format("%02d:%02d", sum[0], sum[1]));
                timeOneUser.setText(String.format("%02d:%02d", divideTime[0], divideTime[1]));
                endTime.setText(String.format("%02d:%02d", endTimeUser[0], endTimeUser[1]));

            } else {
                Toast.makeText(this, R.string.messageCorrectTime, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.messageNull, Toast.LENGTH_SHORT).show();
            fullTime.setText(R.string.null_time);
            timeOneUser.setText(R.string.null_time);
            endTime.setText(R.string.null_time);
        }
    }

}
