package com.inbm.inbmstarter;

import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.inbm.inbmstarter.inbm.AbsWeb;
import com.inbm.inbmstarter.inbm._log;
import com.inbm.inbmstarter.inbm.recycler.AbsAdapter4Header;
import com.inbm.inbmstarter.inbm.recycler.AbsViewHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    RV4Flower rv4Flower;
    Adapter4Flower adapter4Flower;


    Realm realm;

    RealmResults<Dog> puppies;

    Dog managedDog;



    Button btn_size;

    Button btn_transaction, btn_live_data;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        new GetMountain<List<Mountain>>(new AbsWeb.OnJsonLoadListener() {
            @Override
            public <T> void onJsonLoad(T t) throws IOException, PackageManager.NameNotFoundException {

            }
        }, "");


        MyViewModel vm = ViewModelProviders.of(this).get(MyViewModel.class);

        vm.users.observe(this, users->{
            _log.e(users.size() + "");
        });


        btn_live_data = findViewById(R.id.btn_live_data);

        btn_live_data.setOnClickListener(v->{
            vm.users.add(new User(1, "dt"));
        });




        btn_size = findViewById(R.id.btn_size);
        btn_transaction = findViewById(R.id.btn_transaction);


        Dog dog = new Dog();
        dog.setName("Rex");
        dog.setAge(1);

// Realm을 초기화합니다.
        Realm.init(this);


        realm = Realm.getDefaultInstance();


        puppies = realm.where(Dog.class).lessThan("age", 2).findAll();

        btn_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                _log.e("size:" + puppies.size());







            }
        });

        btn_transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                final Dog managedDog = realm.copyToRealm(dog); // 비관리 객체를 영속화합니다
                Person person = realm.createObject(Person.class, new Random().nextLong()); // 관리 객체를 직접 만듭니다
                person.getDogs().add(managedDog);
                realm.commitTransaction();
            }
        });




// 2살 미만의 모든 개에 대한 Realm 질의합니다






        puppies.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Dog>>() {
            @Override
            public void onChange(RealmResults<Dog> results, OrderedCollectionChangeSet changeSet) {
                // 질의 결과는 세밀한 알림과 함께 실시간으로 갱신됩니다

                 // => Realm에 아직 개가 추가되지 않았기 때문에 0

                _log.e("size:" + puppies.size());
            }
        });






        new GetFlower(new AbsWeb.OnJsonLoadListener() {
            @Override
            public <T> void onJsonLoad(T t) throws IOException, PackageManager.NameNotFoundException {

                rv4Flower = findViewById(R.id.rv_flower);

                adapter4Flower = new Adapter4Flower(MainActivity.this, (ArrayList<Adapter4Flower._data_>) t, new AbsAdapter4Header.OnClicked() {
                    @Override
                    public void onClick(View v, AbsViewHolder holder) {
                        _log.e(v.getTag() + "" +  holder.position);
                    }
                });


                rv4Flower.setAdapter(adapter4Flower );
            }
        }, "");
    }
}
