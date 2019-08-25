package net.ijiangtao.tech.transaction.mybatis.manager;

import net.ijiangtao.tech.transaction.mybatis.entity.UserEntity;
import net.ijiangtao.tech.transaction.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * User
 *
 * @author ijiangtao
 * @create 2019-08-15 15:10
 **/
@Component
public class UserServiceB {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void test4(UserEntity user4) {
        userMapper.insert(user4);
        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test6(UserEntity user4) {
        userMapper.insert(user4);
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
}
