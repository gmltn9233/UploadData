package com.example.uploaddata.controller;


import com.example.uploaddata.domain.ExcelData;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExcelController {
    @GetMapping("/excel")
    public String main(){
        return "excel";
    }

    @PostMapping("/excel/read")
    public String readExcel(@RequestParam("file") MultipartFile file, Model model)
        throws IOException{

        List<ExcelData> dataList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if(!extension.equals("xlsx") && !extension.equals("xls")){
            throw new IOException("엑셀파일만 업로드 해주세요");
        } //엑셀파일이 아닌 경우 에러처리

        Workbook workbook = null;

        if (extension.equals("xlsx")){
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")){
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);

        DataFormatter dataFormatter = new DataFormatter();

        for(int i=4; i<worksheet.getPhysicalNumberOfRows(); i++){

            Row row = worksheet.getRow(i);

            ExcelData data = new ExcelData();

            String yearAsString = dataFormatter.formatCellValue(row.getCell(1));
            data.setYear(Integer.parseInt(yearAsString));

            data.setSemester(row.getCell(2).getStringCellValue());

            String academicValueAsString = dataFormatter.formatCellValue(row.getCell(3));
            data.setAcademicNumber(Integer.parseInt((academicValueAsString)));

            data.setName((row.getCell(4).getStringCellValue()));

            dataList.add(data);
        }

        model.addAttribute("datas",dataList);

        return "excelList";

    }
}
