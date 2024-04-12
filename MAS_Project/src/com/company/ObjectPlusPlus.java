package com.company;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

public abstract class ObjectPlusPlus extends ObjectPlus implements Serializable {

    private Map<String, Map<Object, ObjectPlusPlus>> links = new Hashtable<>();
    private static Set<ObjectPlusPlus> parts = new HashSet<>();

    public ObjectPlusPlus() {
        super();
    }
    public void hash(){
        System.out.println(links);
    }
    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object
            qualifier) {
        addLink(roleName, reverseRoleName, targetObject, qualifier, 2);
    }

    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject) {
        addLink(roleName, reverseRoleName, targetObject, targetObject);
    }

    public boolean anyLink(String role){
        return links.containsKey(role);
    }

    public void addPart(String roleName, String reverseRoleName, ObjectPlusPlus partObject) throws
            Exception {
        if (parts.contains(partObject)) {
            throw new Exception("The part is already connected to a whole!");
        }
        addLink(roleName, reverseRoleName, partObject);
        parts.add(partObject);
    }

    public void removePart(String roleName, String reverseRoleName, ObjectPlusPlus partObject){
        removeLink(roleName,reverseRoleName,partObject);
        parts.remove(partObject);
    }

    private void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier, int counter) {
        Map<Object, ObjectPlusPlus> objectLinks;
        if (counter < 1) {
            return;
        }
        if (links.containsKey(roleName)) {
            objectLinks = links.get(roleName);
        } else {
            objectLinks = new HashMap<>();
            links.put(roleName, objectLinks);
        }
        if (!objectLinks.containsKey(qualifier)) {
            objectLinks.put(qualifier, targetObject);
            targetObject.addLink(reverseRoleName, roleName, this, this, counter - 1);
        }
    }

    public void removeLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject){
        if (links.containsKey(roleName) && targetObject.links.containsKey(reverseRoleName)){
            links.get(roleName).remove(targetObject);
            targetObject.links.get(reverseRoleName).remove(this);
            if(links.get(roleName).size()==0){
                links.remove(roleName);
            } if (targetObject.links.get(reverseRoleName).size() == 0) {
                targetObject.links.remove(reverseRoleName);
            }
        }
    }

    public ObjectPlusPlus[] getLinks(String roleName) throws Exception {
        if(anyLink(roleName)) {
            Map<Object, ObjectPlusPlus> objectLinks;
            if (!links.containsKey(roleName)) {
                throw new Exception("No links for the role: " + roleName);
            }
            objectLinks = links.get(roleName);
            return (ObjectPlusPlus[]) objectLinks.values().toArray(new ObjectPlusPlus[0]);
        } return null;
    }

    public void showLinks(String roleName, PrintStream stream) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;
        if (!links.containsKey(roleName)) {

            throw new Exception("No links for the role: " + roleName);
        }
        objectLinks = links.get(roleName);
        Collection col = objectLinks.values();
        stream.println(this.getClass().getSimpleName() + " links, role '" + roleName + "':");
        for (Object obj : col) {
            stream.println(" " + obj);
        }

    }
    public ObjectPlusPlus getLinkedObject(String roleName, Object qualifier) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;
        if(!links.containsKey(roleName)) {
            throw new Exception("No links for the role: " + roleName);
        }
        objectLinks = links.get(roleName);
        if(!objectLinks.containsKey(qualifier)) {
            throw new Exception("No link for the qualifer: " + qualifier);
        }
        return objectLinks.get(qualifier);
    }
    public boolean isLink(String roleName, ObjectPlusPlus targetObject) {
        Map<Object, ObjectPlusPlus> objectLink;
        if(!links.containsKey(roleName)) {
            return false;
        }
        objectLink = links.get(roleName);
        return objectLink.containsValue(targetObject);
    }


}

