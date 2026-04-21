package com.kirillkotov.program;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author Kotov Kirill
 */
public class Main {
    public static void main(String[] args) {
        try {
            /**
             * Retrieving Class Objects:
             * 1. If an instance of an object is available
             * Arrays are Objects - it is possible to invoke getClass() on them
             */
            ArrayList<String> arrayList = new ArrayList<>();
            System.out.println("getClass on ArrayList:");
            System.out.println(arrayList.getClass());
            System.out.println();

            /**
             * Map is the interface to HashMap
             * getClass returns HashMap
             */

            Map<Integer, String> map = new HashMap<>();
            System.out.println("Map<Integer, String> map = new HashMap<>();");
            System.out.println("getClass() on map returns class HashMap");
            System.out.println(map.getClass());
            System.out.println();

            /**
             * 2. If there is no instance or the type is primitive
             * Appending ".class" to the type name
             */
            int a = 10;
            System.out.println("getClass() on int a - won't work");
            /*System.out.println(a.getClass);*/
            System.out.println("Class c = int.class");
            Class<Integer> c = int.class;
            System.out.println("c = " + c);

            /**
             * Reflection APIs that may only be accessed if a Class has already been obtained.
             * getSuperclass() - returns the super class
             */
            Class<? extends ArrayList> c2 = arrayList.getClass();
            System.out.println("The super class for ArrayList:");
            System.out.println(c2.getSuperclass());
            System.out.println();

            /**
             * getClasses()
             * Returns all the public classes, interfaces, and enums
             * that are members of the class including inherited.
             */
            System.out.println("getClasses() for the HashMap class: " +
                    "(Nested classes/interfaces inherited from class java.util.AbstractMap)");
            Class<?>[] classes = map.getClass().getClasses();
            System.out.println(Arrays.toString(classes));

            /**
             * getDeclaredClasses()
             * Returns all declared classes, interfaces, and enums.
             */
            System.out.println("getDeclaredClasses() for the HashMap class:");
            Class<?>[] classesDeclared = map.getClass().getDeclaredClasses();
            System.out.println(Arrays.toString(classesDeclared));
            System.out.println();

            /**
             * getName()
             * Returns the name of class, interface,
             * array class, primitive type, or void as a String.
             */
            System.out.println("getName() for class ArrayList:");
            System.out.println(arrayList.getClass().getName());

            /**
             * If the class object represents a class of arrays,
             * the name consists of the name of the element type
             * preceded by one or more '[' characters
             * representing the depth of the array nesting.
             * I = int, D = double, C = char, F = float, Z = boolean
             */
            System.out.println("getName() for new int[][] ints:");
            int[][] ints = new int[2][2];
            System.out.println(ints.getClass().getName());
            System.out.println();

            class TV {
                public String brand;
                public int price;

                protected TV() {
                }

                public TV(String brand) {
                    this.brand = brand;
                }

                public TV(String brand, int price) {
                    this.brand = brand;
                    this.price = price;
                }

                public void watch() {
                }

                public void sell(int price) {
                    System.out.println("TV for sale for " + price);
                }

                @Override
                public String toString() {
                    return "TV{" +
                            "brand='" + brand + '\'' +
                            ", price=" + price +
                            '}';
                }
            }

            class Car {
                private String brand;
                private int year;

                public Car(String brand, int year) {
                    this.brand = brand;
                    this.year = year;
                }

                public void start() {
                }

                public void stop() {
                }

                public void sell(int price) {
                    System.out.println("Car for sale for " + price);
                }

                public void sell(String exchange) {
                    System.out.println("Car in exchange for " + exchange);
                }

                @Override
                public String toString() {
                    return "Car{" +
                            "brand='" + brand + '\'' +
                            ", year=" + year +
                            '}';
                }
            }

            /**
             * getFields()
             * Returns an array of fields containing all the accessible public fields
             */
            TV tvTest = new TV();
            System.out.println("Public fields of class TV:");
            System.out.println(Arrays.toString(tvTest.getClass().getFields()));
            System.out.println("Public fields of ArrayList:");
            System.out.println(Arrays.toString(arrayList.getClass().getFields()));
            Car auto = new Car("Mercedes", 2008);
            System.out.println("Public fields of class Car");
            System.out.println(Arrays.toString(auto.getClass().getFields()));
            System.out.println();

            /**
             * getDeclaredFields
             * Returns an array of all the fields declared by the class or interface.
             * This includes public, protected, default (package) access, and private fields,
             * but excludes inherited fields.
             */
            System.out.println("Declared fields of class TV:");
            System.out.println(Arrays.toString(tvTest.getClass().getDeclaredFields()));
            System.out.println("Declared fields of ArrayList:");
            System.out.println(Arrays.toString(arrayList.getClass().getDeclaredFields()));
            System.out.println("Declared fields of class Car");
            System.out.println(Arrays.toString(auto.getClass().getDeclaredFields()));
            System.out.println();

            /**
             * getField(String name)
             * Returns specified public field
             * The name parameter is a String specifying the simple name of the desired field.
             */
            System.out.println("getField(\"brand\") on TV class:");
            System.out.println(tvTest.getClass().getField("brand"));
            System.out.println(tvTest.getClass().getField("brand").getName());
        /*System.out.println("getField(\"owner\") on TV class: (this field doesn't exist)");
        System.out.println(TV.class.getField("owner"));
        System.out.println(TV.class.getField("owner").getName());*/
            System.out.println();

            /**
             * getDeclaredField(String name)
             * Returns specified public field
             * The name parameter is a String specifying the simple name of the desired field.
             */
            System.out.println("getDeclaredField(\"brand\") on Car class:");
            Car carNew = new Car("Mers", 2023);
            Field fieldBrand = carNew.getClass().getDeclaredField("brand");
            System.out.println(fieldBrand);
            System.out.println("Get data from field: " + fieldBrand.get(carNew));
            System.out.println();

            /**
             * getMethods
             * Returns an array of all the public methods of the class or interface
             */
            System.out.println("All the public methods of class Car:");
            System.out.println(Arrays.toString(carNew.getClass().getMethods()));

            /**
             * getDeclaredMethods
             * Returns an array of all the declared methods
             * including public, protected, default (package) access, and private methods, but excluding inherited methods.
             */
            System.out.println("All the declared methods of class Car:");
            System.out.println(Arrays.toString(carNew.getClass().getDeclaredMethods()));

            /**
             * getMethod(String name, Class<?>... parameterTypes)
             * Returns a specified public method
             * The name parameter is a String specifying the simple name of the desired method.
             * The parameterTypes parameter is an array of Class objects
             * that identify the method's formal parameter types.
             */
            System.out.println("getMethod on Car class that has different implementations of sell method:");
            System.out.println(carNew.getClass().getMethod("sell", int.class));
            System.out.println(carNew.getClass().getMethod("sell", String.class));
            System.out.println();

            /**
             * Reflection provides a means for invoking methods on a class.
             * Typically, this would only be necessary if it is not possible
             * to cast an instance of the class to the desired type in non-reflective code.
             *
             * Method.invoke(Object obj, Object... args)
             * The first argument is the object instance on which this particular method is to be invoked.
             * If the method is static, the first argument should be null.
             * Subsequent arguments are the method's parameters.
             */
            System.out.println("Invoke class TV method sell with int argument for price:");
            TV someObject = new TV();
            someObject.getClass().getMethod("sell", int.class).invoke(tvTest, 17000);

            /**
             * getConstructors
             * Returns an array of all the public constructors
             * The return type of this method is Constructor<?>[] and not Constructor<T>[], because
             * the returned array could be modified to hold Constructor objects for different classes
             */
            System.out.println("All the public constructors of class TV:");
            System.out.println(Arrays.toString(tvTest.getClass().getConstructors()));

            /**
             * getDeclaredConstructors
             * Returns an array of all the declared constructors
             * These are public, protected, default (package) access, and private constructors.
             * If the class has a default constructor, it is included in the returned array.
             */
            System.out.println("All the declared constructors of class TV: (default constructor is protected and will be shown)");
            System.out.println(Arrays.toString(tvTest.getClass().getDeclaredConstructors()));

            /**
             * getConstructor(Class<?>... parameterTypes)
             * Returns the specified public constructor
             * The parameterTypes parameter is an array of Class objects
             * that identify the constructor's formal parameter types.
             */
            System.out.println("getConstructor to return main TV constructor, taking String brand and int price:");
            System.out.println(someObject.getClass().getConstructor(String.class, int.class));
            System.out.println();

            /**
             * newInstance
             * Creates a new instance of the class
             * It is instantiated as if by a new expression with an empty argument list.
             * The class is initialized if it has not already been initialized.
             */
            System.out.println("newInstance of a class TV, using constructor with both brand and price:");
            TV tv2 = someObject.getClass().getConstructor(String.class, int.class)
                    .newInstance("Daewoo", 20000);
            System.out.println(tv2);
            System.out.println();

            /**
             * Reflection allows to create new array for type like another array
             * Type of new Array will define dynamically like given array
             * getComponentType() - method defines type of elements array
             * You may use it for creating generic arrays, define dynamically type
             */
            Integer[] mass = {1, 5, 7, 8, 7};
            Integer[] resArr = (Integer[]) Array.newInstance(mass.getClass().getComponentType(), 10);
            System.out.println(Arrays.toString(resArr));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
