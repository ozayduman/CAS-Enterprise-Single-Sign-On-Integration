package com.ozayduman.casclient.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by ozayd on 19.09.2017.
 */
@Repository
/*
 * Life cycle is not managed by http session
 */
public class CitizenRepositoryImpl implements CitizenRepository {
    private static final String KEY = "Citizen";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOps;

    @Autowired
    public CitizenRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOps = redisTemplate.opsForHash();
    }

    @Override
    public void saveCitizen(Citizen citizen) {
        hashOps.put(KEY,citizen.getId(),citizen);
    }
    @Override
    public void updateCitizen(Citizen citizen) {
        hashOps.put(KEY, citizen.getId(), citizen);
    }
    @Override
    public Citizen findCitizen(String id){
        return (Citizen) hashOps.get(KEY,id);
    }
    @Override
    public Map<Object, Object> findAllCitizens() {
        return hashOps.entries(KEY);
    }
    @Override
    public void deleteCitizen(String id) {
        hashOps.delete(KEY, id);
    }

}
