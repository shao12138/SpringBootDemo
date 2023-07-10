package com.nudt.elasticsearch.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Document(indexName = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private int id;
    @Field(type = FieldType.Text)
    private String bookName;
    @Field(type = FieldType.Text)
    private String author;
    @Field(type = FieldType.Float)
    private float price;
    @Field(type = FieldType.Integer)
    private int page;
    @Field(type = FieldType.Text)
    private String category;
}