package com.abdullaevaziz.service;


import com.abdullaevaziz.model.Admin;
import com.abdullaevaziz.model.Compliment;
import com.abdullaevaziz.model.ComplimentsTelegramUsers;
import com.abdullaevaziz.model.TelegramUser;

import java.util.List;

public interface ComplimentsTelegramUsersService {

    ComplimentsTelegramUsers saveComplimentForUser(TelegramUser telegramUser, Compliment compliment);

}
