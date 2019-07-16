package com.inbm.inbmstarter;

import io.realm.RealmObject;

public class Dog extends RealmObject {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    private String name;
    private int age;

    // ... 생성된 getter와 setter
}