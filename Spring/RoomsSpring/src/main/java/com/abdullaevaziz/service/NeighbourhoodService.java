package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Neighbourhood;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface NeighbourhoodService {

    Neighbourhood add(Neighbourhood neighbourhood);
    Neighbourhood get(long id);
    Neighbourhood findByName(String name);
    List<Neighbourhood> findAll();
    void exportToCSV(String outputFileName) throws IOException;
}
