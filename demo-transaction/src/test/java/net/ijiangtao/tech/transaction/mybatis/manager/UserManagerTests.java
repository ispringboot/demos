package net.ijiangtao.tech.transaction.mybatis.manager;

import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.transaction.mybatis.entity.UserEntity;
import net.ijiangtao.tech.transaction.mybatis.enums.UserSexEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * UserManager tests
 *
 * @author ijiangtao
 * @create 2019-08-25 21:22
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserManagerTests {

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserServiceA userServiceA;

    @Autowired
    private UserServiceB userServiceB;


    @Test
    public void test1() {
        UserEntity u = new UserEntity("test1", "a123456", UserSexEnum.MAN);
        userManager.test1(u);
    }

    @Test
    public void test2() {
        UserEntity u = new UserEntity("test2", "a123456", UserSexEnum.MAN);
        userManager.test2(u);
    }

    @Test
    public void test3() {
        UserEntity u3 = new UserEntity("test3", "a123456", UserSexEnum.MAN);
        UserEntity u4 = new UserEntity("test4", "a123456", UserSexEnum.MAN);
        userManager.test3(u3, u4);
    }

    @Test
    public void test32() {
        UserEntity u3 = new UserEntity("test3", "a123456", UserSexEnum.MAN);
        UserEntity u4 = new UserEntity("test4", "a123456", UserSexEnum.MAN);
        userServiceA.test32(u3, u4);
    }

    //事务提交失败
    //org.springframework.transaction.UnexpectedRollbackException: Transaction rolled back because it has been marked as rollback-only
    @Test
    public void test4() {
        UserEntity u3 = new UserEntity("test3", "a123456", UserSexEnum.MAN);
        UserEntity u4 = new UserEntity("test4", "a123456", UserSexEnum.MAN);
        userServiceA.test3(u3, u4);
    }

    //第一个事务提交失败成功，第二个事务提交失败
    @Test
    public void test5() {
        UserEntity u5 = new UserEntity("test5", "a123456", UserSexEnum.MAN);
        UserEntity u6 = new UserEntity("test6", "a123456", UserSexEnum.MAN);
        userServiceA.test5(u5, u6);
    }

    //第一个事务提交失败成功，第二个事务提交失败
    @Test
    public void test7() {
        UserEntity u71 = new UserEntity("71", "a123456", UserSexEnum.MAN);
        UserEntity u72 = new UserEntity("72", "a123456", UserSexEnum.MAN);
        try {
            userServiceB.test7(u71, new ExceptionA());
        } catch (Exception e) {
            log.error("", e);
        }
        try {
            userServiceB.test7(u72, new ExceptionB());
        } catch (Exception e) {
            log.error("", e);
        }
    }

    @Test
    public void test8() {
        userServiceB.test8(42L);
    }


}
