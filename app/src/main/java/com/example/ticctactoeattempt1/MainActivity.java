package com.example.ticctactoeattempt1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn;
    TextView tv1, tv2;
    boolean player1turn = true;
    int i, j,a=0,b=0;
    int roundcount = 0;
    Button[][] btns = new Button[3][3];
    String[][] fields = new String[3][3];
    boolean win=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv1 = findViewById(R.id.p1);
        tv2 = findViewById(R.id.p2);
        btn = findViewById(R.id.rst);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (i = 0; i < 3; i++) {
                    for (j = 0; j < 3; j++) {
                        btns[i][j].setText("");
                        fields[i][j]= btns[i][j].getText().toString();
                        roundcount=0;
                        win=false;

                    }
                }

            }
        });

        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                String btn_id = "btn" + i + j;
                int res_id = getResources().getIdentifier(btn_id, "id", getPackageName());
                btns[i][j] = findViewById(res_id);
                btns[i][j].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if(win){
            return;
        }
        if (player1turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }
        roundcount++;

        if (checkForWin()) {
            win=true;
            if(player1turn){
                Toast.makeText(this,"Player 1 Wins", Toast.LENGTH_SHORT).show();
                a++;
                tv1.setText("Player 1:" + a);
            }
            else{
                Toast.makeText(this,"Player 2 Wins", Toast.LENGTH_SHORT).show();
                b++;
                tv2.setText("Player 2:" + b);
            }

        }
        else if(roundcount == 9){
            Toast.makeText(this,"Draw", Toast.LENGTH_SHORT).show();
        }
        else{
            player1turn= !player1turn;
        }
    }

    private boolean checkForWin() {
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                fields[i][j]= btns[i][j].getText().toString();

            }
        }
        for(i=0;i<3;i++){
            if(fields[0][i].equals(fields[1][i]) && fields[0][i].equals(fields[2][i]) && !fields[0][i].equals("")){
                return true;
            }
        }
        for(i=0;i<3;i++){
            if(fields[i][0].equals(fields[i][1]) && fields[i][0].equals(fields[i][2]) && !fields[i][0].equals("")){
                return true;
            }

        }
        if(fields[0][0].equals(fields[1][1]) && fields[0][0].equals(fields[2][2]) && !fields[0][0].equals("")){
            return true;
        }
        if(fields[0][2].equals(fields[1][1]) && fields[0][2].equals(fields[2][0]) && !fields[0][2].equals("")){
            return true;
        }
        return false;

    }
}
