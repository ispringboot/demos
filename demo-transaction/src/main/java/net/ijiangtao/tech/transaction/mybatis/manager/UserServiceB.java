package net.ijiangtao.tech.transaction.mybatis.manager;

import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.transaction.mybatis.entity.UserEntity;
import net.ijiangtao.tech.transaction.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
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
public class UserServiceB {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void notice(UserEntity user4) {
        userMapper.insert(user4);
        log.info("isNewTransaction={}", TransactionAspectSupport.currentTransactionStatus().isNewTransaction());
        throw new RuntimeException();
    }

    @Transactional
    public void test4(UserEntity user4) {
        userMapper.insert(user4);
        log.info("isNewTransaction={}", TransactionAspectSupport.currentTransactionStatus().isNewTransaction());
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test6(UserEntity user6) {
        userMapper.insert(user6);
        log.info("isNewTransaction={}", TransactionAspectSupport.currentTransactionStatus().isNewTransaction());
        throw new RuntimeException();
    }

    @Transactional(noRollbackFor = {ExceptionA.class}, rollbackFor = {ExceptionB.class})
    public void test7(UserEntity user7, Exception e) throws Exception {
        userMapper.insert(user7);
        throw e;
    }

    @Transactional(readOnly = true)
    //@Transactional
    public void test8(Long id) {
        UserEntity u = userMapper.getOne(id);
        System.out.println("done" + u);
    }

    //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    //@Transactional
    public void test9(UserEntity user9) {
        Long uid = userMapper.insert(user9);

        System.out.println("user9-id=" + uid);
    }
}
