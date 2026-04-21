package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Neighbourhood;
import com.abdullaevaziz.repository.NeighbourhoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;

@Service
public class NeighbourhoodServiceImpl implements NeighbourhoodService {
    private NeighbourhoodRepository neighbourhoodRepository;

    @Value("${datasource.filename.boroughs}")
    private String fileName;

    @Autowired
    public void setNeighbourhoodRepository(NeighbourhoodRepository neighbourhoodRepository) {
        this.neighbourhoodRepository = neighbourhoodRepository;
    }

    @PostConstruct
    public void init() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    String[] split = line.split(";");
                    String name = split[0];
                    String borough = split[1];
                    Neighbourhood neighbourhood1 = new Neighbourhood(name, borough);
                    this.add(neighbourhood1);
                } catch (RuntimeException ignored) {
                }
            }
        } catch (IOException e) {
            System.out.println("Неверный формат файлов");
        }
    }


    @Override
    public Neighbourhood add(Neighbourhood neighbourhood) {
        try {
            return this.neighbourhoodRepository.save(neighbourhood);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Neighbourhood has already added!");
        }
    }

    @Override
    public Neighbourhood get(long id) {
        return this.neighbourhoodRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Neighbourhood does not exists!"));
    }

    @Override
    public Neighbourhood findByName(String name) {
        return this.neighbourhoodRepository.findByName(name). orElseThrow(() -> new IllegalArgumentException("Neighbourhood name does not exists!"));
    }

    @Override
    public List<Neighbourhood> findAll() {
        return this.neighbourhoodRepository.findAll();
    }

    @Override
    public void exportToCSV(String outputFileName) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFileName))){
            bufferedWriter.write("Neighbourhood;Borough\n");
            for (Neighbourhood neighbourhood : this.findAll()) {
                bufferedWriter.write(neighbourhood.toCSV());
                bufferedWriter.newLine();
            }
        }
    }

}
