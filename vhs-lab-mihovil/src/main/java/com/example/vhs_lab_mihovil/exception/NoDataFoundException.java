package com.example.vhs_lab_mihovil.exception;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

@Data
public class NoDataFoundException extends Exception {
    private Integer id;
    private String repositoryName;

    public NoDataFoundException(JpaRepository repository, Integer id) {
        super("noDataFoundMsg"); // Will be processed by GenericExceptionHandler
        this.id = id;
        this.repositoryName = repository.getClass().getInterfaces()[0].getSimpleName();
    }
}
