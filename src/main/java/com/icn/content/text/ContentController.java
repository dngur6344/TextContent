package com.icn.content.text;

import com.icn.content.auth.TokenConfig;
import com.icn.content.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

import static java.util.Calendar.HOUR;

@RequestMapping(value = "/content")
@RestController
public class ContentController {

    @Autowired
    TokenConfig tokenConfig;

    @Autowired
    ContentService contentService;

    @RequestMapping(value = "/insert",consumes = MediaType.APPLICATION_JSON_VALUE)
    public String insertContent(@RequestBody ContentEntity contentEntity, @RequestHeader(value = "Authorization")String token){
        UserEntity user = tokenConfig.getUserInformation(token);
        contentEntity.setUserEntity(user);
        contentEntity.setWriter(user.getKoreanname());
        try {
            contentService.insertContent(contentEntity);
            return "success";
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    @RequestMapping(value = "/findAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContentEntity> findAll(){
        List<ContentEntity> listing = contentService.findAll();
//        Calendar cal = Calendar.getInstance();
//        for(int i=0;i<listing.size();i++){//현재 시간대 맞추기(한국 시간대=UTC+9)
//            cal.setTime(listing.get(i).getDatetime());
//            cal.add(HOUR, 9);
//            listing.get(i).setDatetime(cal.getTime());
//        }
        return listing;
    }
    @RequestMapping(value="/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
    public String deleteByContentId(@RequestBody ContentEntity contentEntity){
        try {
            contentService.deleteByContentId(contentEntity.getContentId());
            return "success";
        }
        catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
}
