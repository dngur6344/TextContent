package com.icn.content.text;

import com.icn.content.auth.TokenConfig;
import com.icn.content.user.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContentService {
    @Autowired
    ContentRepository contentRepository;
    @Autowired
    TokenConfig tokenConfig;
    @Autowired
    ModelMapper modelMapper;

    public String insertContent(ContentDTO content,String token){
        ContentEntity contentEntity=modelMapper.map(content,ContentEntity.class);
        UserEntity user = tokenConfig.getUserInformation(token);
        contentEntity.setUserid(user);
        contentEntity.setWriter(user.getKoreanname());
        try {
            contentRepository.save(contentEntity);
        }
        catch(Exception e){
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }
    public List<ContentDTO> findAll(){
        List<ContentEntity> list = contentRepository.findAll();
        List<ContentDTO> listDTO = new ArrayList<ContentDTO>();
        for(ContentEntity item : list){
            listDTO.add(modelMapper.map(item,ContentDTO.class));
        }
        return listDTO;
    }
    public void deleteByContentId(Integer id)throws Exception{
        contentRepository.deleteById(id);
    }
}
