package com.example.demo.EsDemo;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class EsDemo {

    @Autowired
    private TransportClient client;

    @GetMapping("/get/people/man")
    @ResponseBody
    public ResponseEntity get(@RequestParam(name = "id" ,defaultValue = "") String id) {

        if (id.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        GetResponse result = this.client.prepareGet("people", "man", id)
                .get();

        if (!result.isExists()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return  new ResponseEntity(result.getSource() , HttpStatus.OK);
    }

    @PostMapping("/add/people/man")
    @ResponseBody
    public ResponseEntity add(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "country") String country,
            @RequestParam(name = "age") int age,
            @RequestParam(name = "date")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            Date date
    ){

        try {

            XContentBuilder content = XContentFactory.jsonBuilder()
                   .startObject()
                   .field("name",name)
                   .field("country",country)
                   .field("age",age)
                   .field("date",date)
                   .endObject();

           IndexResponse result = this.client.prepareIndex("people","man")
                   .setSource(content)
                   .get();
            return new ResponseEntity(result.getId(),HttpStatus.OK);
       }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
       }

    }

    @DeleteMapping("/delete/people/man")
    @ResponseBody
    public ResponseEntity delete(@RequestParam(name="id",defaultValue = "") String id){
        if (id.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        DeleteResponse result = this.client.prepareDelete("people","man",id).get();

        return  new ResponseEntity(result.getResult().toString() , HttpStatus.OK);

    }

    @PutMapping("/update/people/man")
    @ResponseBody
    public ResponseEntity update(
            @RequestParam(name = "id") String id,
            @RequestParam(name = "name") String name
    ){

        UpdateRequest update = new UpdateRequest("people","man",id);

        try{

            XContentBuilder builder = XContentFactory.jsonBuilder().startObject();

            if(name != null){
                builder.field("name",name);
            }
            builder.endObject();
            update.doc(builder);
        }catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try{
            UpdateResponse result = this.client.update(update).get();
            return new ResponseEntity( result.getResult().toString(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/query/people/man")
    @ResponseBody
    public ResponseEntity query(
        @RequestParam(name = "name",required = false) String name,
        @RequestParam(name = "gt_age",defaultValue = "0") int gt_age,
        @RequestParam(name = "lt_age",required = false) Integer lt_age
    ){

        BoolQueryBuilder booleanQuery = QueryBuilders.boolQuery();

        if(name != null){
            booleanQuery.must(QueryBuilders.matchQuery("name",name));
        }

        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age")
                .from(gt_age);
        if(lt_age != null && lt_age >0){
            rangeQuery.to(lt_age);
        }

        booleanQuery.filter(rangeQuery);
        SearchRequestBuilder builder = this.client.prepareSearch("people")
                .setTypes("man")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(booleanQuery)
                .setFrom(0)
                .setSize(10);

        System.out.println(builder);
        SearchResponse response = builder.get();

        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        SearchHits hit = response.getHits();

        hit.forEach( p -> result.add(p.getSourceAsMap()));

        return  new ResponseEntity(result,HttpStatus.OK);
    }

}
