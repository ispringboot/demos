package net.ijiangtao.tech.framework.spring.ispringboot.alipay.starter.web;

import com.alipay.api.response.*;
import net.ijiangtao.tech.framework.spring.ispringboot.alipay.starter.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import net.ijiangtao.tech.framework.spring.ispringboot.alipay.core.api.PCPagePay;
import net.ijiangtao.tech.framework.spring.ispringboot.alipay.starter.domain.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @Desctiption:
 * @Date: 2019/01/25 11:10
 */
@Controller
@RequestMapping(value = "/alipay/pc/page")
public class AliPCPagePayController {


    @Autowired
    private PCPagePay pcPagePay;

    @GetMapping(value = "/pay")
    public void pagePay(@Validated PagePayFrom pagePayFrom, HttpServletResponse response){
        AlipayTradePagePayResponse payResponse = pcPagePay.pay(pagePayFrom);
        try {
            if(payResponse.isSuccess()){
                response.setContentType("text/html;charset=UTF-8" );
                response.getWriter().write(payResponse.getBody());
                response.getWriter().flush();
                response.getWriter().close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/query")
    public ResponseEntity query(PagePayQueryFrom pagePayQueryFrom){
        AlipayTradeQueryResponse response = pcPagePay.query(pagePayQueryFrom);
        return ResponseEntity.ok().body(response.getBody());

    }

    @PostMapping(value = "/refund")
    public ResponseEntity refund(@Validated RefundForm refundForm){
        AlipayTradeRefundResponse response = pcPagePay.refund(refundForm);
        return ResponseEntity.ok().body(response.getBody());
    }

    @GetMapping(value = "/refund/query")
    public ResponseEntity refundQuery(@Validated RefundQueryForm refundQueryForm){
        AlipayTradeFastpayRefundQueryResponse response = pcPagePay.refundQuery(refundQueryForm);
        return ResponseEntity.ok().body(response.getBody());
    }


    @PostMapping(value = "/close")
    public ResponseEntity close(OrderCloseFrom orderCloseFrom){
        AlipayTradeCloseResponse response = pcPagePay.close(orderCloseFrom);
        return ResponseEntity.ok().body(response.getBody());
    }




}
