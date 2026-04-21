package com.abdullaevaziz.userfilesspringbootfx.controllers;

import java.io.IOException;

public interface ControllerData <T>{

    void initData(T value) throws IOException;
}
