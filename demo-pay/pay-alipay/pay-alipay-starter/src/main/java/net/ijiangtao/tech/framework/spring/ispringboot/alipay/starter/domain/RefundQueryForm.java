package net.ijiangtao.tech.framework.spring.ispringboot.alipay.starter.domain;

import net.ijiangtao.tech.framework.spring.ispringboot.alipay.core.domain.RefundQueryDTO;

import javax.validation.constraints.NotNull;

/**
 *
 * @Desctiption:
 * @Date: 2019/01/25 15:45
 */
public class RefundQueryForm extends RefundQueryDTO {


    @NotNull
    @Override
    public String getOutRequestNo() {
        return super.getOutRequestNo();
    }
}
