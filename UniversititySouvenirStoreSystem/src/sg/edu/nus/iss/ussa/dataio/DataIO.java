package sg.edu.nus.iss.ussa.dataio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Super Class for Data Access Object, provide access to flat file
 *
 * @author Andy Pan
 *
 */
public abstract class DataIO {

    private static final String relativePath = "./data/";

    public ArrayList<String> loadFile(String fullpath) throws IOException {

        ArrayList<String> stringList = new ArrayList<String>();
        File inFile = new File(fullpath);
        FileReader fr = new FileReader(inFile);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            stringList.add(line);
        }

        br.close();
        fr.close();

        return stringList;
    }

    public void saveString(String fullpath, ArrayList<String> stringList) throws IOException {
        File outFile = new File(fullpath);
        BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(outFile));
        PrintWriter pw = new PrintWriter(bw);

        for (String line : stringList) {
            pw.println(line);
        }
        pw.flush();

        pw.close();
        bw.close();
    }
    public static String getcDatafolderpath() {
        return relativePath;
    }

}
