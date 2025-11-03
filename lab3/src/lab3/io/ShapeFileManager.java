package lab3.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import lab3.model.Shape;

public class ShapeFileManager {

    public static void saveToFile(Shape[] shapes, File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(shapes);
        }
    }

    public static Shape[] loadFromFile(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof Shape[]) {
                return (Shape[]) obj;
            } else {
                throw new IOException("Неправильний формат файлу: очікувався масив фігур");
            }
        }
    }
}

