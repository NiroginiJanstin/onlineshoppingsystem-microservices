package com.shopping.customerservice.utils;

public class validation {
    public static boolean emptyFieldValidation(String value){
        if(value.equals("") || value == null){
            return true;
        }
        else{
            return false;
        }
    }
}
