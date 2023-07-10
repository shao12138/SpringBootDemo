package com.nudt.mybatis.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BookMapper {
    @Insert("insert into book(name) values(#{name})")
    void addBook(String name);
}
