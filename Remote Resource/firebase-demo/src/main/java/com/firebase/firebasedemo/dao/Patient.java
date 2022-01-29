package com.firebase.firebasedemo.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Data
public class Patient {
    private String name;
    private int age;
    private String city;
}
