package storage;

import model.Shape;

import java.io.*;


public class ShapeFileManager {

    
    public long save(String path, Shape[] shapes) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(path)))) {
            out.writeObject(shapes);
        }
        return new File(path).length();
    }

    
    public Shape[] load(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(path)))) {
            return (Shape[]) in.readObject();
        }
    }
}