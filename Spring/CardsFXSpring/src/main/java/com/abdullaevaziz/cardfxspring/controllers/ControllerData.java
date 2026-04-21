package com.abdullaevaziz.cardfxspring.controllers;

import java.io.IOException;

public interface ControllerData <T> {
    void initData(T value) throws IOException;
}
