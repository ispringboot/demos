package net.ijiangtao.tech.ispringboot.alipay.starter.domain;

import net.ijiangtao.tech.ispringboot.alipay.core.domain.RefundDTO;

import javax.validation.constraints.NotNull;

/**
 *
 * @Desctiption:
 * @Date: 2019/01/25 15:43
 */
public class RefundForm extends RefundDTO {

    @NotNull
    @Override
    public String getRefundAmount() {
        return super.getRefundAmount();
    }
}

