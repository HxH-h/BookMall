package com.bookmall.Service.Impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.bookmall.Controller.ControllerPojo.BookDTO;
import com.bookmall.Dao.Mapper.BookMapper;
import com.bookmall.Dao.Pojo.Book;
import com.bookmall.Service.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    AmazonS3 amazonS3;

    @Override
    @Transactional
    public void addBook(BookDTO bookDTO,MultipartFile file) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO,book);
        String uuid = UUID.randomUUID().toString();
        book.setUuid(uuid);
        bookMapper.addBook(book);
        if (file == null){
            return ;
        }
        uploadPicture(file,uuid);
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
        deletePicture(uuid);
    }

    @Override
    public List<Book> searchBookByname(String bookname) {
        return bookMapper.selectByname(bookname);
    }

    @Override
    public List<Book> searchBookBypage(int start, int pag) {
        return bookMapper.selectAll((start-1)*pag,pag);
    }

    public int getSum(){
        return bookMapper.getBookSum();
    }

    @Override
    public void uploadPicture(MultipartFile file,String uuid) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        String name = file.getOriginalFilename();
        String suffix = name.substring(name.indexOf('.'),name.length());
        System.out.println(file.getName() +  "   " +file.getOriginalFilename());
        String key = "book/" + uuid + suffix;
        try {
            PutObjectResult hxh = amazonS3.putObject(new PutObjectRequest("hxh", key, file.getInputStream(), objectMetadata));
            System.out.println(hxh);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePicture(String uuid) {
        String fileName = "book/" + uuid + ".jpg";
        System.out.println(fileName);
        amazonS3.deleteObject(new DeleteObjectRequest("hxh", fileName));
    }
}
