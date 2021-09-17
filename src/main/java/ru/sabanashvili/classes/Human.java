package ru.sabanashvili.classes;

public class Human {
    public int age;
    public String name;
//    Human(){
//        this.age = 1;
//        this.name = "DEFAULT_NAME";
//    }

    public Human(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.age + " " + this.name;
    }
}
