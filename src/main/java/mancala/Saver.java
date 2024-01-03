package mancala;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.IOException;

public class Saver {
    private static final long serialVersionUID = 1L;

    /**
     * Saves a Serializable object to a file.
     *
     * @param toSave   The Serializable object to save.
     * @param filename The name of the file to save the object to.
     */
    public static void saveObject(final Serializable toSave, final String filename) {
        try {
            final FileOutputStream fileOut = new FileOutputStream("assets/" + filename);
            final ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(toSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a Serializable object from a file.
     *
     * @param filename The name of the file to load the object from.
     * @return The loaded Serializable object.
     */
    public static Serializable loadObject(final String filename) {
        Serializable retValue = null;
        try {
            final FileInputStream fileIn = new FileInputStream("assets/" + filename);
            final ObjectInputStream objIn = new ObjectInputStream(fileIn);
            retValue = (Serializable) objIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return retValue;
    }

}
