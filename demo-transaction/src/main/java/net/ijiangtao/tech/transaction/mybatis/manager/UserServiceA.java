package net.ijiangtao.tech.transaction.mybatis.manager;

import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.transaction.mybatis.entity.UserEntity;
import net.ijiangtao.tech.transaction.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * User
 *
 * @author ijiangtao
 * @create 2019-08-15 15:10
 **/
@Component
@Slf4j
public class UserServiceA {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserServiceB userServiceB;

    @Transactional
    public void test3(UserEntity user3, UserEntity user4) {
        userMapper.insert(user3);
        try {
            userServiceB.test4(user4);
        } catch (Exception e) {
            log.info("isNewTransaction={}", TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
            e.printStackTrace();
        }
    }

    @Transactional
    public void test32(UserEntity user3, UserEntity user4) {
        userMapper.insert(user3);
        try {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    userServiceB.test4(user4);
                }
            };
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void test5(UserEntity user5, UserEntity user6) {
        userMapper.insert(user5);
        try {
            userServiceB.test6(user6);
        } catch (Exception e) {
            log.info("isRollbackOnly={}", TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
            e.printStackTrace();
        }
    }

}
