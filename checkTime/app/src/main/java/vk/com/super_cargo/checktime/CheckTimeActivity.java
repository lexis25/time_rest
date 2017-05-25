package vk.com.super_cargo.checktime;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CheckTimeActivity extends Activity {

    private int[]sum = new int[2];
    private int[]divideTime = new int[2];
    private int[]endTimeUser = new int[2];

    private String timeEnter;
    private int numUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_time);
        if(savedInstanceState != null){
            sum = savedInstanceState.getIntArray("sum");
            divideTime = savedInstanceState.getIntArray("divideTime");
            endTimeUser = savedInstanceState.getIntArray("endTimeUser");
            timeEnter = savedInstanceState.getString("timeEnter");
            numUser = savedInstanceState.getInt("numUser");
            setConnectionView();
        }
    }
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putIntArray("sum",sum);
        savedInstanceState.putIntArray("divideTime",divideTime);
        savedInstanceState.putIntArray("endTimeUser",endTimeUser);
        savedInstanceState.putString("timeEnter",timeEnter);
        savedInstanceState.putInt("numUser",numUser);
    }

    public void setConnectionView(){
        EditText time = (EditText) findViewById(R.id.timeEnter);
        EditText user = (EditText) findViewById(R.id.user);

        time.setText(""+timeEnter);
        user.setText(""+String.valueOf(numUser));

        TextView fullTime = (TextView) findViewById(R.id.fullTime);
        TextView timeOneUser = (TextView) findViewById(R.id.timeOneUser);
        TextView endTime = (TextView) findViewById(R.id.endTime);

        fullTime.setText(String.format("%02d:%02d", sum[0], sum[1]));
        timeOneUser.setText(String.format("%02d:%02d",divideTime[0],divideTime[1]));
        endTime.setText(String.format("%02d:%02d",endTimeUser[0],endTimeUser[1]));

    }

    public void checkTimeClick(View view) {

        EditText time = (EditText) findViewById(R.id.timeEnter);
        timeEnter = String.valueOf(time.getText());

        EditText user = (EditText) findViewById(R.id.user);
        numUser = Integer.parseInt(user.getText().toString());

        if (TimeControler.validateTime(timeEnter) & numUser > 0) {

            TextView fullTime = (TextView) findViewById(R.id.fullTime);
            TextView timeOneUser = (TextView) findViewById(R.id.timeOneUser);
            TextView endTime = (TextView) findViewById(R.id.endTime);

            sum = TimeControler.checkSumTime(Integer.parseInt(timeEnter.substring(0, 2)), Integer.parseInt(timeEnter.substring(2)));
            divideTime = TimeControler.divideTimeUser(sum, numUser);
            endTimeUser = TimeControler.endTimeUser(divideTime[0],divideTime[1]);

            fullTime.setText(String.format("%02d:%02d", sum[0], sum[1]));
            timeOneUser.setText(String.format("%02d:%02d",divideTime[0],divideTime[1]));
            endTime.setText(String.format("%02d:%02d",endTimeUser[0],endTimeUser[1]));


        }

    }

}
