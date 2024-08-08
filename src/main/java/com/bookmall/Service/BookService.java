package com.bookmall.Service;

import com.bookmall.Controller.ControllerPojo.BookDTO;
import com.bookmall.Controller.Response.Result;
import com.bookmall.Dao.Pojo.Book;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {
    void addBook(BookDTO bookDTO, MultipartFile file);
    void updateBook(BookDTO bookDTO);
    void deleteBook(String uuid);
    List<Book> searchBookByname(String bookname);
    List<Book> searchBookBypage(int start,int pag);
    void uploadPicture(MultipartFile file, String uuid);
    void deletePicture(String uuid);
}
