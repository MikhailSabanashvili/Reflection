package ru.sabanashvili.demo;

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws Exception {
        SomeClass someObject = new SomeClass();
        Class<SomeClass> someClassAsClass = (Class<SomeClass>) someObject.getClass();
        //Если я хочу узнать инфу о полях данного класса(SomeClass)
        Field someField = someClassAsClass.getField("someField");
        System.out.println(someField.getType());
        // инфа о всех полях(но не приватных)
        Field[] fields = someClassAsClass.getFields();
        for(Field field: fields){
            System.out.println(field.getType() + " " + field.getName());
        }
        // можно также через рефлексию установить значение поля класса
        someField.set(someObject, 777);
        System.out.println(someObject.someField);
        // теперь аналогичная работа, но с приватными полями
        Field privateField = someClassAsClass.getDeclaredField("somePrivate");
        System.out.println(privateField.getName());
        //если хотим вывести абсолютно все поля, включая приватные - метод getDeclaredFields
        privateField.setAccessible(true);
        privateField.set(someObject, "Marsel");
        System.out.println(someObject.getSomePrivate());

    }
}
