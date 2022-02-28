package com.esliceu.forum.controllers;

import com.esliceu.forum.models.Categoria;
import com.esliceu.forum.models.Cuenta;
import com.esliceu.forum.models.Reply;
import com.esliceu.forum.models.Topic;
import com.esliceu.forum.services.CategoryServiceImpl;
import com.esliceu.forum.services.ReplyServiceImpl;
import com.esliceu.forum.services.TopicServiceImpl;
import com.esliceu.forum.services.UserServiceImpl;
import com.esliceu.forum.utils.JwtTokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.Instant;
import java.util.*;


@RestController
public class CategoriesController {

    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    TopicServiceImpl topicService;

    @Autowired
    ReplyServiceImpl replyService;


    @GetMapping("/categories")
    public List <Categoria> showAllCategories(){
        return categoryService.findAll();
    }

    @PreAuthorize("hasAnyRole('User','Moderator','Admin')")
    @PostMapping("/categories")
    public Map <String,Object> createCategory(@RequestBody Map<String,Object> data){
        String title = (String) data.get("title");

        if(title.contains("/")){
            data.put("title", title.replace("/",""));
        }
        data.put("slug",title);
        Categoria categoria = new Categoria();
        categoria.setTitle((String) data.get("title"));
        categoria.setDescription((String) data.get("description"));
        categoria.setSlug((String) data.get("title"));

        categoryService.createCategory(categoria);
        return data;
    }

    @GetMapping("/categories/{title}")
    public String getTopics(@PathVariable String title) throws JsonProcessingException {

        Categoria categoria = categoryService.findByTitle(title);

        return new ObjectMapper().writeValueAsString(categoria);
    }

    @PreAuthorize("hasAnyRole('User','Moderator','Admin')")
    @PutMapping ("/categories/{title}")
    public Map <String,Object> updateCategory(@PathVariable String title, @RequestBody Map<String,Object> newData){
        Categoria categoria = categoryService.findByTitle(title);
        categoria.setTitle((String) newData.get("title"));
        categoria.setDescription((String) newData.get("description"));
        categoria.setSlug((String) newData.get("title"));

        categoryService.createCategory(categoria);
        return newData;
    }

    @PreAuthorize("hasAnyRole('Moderator','Admin')")
    @DeleteMapping("/categories/{title}")
    public String deleteCategory(@PathVariable String title){
        categoryService.deleteCategory(title);
        return "ok";
    }

                                   /* TOPICS */

    @GetMapping("/categories/{title}/topics")
    public List<Topic> getAllTopics(@PathVariable String title) {

        Categoria categoria = categoryService.findByTitle(title);

        return  topicService.getAllByIdCategoria(categoria.getId());
    }

    @PreAuthorize("hasAnyRole('User','Moderator','Admin')")
    @PostMapping("/topics")
    public Map<String,Object> CreateTopic(@RequestBody Map<String,Object> t, @RequestHeader("Authorization") String token)  {
        String user = jwtTokenUtil.getUsername(token.replace("Bearer ", ""));
        Cuenta cuenta = userService.getUser(user);

        Topic topic = new Topic();
        topic.setTitle((String) t.get("title"));
        topic.setContent((String) t.get("content"));
        topic.setCreatedAt(Date.from(Instant.now()));
        topic.setCuenta(cuenta);
        Categoria categoria = categoryService.findByTitle(topic.getTitle());
        topic.setCategoria(categoria);
        topicService.CreateTopic(topic);

        Map<String,Object> topicResult = new ObjectMapper().convertValue(t,Map.class);
        topicResult.put("_id", String.valueOf(topic.getId()));
        return topicResult;
    }

    @GetMapping("/topics/{idtopic}")
    public Map<String,Object> getTopic(@PathVariable String idtopic) throws NumberFormatException{

        Topic t = topicService.getTopicById(Integer.parseInt(idtopic));
        Map<String,Object> topic = new HashMap <>();
        topic.put("id",t.getId());
        topic.put("content",t.getContent());
        topic.put("createdAt",t.getCreatedAt());
        topic.put("categoria",t.getCategoria());
        topic.put("cuenta",t.getCuenta());
        topic.put("title",t.getTitle());
        topic.put("_id",t.getId());

        List<Reply> Allreplies = List.copyOf(t.getReplies());
        List<Map<String,Object>> replies = new ArrayList<>();
        for (Reply r : Allreplies) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", r.getId());
            map.put("content", r.getContent());
            map.put("createdAt", r.getCreatedAt());
            map.put("topic", r.getTopic().getId());
            map.put("cuenta", r.getCuenta());
            map.put("_id", r.getId());
            replies.add(map);
        }
        topic.put("replies",replies);

        return topic;
    }

    @PutMapping("/topics/{id}")
    @PreAuthorize("hasAnyRole('User','Moderator','Admin')")
    public String updateTopic(@PathVariable String id,@RequestBody Map<String,Object> topicValues){

        Topic topic = topicService.getTopicById(Integer.parseInt(id));
        topic.setTitle((String) topicValues.get("title"));
        topic.setContent((String) topicValues.get("content"));
        Categoria categoria = categoryService.findByTitle((String) topicValues.get("title"));
        topic.setCategoria(categoria);
        topicService.CreateTopic(topic);

        return "Ok";
    }

                                         /* REPLY */

    @PreAuthorize("hasAnyRole('User','Moderator','Admin')")
    @PostMapping("/topics/{id}/replies")
    public Map<String, Object> createReply(@RequestHeader("Authorization") String token, @PathVariable String id, @RequestBody Map data){
        String user = jwtTokenUtil.getUsername(token.replace("Bearer ", ""));
        Cuenta cuenta = userService.getUser(user);
        String content = (String) data.get("content");

        Topic topic = topicService.getTopicById(Integer.parseInt(id));

        Reply reply = new Reply();
        reply.setTopic(topic);
        reply.setContent(content);
        reply.setCuenta(cuenta);
        reply.setCreatedAt(Date.from(Instant.now()));
        replyService.CreateReply(reply);

        Map<String,Object> replyContent = new HashMap<>();
        replyContent.put("id",reply.getId());
        replyContent.put("content",reply.getContent());
        replyContent.put("createdAt",reply.getCreatedAt());
        replyContent.put("cuenta",reply.getCuenta());
        replyContent.put("_id",reply.getId());

        return replyContent;
    }
    @PreAuthorize("hasAnyRole('Moderator','Admin')")
    @PutMapping("/topics/{idtopic}/replies/{idreply}")
    public String updateReply(@PathVariable String idreply,@RequestBody Map ReplyUP){

        Reply reply = replyService.getReplyById(Integer.parseInt(idreply));
        reply.setContent((String) ReplyUP.get("content"));
        return "ok";
    }

    @PreAuthorize("hasAnyRole('Moderator','Admin')")
    @DeleteMapping("/topics/{idtopic}/replies/{idreply}")
    public String deleteReply(@PathVariable String idreply){

        Reply reply = replyService.getReplyById(Integer.parseInt(idreply));
        replyService.deleteReply(reply);
        return "ok";
    }

}
