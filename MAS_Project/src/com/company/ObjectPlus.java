package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public abstract class ObjectPlus implements Serializable {
    private static Map<Class, List<ObjectPlus>> allExtents = new Hashtable<>();

    public ObjectPlus() {
        List<ObjectPlus> extent = null;
        Class theClass = this.getClass();
        if(allExtents.containsKey(theClass)) {
            extent = allExtents.get(theClass);
        }
        else {
            extent = new ArrayList();
            allExtents.put(theClass, extent);
        }
        extent.add(this);
    }
    public static void removeExtent(ObjectPlus theClass) throws Exception {
        if(allExtents.containsKey(theClass.getClass())) {
            allExtents.get(theClass.getClass()).remove(theClass);
        }
        else {
            throw new Exception("Nieznana klasa " + theClass);
        }
    }
    public static void removeFullExtent(Class theClass) throws Exception {
        if(allExtents.containsKey(theClass)){
            allExtents.remove(theClass);
        }
        else {
            throw new Exception("Nieznana klasa " + theClass);
        }
    }
    public static void writeExtents(ObjectOutputStream stream) throws IOException {
        stream.writeObject(allExtents);
        stream.close();
    }
    public static void readExtents(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        allExtents = (Hashtable) stream.readObject();
        stream.close();
    }
    public static <T> Iterable<T> getExtent(Class<T> type) throws
            ClassNotFoundException {
        if (allExtents.containsKey(type)) {
            return (Iterable<T>) allExtents.get(type);
        }
        throw new ClassNotFoundException(
                String.format("%s. Stored extents: %s",
                        type.toString(),
                        allExtents.keySet()));
    }

    public static void showExtent(Class theClass) throws Exception {
        List<ObjectPlus> extent = null;
        if(allExtents.containsKey(theClass)) {

            extent = allExtents.get(theClass);
        }
        else {
            throw new Exception("Nieznana klasa " + theClass);
        }
        System.out.println("Ekstencja klasy: " + theClass.getSimpleName());
        for(Object obj : extent) {
            System.out.println(obj);
        }
    }

}

