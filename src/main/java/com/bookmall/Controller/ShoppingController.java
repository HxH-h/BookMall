package com.bookmall.Controller;

import com.bookmall.Controller.ControllerPojo.GoodsDTO;
import com.bookmall.Controller.ControllerPojo.OrderDTO;
import com.bookmall.Controller.ControllerPojo.OrderVO;
import com.bookmall.Controller.ControllerPojo.PayDTO;
import com.bookmall.Controller.Response.Code;
import com.bookmall.Controller.Response.Message;
import com.bookmall.Controller.Response.Result;
import com.bookmall.CusException.*;
import com.bookmall.Dao.Mapper.OrderMapper;
import com.bookmall.Dao.Pojo.Book;
import com.bookmall.Service.Impl.ShopServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user/shopping")
@Tag(name = "用户购物接口")
public class ShoppingController {

    @Autowired
    ShopServiceImpl shopServiceImpl;

    @Autowired
    OrderMapper orderMapper;

    @Operation(summary = "添加到购物车")
    @PostMapping("/add")
    public Result addGoods(@RequestBody GoodsDTO goodsDTO) throws BookNotFoundException, BookShortageException, NumberIllegalException {
        shopServiceImpl.addGoods(goodsDTO);
        return new Result(Code.ADD_SHOPCART_SUCCESS, Message.ADD_SHOPCART_SUCCESS);
    }

    @Operation(summary = "清空购物车")
    @DeleteMapping("/clear")
    public Result removeGoods(){
        shopServiceImpl.clearShopCart();
        return new Result(Code.CLEAR_SHOPCART_SUCCESS,Message.CLEAR_SHOPCART_SUCCESS);
    }

    @Operation(summary = "查看购物车")
    @GetMapping("/show")
    public Result<List> showCart(){
        List<Book> books = shopServiceImpl.showCart();
        return new Result<List>(Code.GETINFO_SHOPCART_SUCCESS,Message.GETINFO_SHOPCART_SUCCESS,books);
    }
    @Operation(summary = "用户下单")
    @PostMapping("/order")
    public Result<OrderVO> order(@RequestBody OrderDTO orderDTO) throws ShopCartEmptyException, AddressNotFoundException, BookShortageException {

        OrderVO order = shopServiceImpl.order(orderDTO);
        return new Result<OrderVO>(Code.ORDER_SUCCESS,Message.ORDER_SUCCESS,order);

    }

    @Operation(summary = "用户支付")
    @PostMapping("/pay")
    public Result pay(@RequestBody PayDTO payDTO) throws OrderNotFoundException, IOException {
        shopServiceImpl.pay(payDTO);
        return new Result(Code.PAY_SUCCESS,Message.PAY_SUCCESS);
    }
}
