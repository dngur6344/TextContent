package com.icn.content.text;

import com.icn.content.auth.TokenConfig;
import com.icn.content.dto.ContentDTO;
import com.icn.content.exception.DeleteContentException;
import com.icn.content.exception.RegisterContentException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void insertContent(ContentDTO content, String token){
        ContentEntity contentEntity=modelMapper.map(content,ContentEntity.class);

        String username = tokenConfig.getUserName(token);
        String koreanName = tokenConfig.getKoreanName(token);

        contentEntity.setUsername(username);
        contentEntity.setWriter(koreanName);

        try {
            contentRepository.save(contentEntity);
        }
        catch(Exception e){
            e.printStackTrace();
            throw new RegisterContentException();
        }
    }
    public List<ContentDTO> findAll(){
        List<ContentEntity> list = contentRepository.findAll();
        List<ContentDTO> listDTO = new ArrayList<ContentDTO>();
        for(ContentEntity item : list){
            listDTO.add(modelMapper.map(item,ContentDTO.class));
        }
        return listDTO;
    }
    public void deleteByContentId(Integer id) {
        try {
            contentRepository.deleteById(id);
        }
        catch(RuntimeException e){
            e.printStackTrace();
            throw new DeleteContentException();
        }
    }
}
