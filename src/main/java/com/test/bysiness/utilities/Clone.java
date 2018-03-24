package com.test.bysiness.utilities;

import java.io.*;

public class Clone {
    static public Object deepCopy(Object oldObj) {
        ObjectOutputStream oos;
        ObjectInputStream ois;
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(oldObj);
            oos.flush();
            ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bin);
            return ois.readObject();
        } catch (Exception e) {
            throw new TestLogicException("Clone by serialization exception "+e.getLocalizedMessage());
        }
    }
}