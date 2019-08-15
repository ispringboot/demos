package net.ijiangtao.tech.transaction.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.ijiangtao.tech.transaction.mybatis.enums.UserSexEnum;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 5443652439105133313L;

    private Long id;

    private String username;

    private String password;

    private UserSexEnum userSex;

    private String nickName;

    public UserEntity(String username, String password, UserSexEnum userSex) {
        this.username = username;
        this.password = password;
        this.userSex = userSex;
    }

    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public UserSexEnum getUserSex() {
        return this.userSex;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserSex(UserSexEnum userSex) {
        this.userSex = userSex;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserEntity)) return false;
        final UserEntity other = (UserEntity) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$userName = this.getUsername();
        final Object other$userName = other.getUsername();
        if (this$userName == null ? other$userName != null : !this$userName.equals(other$userName)) return false;
        final Object this$passWord = this.getPassword();
        final Object other$passWord = other.getPassword();
        if (this$passWord == null ? other$passWord != null : !this$passWord.equals(other$passWord)) return false;
        final Object this$userSex = this.getUserSex();
        final Object other$userSex = other.getUserSex();
        if (this$userSex == null ? other$userSex != null : !this$userSex.equals(other$userSex)) return false;
        final Object this$nickName = this.getNickName();
        final Object other$nickName = other.getNickName();
        if (this$nickName == null ? other$nickName != null : !this$nickName.equals(other$nickName)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserEntity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $userName = this.getUsername();
        result = result * PRIME + ($userName == null ? 43 : $userName.hashCode());
        final Object $passWord = this.getPassword();
        result = result * PRIME + ($passWord == null ? 43 : $passWord.hashCode());
        final Object $userSex = this.getUserSex();
        result = result * PRIME + ($userSex == null ? 43 : $userSex.hashCode());
        final Object $nickName = this.getNickName();
        result = result * PRIME + ($nickName == null ? 43 : $nickName.hashCode());
        return result;
    }

    public String toString() {
        return "UserEntity(id=" + this.getId() + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ", userSex=" + this.getUserSex() + ", nickName=" + this.getNickName() + ")";
    }
}