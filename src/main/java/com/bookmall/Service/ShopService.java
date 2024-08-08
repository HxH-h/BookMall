package com.bookmall.Service;

import com.bookmall.Controller.ControllerPojo.GoodsDTO;
import com.bookmall.Controller.ControllerPojo.OrderDTO;
import com.bookmall.Controller.ControllerPojo.OrderVO;
import com.bookmall.Controller.ControllerPojo.PayDTO;
import com.bookmall.CusException.*;
import com.bookmall.Dao.Pojo.Book;

import java.io.IOException;
import java.util.List;

public interface ShopService {
     void addGoods(GoodsDTO goodsDTO) throws BookNotFoundException, BookShortageException, NumberIllegalException;
     void clearShopCart();
     List<Book> showCart();

     OrderVO order(OrderDTO orderDTO) throws ShopCartEmptyException, AddressNotFoundException, BookShortageException;
     void pay(PayDTO payDTO) throws OrderNotFoundException, IOException;

}
