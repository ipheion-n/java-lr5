package storage;

import model.Shape;

import java.io.*;

/** Робота з файлами в окремому класі: об'єктні потоки + серіалізація за замовчуванням. */
public class ShapeFileManager {

    /** Зберігає набір фігур у файл; повертає розмір створеного файлу в байтах. */
    public long save(String path, Shape[] shapes) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(path)))) {
            out.writeObject(shapes);
        }
        return new File(path).length();
    }

    /** Читає набір фігур із файлу. */
    public Shape[] load(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(path)))) {
            return (Shape[]) in.readObject();
        }
    }
}