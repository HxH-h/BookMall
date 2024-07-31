package com.bookmall.Dao.Mapper;

import com.bookmall.Dao.Pojo.Book;
import com.bookmall.Service.AutoFill;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface BookMapper {

    @AutoFill(AutoFill.methodName.ADD)
    void addBook(Book book);

    @AutoFill(AutoFill.methodName.UPDATE)
    void updateBook(Book book);

    void deleteBook(String uuid);

    List<Book> selectByname(String name);

    List<Book> selectAll(int start,int page);

}
