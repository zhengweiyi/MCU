package name.zhengweiyi.mcu;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.gsm.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	

    private Button btnSend;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnSend = (Button) findViewById(R.id.button1);

        btnSend.setOnClickListener(new View.OnClickListener()  {

            @Override
            public void onClick(View v) {
                String strNo = "10086";
                String strContent = "CXYE";
                SmsManager smsManager = SmsManager.getDefault();
                PendingIntent sentIntent = PendingIntent.getBroadcast(MainActivity.this, 0, new Intent(), 0);
                //如果字数超过70,需拆分成多条短信发送
                if (strContent.length() > 70) {
                    List<String> msgs = smsManager.divideMessage(strContent);
                    for (String msg : msgs) {
                        smsManager.sendTextMessage(strNo, null, msg, sentIntent, null);                        
                    }
                } else {
                    smsManager.sendTextMessage(strNo, null, strContent, sentIntent, null);
                }
                Toast.makeText(MainActivity.this, "短信发送完成", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        
        
        return true;
    }
}
