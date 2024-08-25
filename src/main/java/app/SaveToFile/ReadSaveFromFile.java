package app.SaveToFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ReadSaveFromFile {
    public boolean SaveFile(Object obj, String filePath) throws Exception {
        ObjectOutputStream oos = null;
        oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(obj);
        oos.flush();
        oos.close();
        return true;
    }

    public Object ReadFile(String filePath) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        Object o = ois.readObject();
        ois.close();
        return o;
    }
}
