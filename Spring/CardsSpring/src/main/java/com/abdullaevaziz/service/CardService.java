package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Card;
import com.abdullaevaziz.model.Category;

import java.util.List;

public interface CardService {

    void add(long categoryId, Card card);

    List<Card> getCategories(long categoriesId);

    List<Card> get();

    Card get(long id);

    Card update(Card card);

    Card delete(long id);

}
