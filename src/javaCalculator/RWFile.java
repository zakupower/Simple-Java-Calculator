package javaCalculator;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tomov on 26.11.2016 г..
 */
public class RWFile {
    private String nameOfFile;
    private ArrayList<String> lines = new ArrayList<String>();
    private Charset utf8 = StandardCharsets.UTF_8;
    public RWFile() {
         this.nameOfFile = "";
    }
    public RWFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }

    public String getNameOfFile() {
        return nameOfFile;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(ArrayList<String> lines) {
        this.lines = lines;
    }

    public void setNameOfFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }
    public void operationInput (Operation op) {
        lines.add(op.toString());
    }
    public ArrayList<Operation> operationOutput() {
        ArrayList<Operation> arr = new ArrayList<Operation>();
        for(String line : lines) {
            String[] elements = line.split(",");
            Operation op = new Operation(Integer.parseInt(elements[0]),
                    Double.parseDouble(elements[1]),
                    Double.parseDouble(elements[2]),
                    elements[3],
                    Double.parseDouble(elements[4]));
        arr.add(op);
        }
        return arr;
    }

    public void writeInformationToFile() {
        try {
            Files.write(Paths.get(nameOfFile), lines, utf8);
        } catch(IOException exc) {
            exc.printStackTrace();
        }
    }
    public void readFromFile(){
        try {
           lines = (ArrayList<String>) Files.readAllLines(Paths.get(nameOfFile));
        } catch(IOException exc) {
            exc.printStackTrace();
        }

    }
}
