package ru.sabanashvili.classes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Scanner;

public class Main  {
    public static void main(String[] args) throws  Exception {
        //ru.sabanashvili.classes.Human
        Scanner scanner = new Scanner(System.in);
        //считываем название требуемого класса
        String className = scanner.next();
        // загружаем этот класс
        Class aClass = Class.forName(className);
        // достаем у него все поля
        Field[] fields = aClass.getFields();
        for(Field field: fields){
            System.out.println(field.getType());
        }
        //создаем объект класса через рефлексию
        //Object object = aClass.newInstance();
        //System.out.println(object);
        //а что если у нас нет пустого конструктора? тогда метод newInstance() не отработает
        //но у нас есть класс Constructor - через него мы можем запросить все существующие конструкторы у класса
        // мы запрашиваем все типы полей из класса, записываем их в массив
        Class [] types = new Class[fields.length];
        for (int i = 0; i < types.length; i++) {
            types[i] = fields[i].getType();
        }
        //дальше говорим методу getDeclaredConstructor() что вот, у нас есть конструктор с такими полями, дай нам информацию о нем
        Constructor constructor = aClass.getDeclaredConstructor(types);
        for(Class parameterType: constructor.getParameterTypes()){
            System.out.println(parameterType.getName());
        }
        //в принципе, в теории, комбинируя разные типы полей из массива types, мы можем узнать какие типы конструкторов у нас еще есть
        //идем дальше. Создадим объект через конструктор с параметрами при помощи рефлексии
        // значения полей для конструктора возьмем из потока ввода(просто для примера)
        Integer intValue = 0;
        String stringValue = "";
        for (int i = 0; i < fields.length; i++) {
            if(types[i].getName().equals("int")) {
                intValue = scanner.nextInt();
            }else if(types[i].getName().equals("java.lang.String")){
                stringValue = scanner.next();
            }
        }
        // из потока ввода выцепили, теперь соберем поля в массив, затем передадим в конструктор
        Object[] argumants = {intValue, stringValue};
        Object object = constructor.newInstance(argumants);
        // ну и выведем что получилось
        System.out.println(object);

        // то что получилось в итоге - это такой минифрэймворк. корявый, косой, хромой..но это лишь пример
        // фрэймворки изначально ничего не знают о наших классах
        // но узнают через рефлексию

    }
}
