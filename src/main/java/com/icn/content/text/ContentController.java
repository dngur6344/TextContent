package com.icn.content.text;

import com.icn.content.auth.TokenConfig;
//import com.icn.content.user.UserEntity;
import com.icn.content.dto.ContentDTO;
import com.icn.content.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping(value = "/content")
@RestController
public class ContentController {

    @Autowired
    TokenConfig tokenConfig;

    @Autowired
    ContentService contentService;

    @RequestMapping(value = "/insert",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> insertContent(@RequestBody ContentDTO content, @RequestHeader(value = "Authorization")String token){

        contentService.insertContent(content, token);

        ResponseMessage responseMessage = ResponseMessage.builder()
                .responseTime(new Date())
                .data("Success")
                .build();

        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
    }

    @RequestMapping(value = "/findAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> findAll(){
        List<ContentDTO> dtoList = contentService.findAll();

        ResponseMessage responseMessage = ResponseMessage.builder()
                .responseTime(new Date())
                .data(dtoList)
                .build();

        return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.OK);
    }

    @RequestMapping(value="/delete",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> deleteByContentId(@RequestBody ContentDTO content){
        contentService.deleteByContentId(content.getContentId());

        ResponseMessage responseMessage = ResponseMessage.builder()
                .responseTime(new Date())
                .data("Success")
                .build();

        return new ResponseEntity<ResponseMessage>(responseMessage,HttpStatus.OK);
    }
}
