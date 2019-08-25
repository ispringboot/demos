package net.ijiangtao.tech.transaction.mybatis.manager;

import net.ijiangtao.tech.transaction.mybatis.entity.UserEntity;
import net.ijiangtao.tech.transaction.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserManager
 *
 * @author ijiangtao
 * @create 2019-08-15 15:10
 **/
@Service
public class UserManagerImpl implements UserManager {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserEntity> getAll() {
        return userMapper.getAll();
    }

    @Override
    public UserEntity getOne(Long id) {
        return userMapper.getOne(id);
    }

    @Override
    public Long insert(UserEntity user) {
        return userMapper.insert(user);
    }

    @Override
    public void update(UserEntity user) {
        userMapper.update(user);
    }

    @Override
    public void delete(Long id) {
        userMapper.delete(id);
    }

    @Transactional
    @Override
    public void batchInsert(List<UserEntity> users) {
        for (UserEntity u : users) {
            userMapper.insert(u);
        }
    }

    //--------------------------------------------------------------
    //事务回滚
    @Transactional
    @Override
    public void test1(UserEntity user) {
        userMapper.insert(user);
        throw new RuntimeException();
    }

    //--------------------------------------------------------------
    //如下事务不会回滚
    @Override
    public void test2(UserEntity user) {
        test22(user);
    }

    @Transactional
    private void test22(UserEntity user) {
        userMapper.insert(user);
        throw new RuntimeException();
    }

    //--------------------------------------------------------------
    //如下事务不会回滚
    @Transactional
    public void test3(UserEntity user3, UserEntity user4) {
        userMapper.insert(user3);
        try {
            test4(user4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void test4(UserEntity user4) {
        userMapper.insert(user4);
        throw new RuntimeException();
    }


}
