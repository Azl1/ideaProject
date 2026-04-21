package com.kirillkotov.controllers;

import com.kirillkotov.config.Word;
import com.kirillkotov.config.DataList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/main")
public class MeinController {
    @Value("${projecrt.token}")
    private String token;

    private DataList dataList;

    @Autowired
    public void setDataList(DataList dataList) {
        this.dataList = dataList;
    }

    @GetMapping
    public List<Word> get(){
        System.out.println(this.token);
        return this.dataList.getList();
    }
}
