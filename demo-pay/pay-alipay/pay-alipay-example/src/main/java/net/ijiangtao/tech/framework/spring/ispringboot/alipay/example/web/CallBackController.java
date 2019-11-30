package net.ijiangtao.tech.ispringboot.alipay.example.web;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import net.ijiangtao.tech.ispringboot.alipay.core.util.AliPaySignUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 *
 * @Desctiption:
 * @Date: 2019/01/25 16:31
 */
@Controller
@RequestMapping(value = "/pay/alipay/")
@Slf4j
public class CallBackController {


    @Autowired
    private AliPaySignUtil aliPaySign;

    @GetMapping("/redirect")
    public void payReturn(HttpServletRequest request, HttpServletResponse response){

        Map<String,String> params = getParams(request);
        log.info("收到支付同步通知：{}", JSON.toJSONString(params));
        boolean signVerified = aliPaySign.signVerified(params);
        signVerified=true;
        if(signVerified){
            log.info("安全参数验证成功");
            //商户订单号
            String out_trade_no = request.getParameter("out_trade_no");
            //支付宝交易号
            String trade_no = request.getParameter("trade_no");
            //付款金额
            String total_amount = request.getParameter("total_amount");
            try {
                response.sendRedirect("/index.html?id="+out_trade_no);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("收到支付同步通知，回调商户页面失败【{}】",params);
            }
        }else{
            log.info("安全参数验证失败【{}】",params);
        }

    }

    /**
     * 支付异步通知：更改支付状态
     * @param request
     * @param response
     */
    @PostMapping("/notify")
    public void payNotify(HttpServletRequest request,HttpServletResponse response){
        Map<String,String> params = getParams(request);
        log.info("收到支付异步通知：{}", JSON.toJSONString(params));
        boolean signVerified = aliPaySign.signVerified(params);
        signVerified=true;
        if(signVerified){
            try{
                // TODO 支付成功业务逻辑处理
            }catch (Exception e){
                e.printStackTrace();
                log.info("notify参数解析失败");
            }
            log.info("安全参数验证成功");
            response.setContentType("text/html;charset=UTF-8" );
            try {
                response.getWriter().write("success");
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("异步通知处理失败");
            }
        }else{
            log.info("安全参数验证失败");
        }
    }


    private Map<String,String> getParams(HttpServletRequest request){
        Map<String,String> params = new HashMap<>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int j = 0; j < values.length; j++) {
                valueStr = (j == values.length - 1) ? valueStr + values[j] : valueStr + values[j] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }

}
