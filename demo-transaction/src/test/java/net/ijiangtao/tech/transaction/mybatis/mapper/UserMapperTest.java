package net.ijiangtao.tech.transaction.mybatis.mapper;

import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.transaction.mybatis.entity.UserEntity;
import net.ijiangtao.tech.transaction.mybatis.enums.UserSexEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() throws Exception {
        Long aid = userMapper.insert(new UserEntity("aa", "a123456", UserSexEnum.MAN));
        Long bid = userMapper.insert(new UserEntity("bb", "b123456", UserSexEnum.WOMAN));
        Long cid = userMapper.insert(new UserEntity("cc", "b123456", UserSexEnum.WOMAN));
        log.info("==================================={},{},{}", aid, bid, cid);
    }

    @Test
    public void testQuery() throws Exception {
        List<UserEntity> users = userMapper.getAll();
        if (users == null || users.size() == 0) {
            log.info("is null");
        } else {
            log.info(users.toString());
        }
    }

}