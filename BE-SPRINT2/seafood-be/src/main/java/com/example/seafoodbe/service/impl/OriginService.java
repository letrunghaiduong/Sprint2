package com.example.seafoodbe.service.impl;

import com.example.seafoodbe.model.Origin;
import com.example.seafoodbe.repository.IOriginRepository;
import com.example.seafoodbe.service.IOriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OriginService implements IOriginService {
    @Autowired
    private IOriginRepository originRepository;

    @Override
    public List<Origin> getAll() {
        return originRepository.findAll();
    }
}
