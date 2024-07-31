package com.bookmall.Service.Impl;

import com.bookmall.Controller.ControllerPojo.BookDTO;
import com.bookmall.Dao.Mapper.BookMapper;
import com.bookmall.Dao.Pojo.Book;
import com.bookmall.Service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;
    @Override
    @Transactional
    public void addBook(BookDTO bookDTO) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO,book);
        book.setUuid(UUID.randomUUID().toString());
        bookMapper.addBook(book);
    }

    @Override
    public void updateBook(BookDTO bookDTO) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO,book);
        bookMapper.updateBook(book);
    }

    @Override
    public void deleteBook(String uuid) {

        bookMapper.deleteBook(uuid);
    }

    @Override
    public List<Book> searchBookByname(String bookname) {
        return bookMapper.selectByname(bookname);
    }

    @Override
    public List<Book> searchBookBypage(int start, int pag) {
        return bookMapper.selectAll((start-1)*pag,pag);
    }
}
