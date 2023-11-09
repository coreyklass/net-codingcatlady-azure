package net.codingcatlady.util;

import java.util.*;

public class JavaCast {


    /**
     * Returns if the object is a simple value
     * @param object
     * @return
     */
    static public Boolean isSimpleValue(Object object) {
        Boolean simple = false;

        simple = simple || (object instanceof String);
        simple = simple || (object instanceof Number);
        simple = simple || (object instanceof Boolean);

        return simple;
    }




    /**
     * Convert the object to a string
     * @param object
     * @return
     */
    static public String toString(Object object) {
        if (object == null) {
            return null;
        }

        if (object instanceof String) {
            return (String) object;

        } else {
            return object.toString();
        }
    }


    /**
     * Convert the object to an Integer
     * @param object
     */
    static public Integer toInteger(Object object) {
        if (object instanceof Integer) {
            return (Integer) object;

        } else {
            String string = toString(object);

            try {
                return new Integer(string);
            } catch (NumberFormatException exception) {
                // cannot be converted
                return null;
            }
        }
    }


    /**
     * Convert the object to a hashmap
     * @param object
     * @return
     */
    static public HashMap<Object, Object> toHashMap(Object object) {
        if (object instanceof Map) {
            Map<?, ?> map = ((Map<?, ?>) object);

            HashMap<Object, Object> newMap = new HashMap<>();

            // loop over the keys in the map
            for (Object ixKey : map.keySet()) {
                newMap.put(ixKey, map.get(ixKey));
            }

            return newMap;
        }

        System.out.println("NOT A MAP");

        return null;
    }




    /**
     * Convert the object to a List
     * @param object
     * @return
     */
    static public List<Object> toList(Object object) {
        if (object instanceof Collection) {
            Collection<?> collection = ((Collection<?>) object);
            List<Object> list = new ArrayList<>(collection);

            return list;
        }

        System.out.println("NOT A COLLECTION");

        return null;
    }






    /**
     * Converts the object to the specified class
     * @param clazz
     * @param <T>
     */
    static public <T> T to(Class<? extends T> clazz, Object object) {
        try {
            // cast and return the object
            return clazz.cast(object);

        } catch (ClassCastException e) {
            // return NULL if the object isn't cast-able
            System.out.println("Error casting: " + e.getMessage());
            return null;
        }
    }




}
