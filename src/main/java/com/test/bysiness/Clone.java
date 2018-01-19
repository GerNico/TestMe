package com.test.bysiness;

import java.io.*;

public class Clone
{
    private Clone(){}
    static public Object deepCopy(Object oldObj)
    {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        try
        {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(oldObj);
            oos.flush();
            ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bin);
            return ois.readObject();
        }
        catch (Exception e){
            throw new RuntimeException("Clone by serialization exception");
        }
        finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (ois != null) {
                    ois.close();
                }
            }catch (Exception e){
                throw new RuntimeException("Clone by serialization exception");
            }
        }

    }

}