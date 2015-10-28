package com.example.chicken.realmtest;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmActivity extends AppCompatActivity{

    private Realm realm;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);

        //TODO
        tv = (TextView)findViewById(R.id.textView_bool);

        Button addButton = (Button) findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //追加処理
                insertExec();
            }
        });
        Button findButton = (Button) findViewById(R.id.button_find);
        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = isFind() ? "Find" : "Not Find";
                //検索処理
                tv.setText(s);
            }
        });
        Button viewButton = (Button) findViewById(R.id.button_view);
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RealmBrowserActivityIntent = new Intent(RealmActivity.this,
                        RealmBrowserActivity.class);
                startActivity(RealmBrowserActivityIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_realm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void insertExec(){

        if(!isFind()) {
            //Realmインスタンス作成
            realm = Realm.getInstance(this);
            //トランザクション開始
            realm.beginTransaction();
            User user = realm.createObject(User.class);
            user.setId(1);
            user.setName("Stan");
            user.setAge(10);
            user.setBloodType("A");

        }
        else{
            //Realmインスタンス作成
            realm = Realm.getInstance(this);
            //トランザクション開始
            realm.beginTransaction();
            User user = realm.createObject(User.class);
            user.setId(2);
            user.setName("Kyle");
            user.setAge(10);
            user.setBloodType("A");

        }
        //コミット
        realm.commitTransaction();
        //クローズ
        realm.close();
    }

    private boolean isFind(){

        //Realmインスタンス作成
        realm = Realm.getInstance(this);
        boolean b;
        RealmQuery<User> query = realm.where(User.class);
        //クエリ発行
        query.equalTo("Name", "Stan");
        //クエリ結果
        RealmResults<User> result = query.findAll();

        return result.size() > 0;
    }

}
