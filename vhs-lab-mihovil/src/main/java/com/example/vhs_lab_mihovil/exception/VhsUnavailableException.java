package com.example.vhs_lab_mihovil.exception;

import lombok.Getter;

@Getter
public class VhsUnavailableException extends Exception{
    String vhsId;
    public VhsUnavailableException(String vhsId) {
        super("vhsUnavailableMsg");
        this.vhsId = vhsId;
    }
}
