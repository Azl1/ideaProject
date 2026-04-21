package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Host;
import com.abdullaevaziz.model.Neighbourhood;
import com.abdullaevaziz.repository.HostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class HostServiceImpl implements HostService {

    private HostRepository hostRepository;
    @Value("${datasource.filename.rooms}")
    private String fileName;

    @Autowired
    public void setHostRepository(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @PostConstruct
    public void init() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    String[] split = line.split(";");
                    int hostId = Integer.parseInt(split[2]);
                    String hostName = split[3];
                    Host host = new Host(hostId, hostName);
                    this.hostRepository.save(host);
                } catch (RuntimeException ignored) {
                }
            }
        } catch (IOException e) {
            System.out.println("Неверный формат файлов");
        }
    }

    @Override
    public Host add(Host host) {
        try {
            return this.hostRepository.save(host);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Host has already added!");
        }
    }

    @Override
    public Host get(long id) {
        return this.hostRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("Host does not exists!"));
    }

    @Override
    public Host findByName(String name) {
        return hostRepository.findByName(name).
        orElseThrow(() -> new IllegalArgumentException("Host name does not exists!"));
    }
}
