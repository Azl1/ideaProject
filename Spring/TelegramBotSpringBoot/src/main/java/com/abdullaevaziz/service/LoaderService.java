package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Compliment;
import com.abdullaevaziz.model.DataList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class LoaderService {
    private DataList dataList;

    private ComplimentService complimentService;

    @Autowired
    public void setDataList(DataList dataList) {
        this.dataList = dataList;
    }

    @Autowired
    public void setCompliment(ComplimentService complimentService) {
        this.complimentService = complimentService;
    }

    /**
     * 2. Приложение загружает из конфигурационного .yaml файла
     * все комплименты, которые отсутствуют в базе данных
     */
    @PostConstruct
    public void init() {
        List<Compliment> resList = dataList.getList();
        for (Compliment compliment : resList) {
            try {
                complimentService.add(compliment);
            } catch (IllegalArgumentException ignored) {
            }
        }
    }
}
