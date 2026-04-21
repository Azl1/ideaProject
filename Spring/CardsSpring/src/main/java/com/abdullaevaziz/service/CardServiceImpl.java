package com.abdullaevaziz.service;

import com.abdullaevaziz.model.Card;
import com.abdullaevaziz.model.Category;
import com.abdullaevaziz.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService{

    private CardRepository cardRepository;
    private CategoryService categoryService;

    @Autowired
    public void setCardRepository(CardRepository cardRepository){
        this.cardRepository = cardRepository;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @Override
    public Card get(long id) {
        return this.cardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Card does not exists!"));
    }

    @Override
    public List<Card> getCategories(long categoriesId) {
        return this.cardRepository.findByCategoryId(categoriesId)
                .orElseThrow(() -> new IllegalArgumentException("Categories does not exists userId!"));
    }

    @Override
    public List<Card> get() {
        return this.cardRepository.findAll();
    }

    @Override
    public void add(long categoryId, Card card) {
        Category category = this.categoryService.get(categoryId);
        card.setCategory(category);
        try {
            this.cardRepository.save(card);
        } catch (Exception e) {
            throw new IllegalArgumentException("Card has already added!");
        }
    }

    @Override
    public Card update(Card card) {
        Card base = this.get(card.getId());
        base.setQuestion(card.getQuestion());
        base.setAnswer(card.getAnswer());
        try {
            this.cardRepository.save(base);
            return card;
        } catch (Exception e) {
            System.out.println(e.getClass());
            throw new IllegalArgumentException("Card is already exists!");
        }
    }

    @Override
    public Card delete(long id) {
        Card card = get(id);
        this.cardRepository.deleteById(id);
        return card;
    }
}
