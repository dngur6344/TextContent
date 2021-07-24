package com.icn.content.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {
    @Autowired
    ContentRepository contentRepository;

    public String insertContent(ContentEntity contentEntity)throws Exception{
        contentRepository.save(contentEntity);
        return "success";
    }
    public List<ContentEntity> findAll(){
        List<ContentEntity> list = contentRepository.findAll();
        for(ContentEntity item : list){
            item.setUserEntity(null);
        }
        return list;
    }
}
