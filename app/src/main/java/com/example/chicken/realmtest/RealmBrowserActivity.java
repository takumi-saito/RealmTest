package com.example.chicken.realmtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dd.realmbrowser.RealmBrowser;
import com.dd.realmbrowser.RealmFilesActivity;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RealmBrowserActivity extends AppCompatActivity {
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_browser);

        //Realmインスタンス作成
        realm = Realm.getInstance(this);

        if(isFind()) {
            // Realmデータ閲覧用のActivityを起動
            RealmBrowser.getInstance().addRealmModel(User.class);
            RealmFilesActivity.start(this);
        } else {

        }
        realm.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_realm_browser, menu);
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
