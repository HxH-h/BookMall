package com.bookmall.Service;

import com.bookmall.Controller.ControllerPojo.GoodsDTO;
import com.bookmall.CusException.BookNotFoundException;
import com.bookmall.Dao.Pojo.Book;

import java.util.List;

public interface ShopService {
    public void addGoods(GoodsDTO goodsDTO) throws BookNotFoundException;
    public void clearShopCart();
    public List<Book> showCart();
}
