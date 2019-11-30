package net.ijiangtao.tech.ispringboot.alipay.starter.domain;

import lombok.Data;
import net.ijiangtao.tech.ispringboot.alipay.core.domain.PagePayDTO;

import javax.validation.constraints.NotNull;

/**
 *
 * @Desctiption:
 * @Date: 2019/01/25 11:19
 */
@Data
public class PagePayFrom  extends PagePayDTO{

    @NotNull
    @Override
    public String getTotalAmount() {
        return super.getTotalAmount();
    }
}
