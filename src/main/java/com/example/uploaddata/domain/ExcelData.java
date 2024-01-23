package com.example.uploaddata.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExcelData {
    private Integer year; //수강년도
    private String semester; //학기
    private Integer academicNumber; //학수번호
    private String name; //교과목명

}
