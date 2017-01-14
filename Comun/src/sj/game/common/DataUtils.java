package sj.game.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataUtils {
	public static void saveObjectToFile(Object o, String path) throws IOException{
		FileOutputStream fout = new FileOutputStream(path);
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(o);
	}
	public static Object readObjectFromFile(String path) throws IOException, ClassNotFoundException{
		FileInputStream fout = new FileInputStream(path);
		ObjectInputStream oos = new ObjectInputStream(fout);
		return oos.readObject();
	}
}
