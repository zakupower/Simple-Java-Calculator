package javaCalculator;



import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

public class Controller {
    @FXML private TextField xField;
    @FXML private TextField yField;
    @FXML private TextField resultField;
    @FXML private TextField writeFileName;
    @FXML private TextField readFileName;
    @FXML private TableView tableView;


    private int operationID = 0;


    public void addXY (ActionEvent event) {
        double x = checkAndParse(xField.getText());
        double y = checkAndParse(yField.getText());
        double result;
        result = x + y;
        resultField.setText(checkIfInt(result));
        addOperationToHistory(x,y,"+",result);

    }
    public void subXY (ActionEvent event) {
        double x = checkAndParse(xField.getText());
        double y = checkAndParse(yField.getText());
        double result;
        result = x - y;
        resultField.setText(checkIfInt(result));
        addOperationToHistory(x,y,"-",result);

    }
    public void divXY (ActionEvent event) {
        double x = checkAndParse(xField.getText());
        double y = checkAndParse(yField.getText());
        String result;

        if(y == 0) {
            result = "NaN";
        } else {
            result = checkIfInt(x / y);
        }
        resultField.setText(result);
        addOperationToHistory(x,y,"/",Double.parseDouble(result));
    }
    public void mulXY (ActionEvent event) {
        double x = checkAndParse(xField.getText());
        double y = checkAndParse(yField.getText());
        double result;
        result = x * y;
        resultField.setText(checkIfInt(result));
        addOperationToHistory(x,y,"*",result);
    }
    public void powXY (ActionEvent event) {
        double x = checkAndParse(xField.getText());
        double y = checkAndParse(yField.getText());
        double result;
        result = Math.pow(x,y);
        resultField.setText(checkIfInt(result));
        addOperationToHistory(x,y,"x^y",result);
    }
    public void powYX (ActionEvent event) {
        double x = checkAndParse(xField.getText());
        double y = checkAndParse(yField.getText());
        double result;
        result = Math.pow(y,x);
        resultField.setText(checkIfInt(result));
        addOperationToHistory(x,y,"y^x",result);
    }


    private boolean isInt(double x) {
        x = Math.abs(x);
       return !(Math.floor(x) < x);
    }

    private String checkIfInt(double res) {
        if(Math.floor(res) != res) {
            return Double.toString(res);
        }
        else {
            return Integer.toString((int)res);
        }
    }

    public void itemSelected() {
        ObservableList<Operation> row;
        row = tableView.getSelectionModel().getSelectedItems();
        if(isSelected(row)) {
            restoreResultFromRow(row);
        } else {

        }
    }
    public void itemRemove() {
        ObservableList<Operation> row , allRows;
        allRows = tableView.getItems();
        row = tableView.getSelectionModel().getSelectedItems();

        if(isSelected(row)) {
            decrementallRowsId(row);
            row.forEach(allRows::remove);
        } else {

        }
    }
    public void writeToFile(ActionEvent event) {
        String fileName = writeFileName.getText();
        fileName += ".txt";
        RWFile file = new RWFile(fileName);
        ObservableList<Operation> data = tableView.getItems();
        for(Operation op : data) {
            if(op!= null)
            file.operationInput(op);
        }
        file.writeInformationToFile();
    }
    public void readFromFile(ActionEvent event) {
            String fileName = readFileName.getText();
            fileName += ".txt";
            RWFile file = new RWFile(fileName);
            if(file.readFromFile()) {
                ObservableList<Operation> data = tableView.getItems();
                data.clear();
                ArrayList<Operation> ops = file.operationOutput();
                for (Operation op : ops) {
                    data.add(op);
                    operationID = op.getOpId();
                }
            } else {
                readFileName.setText("No Such File !");
            }
    }
    private void decrementallRowsId(ObservableList<Operation> row) {
        ObservableList<Operation> data = tableView.getItems();
        Operation selectedRow = row.get(0);
        for(Operation op : data) {
            if(selectedRow.getOpId()<op.getOpId()) {
                op.setOpId(op.getOpId() - 1);
            } else {

            }
        }
        operationID--;

    }
    private double checkAndParse(String str) {
        if(isValidNumber(str)) {
            return Double.parseDouble(str);
        } else {
            return Double.NaN;
        }
    }
    private void addOperationToHistory(double opX, double opY, String operation,double result) {
        operationID++;
        ObservableList<Operation> data = tableView.getItems();
        Operation current = new Operation(operationID,opX,opY,operation,result);
        data.add(current);

        xField.clear();
        yField.clear();

    }
    private void restoreResultFromRow(ObservableList<Operation> row) {
        Operation op = row.get(0);
        xField.setText(Double.toString(op.getOpX()));
        yField.setText(Double.toString(op.getOpY()));
        resultField.clear();
    }
    private boolean isSelected(ObservableList<Operation> row) {
        return (row.get(0)!=null);
    }
    private boolean isValidNumber (String input) {
        return ((!input.isEmpty()) && isNumeric(input));
    }
    private boolean isNumeric(String str)
    {
        return str.matches("\\d+|(\\.\\d+)|(-\\d+)");  //match a number and number with decimal .
    }
}
