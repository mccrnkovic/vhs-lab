package com.example.vhs_lab_mihovil.exception;

import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

@Getter
public class NotDeletedException extends Exception{
    String id;
    String repositoryName;

    public NotDeletedException(JpaRepository repository, String id){
        super("notDeletedMsg");
        this.id = id;
        this.repositoryName = repository.getClass().getInterfaces()[0].getSimpleName();
    }
}
