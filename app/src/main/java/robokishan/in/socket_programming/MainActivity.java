package robokishan.in.socket_programming;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {



    private EditText editText_ip, editText_port, editText_mes;
    private String ipAddress;
    private int port = 0;
    private Socket client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_ip = (EditText) findViewById(R.id.ipaddress);
        editText_port= (EditText) findViewById(R.id.port);
        editText_mes = (EditText) findViewById(R.id.message);


        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ipAddress = editText_ip.getText().toString();
                port = Integer.valueOf(editText_port.getText().toString());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            client = new Socket(ipAddress, port);
                            PrintWriter printWriter = new PrintWriter(client.getOutputStream());
                            printWriter.write("This is test message");
                            printWriter.flush();
                            printWriter.close();
                            client.close();
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();


            }
        });
    }
}
