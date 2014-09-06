/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dostojic.common.transfer;

import java.io.Serializable;

/**
 *
 * @author dejan
 */
public class TransferObject implements Serializable{
    
    private boolean operationSucess;
    private Object message;
    private Object data;
    private int operation;;

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }
    
    public boolean isOperationSucess() {
        return operationSucess;
    }

    public void setOperationSucess(boolean operationSucess) {
        this.operationSucess = operationSucess;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    
    
}
