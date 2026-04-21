package com.kirillkotov.service;

import com.kirillkotov.model.TV;
import com.kirillkotov.model.User;
import com.kirillkotov.repository.TVRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TVServiceImpl implements TVService {

    private final UserService userService;

    private final TVRepository tvRepository;

    private final MongoTemplate mongoTemplate;

    @Override
    public void add(String userId, TV tv) {
        User user = this.userService.get(userId);
        try {
            user.getTvs().add(tv);
            this.tvRepository.save(tv);
            this.userService.addTvs(user);
        } catch (Exception e) {//TODO handle Mongo exception
            throw new IllegalArgumentException("TV already exists!");
        }
    }

    @Override
    public List<TV> get() {
        return this.tvRepository.findAll();
    }

    @Override
    public TV get(String id) {
        return this.tvRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TV doesn't exist"));
    }

    @Override
    public TV delete(String id) {
        TV tv = this.get(id);
        this.tvRepository.deleteById(id);
        //Каскадное удаление не поддерживается в MongoDB
        List<User> users = this.userService.get();
        users.stream().peek(x -> x.getTvs().remove(tv)).forEach(userService::addTvs);
        return tv;
    }

    @Override
    public TV update(TV tv) {
        TV oldTV = this.get(tv.getId());
        oldTV.setBrand(tv.getBrand());
        oldTV.setModel(tv.getModel());
        oldTV.setColor(tv.getColor());
        oldTV.setTimeExpectancy(tv.getTimeExpectancy());
        oldTV.setPrice(tv.getPrice());
        try {
            this.tvRepository.save(oldTV);
            return oldTV;
        } catch (Exception e) { //TODO handle Mongo exception
            throw new IllegalArgumentException("TV already exists!");
        }
    }

    @Override
    public void updatePrice() {
        Query query = new Query();
        query.addCriteria(Criteria.where("price").lte(20000));
        Update update = new Update();
        update.set("price", 30000);

        this.mongoTemplate.updateMulti(query, update, TV.class);
    }
}
