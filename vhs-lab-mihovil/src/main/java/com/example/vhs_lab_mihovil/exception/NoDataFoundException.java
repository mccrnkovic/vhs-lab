package com.example.vhs_lab_mihovil.exception;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

@Getter
public class NoDataFoundException extends Exception {
    private String id;
    private String repositoryName;

    public NoDataFoundException(JpaRepository repository, String id) {
        super("noDataFoundMsg"); // Will be processed by GenericExceptionHandler
        this.id = id;
        this.repositoryName = repository.getClass().getInterfaces()[0].getSimpleName();
    }
}
