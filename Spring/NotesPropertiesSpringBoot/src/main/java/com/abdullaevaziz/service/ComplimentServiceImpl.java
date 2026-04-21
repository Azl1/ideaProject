package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Compliment;
import com.abdullaevaziz.model.DataList;
import com.abdullaevaziz.model.UserDetailsImpl;
import com.abdullaevaziz.util.UtilRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ComplimentServiceImpl implements ComplimentService {

    private DataList dataList;

    private HashMap<Long, ArrayList<Integer>> hashMap = new HashMap<>();

    @Autowired
    public void setDataList(DataList dataList){
        this.dataList = dataList;
    }

    @Override
    public List<Compliment> getList() {
        return this.dataList.getComplimentList();
    }

    @Override
    public Compliment getById(Authentication authentication, long id) {
        return this.dataList.getComplimentList().stream().
                filter(x -> x.getId() == id).findFirst().
                orElse(null);
    }

    @Override
    public Compliment getComplimentRandom(Authentication authentication) {
        long id = ((UserDetailsImpl) authentication.getPrincipal()).getId();

        List<Compliment> compliments = this.getList();

        ArrayList<Integer> indexes = hashMap.getOrDefault(id, new ArrayList<>());

        if(indexes.size() == compliments.size()){
            indexes.clear();
        }

        int ind;
        do {
            ind = UtilRandom.getRandom(0, compliments.size() - 1);
        }
        while (indexes.contains(ind));

        ArrayList<Integer> integerArrayList = hashMap.getOrDefault(id, new ArrayList<>());
        integerArrayList.add(ind);
        hashMap.put(id, integerArrayList);
        return compliments.get(ind);
    }
}
