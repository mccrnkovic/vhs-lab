package com.example.vhs_lab_mihovil.mapper;

public interface GenericMapper<T, N> {
    N toDto(T t);
    T toModel(N n);
}
