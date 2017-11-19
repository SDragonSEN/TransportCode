package autoStudy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ImportExportData {
	public static void exportData(HistoryData hd) throws FileNotFoundException, IOException{
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.obj"));
		out.writeObject(hd);
		out.close();
	}
	public static HistoryData importData() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.obj"));
		return (HistoryData)in.readObject();
	}
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		HistoryData hd = new HistoryData();
		System.out.println(hd.getValue());
		
		hd.addBefore(-2500);
		hd.addBefore(-2000);
		hd.addSuccess(-1500);
		hd.addLate(-500);
		hd.print();
		System.out.println(hd.getValue());
		//exportData(hd);
		importData().print();
	}
}
