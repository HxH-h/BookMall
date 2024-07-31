package com.bookmall.Controller;

import com.bookmall.Controller.ControllerPojo.BookDTO;
import com.bookmall.Controller.Response.Code;
import com.bookmall.Controller.Response.Message;
import com.bookmall.Controller.Response.Result;
import com.bookmall.Dao.Pojo.Book;
import com.bookmall.Service.Impl.BookServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "后台管理图书")
@RequestMapping("/admin/book")
public class BookController {
    @Autowired
    BookServiceImpl bookServiceImpl;

    @PostMapping("/add")
    @Operation(summary = "添加图书信息")
    public Result addBook(@RequestBody BookDTO bookDTO){
        bookServiceImpl.addBook(bookDTO);

        return new Result(Code.ADD_BOOK_SUCCESS, Message.ADD_BOOK_SUCCESS);
    }

    @PostMapping("/update")
    @Operation(summary = "更新图书信息")
    public Result updateBook(@RequestBody BookDTO bookDTO){
        bookServiceImpl.updateBook(bookDTO);

        return new Result(Code.UPDATE_BOOK_SUCCESS,Message.UPDATE_BOOK_SUCCESS);
    }

    @DeleteMapping("/{uuid}")
    @Operation(summary = "删除图书信息")
    public Result deleteBook(@PathVariable String uuid){
        bookServiceImpl.deleteBook(uuid);
        return new Result(Code.DEL_BOOK_SUCCESS,Message.DEL_BOOK_SUCCESS);
    }

    @GetMapping("/{name}")
    @Operation(summary = "根据书名查找图书信息")
    public Result<List> searchBookByname(@PathVariable String name){

        List<Book> books = bookServiceImpl.searchBookByname(name);
        return new Result<List>(Code.SEARCH_BOOK_SUCCESS,Message.SEARCH_BOOK_SUCCESS,books);
    }

    @GetMapping("/search/{start}/{page}")
    @Operation(summary = "分页查询图书信息")
    public Result searchBookBypage(@PathVariable int start,@PathVariable int page){

        List<Book> books = bookServiceImpl.searchBookBypage(start, page);
        return new Result<List>(Code.SEARCH_BOOK_SUCCESS,Message.SEARCH_BOOK_SUCCESS,books);
    }
}
