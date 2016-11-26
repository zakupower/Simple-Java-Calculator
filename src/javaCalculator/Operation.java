package javaCalculator;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Tomov on 26.11.2016 г..
 */
public class Operation {
    private int opId=0;
    private double opX;
    private double opY;
    private SimpleStringProperty operation = new SimpleStringProperty("");
    private double result;

    public Operation() {
        this.opX=0;
        this.opY=0;
        this.operation.set("NaO");//Not an operation
        this.result = Double.NaN;
    }

    public Operation(int opId,double opX, double opY, String operation, double result) {
        this.opId = opId;
        this.opX = opX;
        this.opY = opY;
        this.operation.set(operation);
        this.result = result;
    }

    public void setOpId(int opId) {
        this.opId = opId;
    }

    public void setOpX(double opX) {
        this.opX = opX;
    }

    public void setOpY(double opY) {
        this.opY = opY;
    }

    public void setOperation(String operation) {
        this.operation.set(operation);
    }

    public void setResult(double result) {
        this.result = result;
    }

    public int getOpId() {
        return opId;
    }

    public double getOpX() {
        return opX;
    }

    public double getOpY() {
        return opY;
    }

    public String getOperation() {
        return operation.get();
    }

    public double getResult() {
        return result;
    }





}
