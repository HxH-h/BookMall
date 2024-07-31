package com.bookmall.Service;

import com.bookmall.Controller.ControllerPojo.BookDTO;
import com.bookmall.Controller.Response.Result;
import com.bookmall.Dao.Pojo.Book;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookService {
    void addBook(BookDTO bookDTO);
    void updateBook(BookDTO bookDTO);
    void deleteBook(String uuid);
    List<Book> searchBookByname(String bookname);
    List<Book> searchBookBypage(int start,int pag);
}
