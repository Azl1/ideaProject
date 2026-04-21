package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Host;

public interface HostService {

    Host add(Host host);
    Host get(long id);

    Host findByName(String name);
}
