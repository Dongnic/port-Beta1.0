package com.im.port.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.im.port.vo.entity.UserEntity;

// CRUD 함수를 JpaRepository가 들고 있음
// @Repository라는 어노테이션이 없어도 Ioc된다. 이유는 상속받았기 때문에
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // findBy규칙 - > Username문법
    // select * from user where username = parameter의 username
    public UserEntity findByUsername(String username); // Jpa query methods
    public UserEntity findByEmail(String email); // Jpa query methods
    Optional<UserEntity> findByProviderAndEmail(String provider, String Email);
    @Query(value = " SELECT a.*                      "
                 + " FROM user a                     "
                 + " LEFT JOIN chatuser b            "
                 + " ON a.userid = b.userid          "
                 + " RIGHT JOIN onlineuser c         "
                 + " ON a.userid = c.userid          "
                 + " WHERE 1=1                       "
                 + " AND b.chatroomid = :chatroomid  ", nativeQuery = true)
    public List<UserEntity> findOnlineUserSQLBychatroomid(@Param(value = "chatroomid") Long chatroomid);
    @Query(value = " SELECT a.*                      "
                 + " FROM user a                     "
                 + " LEFT JOIN onlineuser c          "
                 + " ON a.userid = c.userid          "
                 + " LEFT JOIN chatuser b            "
                 + " ON a.userid = b.userid          "
                 + " WHERE 1=1                       "
                 + " AND c.userid IS NULL            "
                 + " AND b.chatroomid = :chatroomid  ", nativeQuery = true)
    public List<UserEntity> findOfflineUserSQLBychatroomid(@Param(value = "chatroomid") Long chatroomid);
    @Query(value = " SELECT a.*                         "
                 + " FROM user a                        "
                 + " WHERE userid                       "
                 + " NOT IN (                           "
                 + " 	SELECT DISTINCT userid          "
                 + " 	FROM chatuser                   "
                 + " 	WHERE chatroomid = :chatroomid  "
                 + " )                                  ", nativeQuery = true)
    public List<UserEntity> findOtherUserSQLBychatroomid(@Param(value = "chatroomid") Long chatroomid);
    @Query(value = " SELECT a.*                        "
                 + " FROM user a                       "
                 + " WHERE userid IN (                 "
                 + " 	SELECT b.userid                "
                 + " 	FROM chatroom b                "
<<<<<<< HEAD
                 + " 	WHERE b.otherid = :userid1     "
                 + "    AND b.roomtype LIKE 'DM'       "
                 + " UNION                             " 
                 + " 	SELECT c.otherid               "
                 + " 	FROM chatroom c                "
                 + " 	WHERE c.userid = :userid2      "
                 + "    AND c.roomtype LIKE 'DM'       "
                 + " )                                 ", nativeQuery = true)
    public List<UserEntity> DMUserSQLByuserid(@Param(value = "userid1") Long userid1, @Param(value = "userid2") Long userid2);
=======
                 + " 	WHERE b.otherid = :userid      "
                 + " 	UNION                          "
                 + " 	SELECT c.otherid               "
                 + " 	FROM chatroom c                "
                 + " 	WHERE c.userid = :userid       "
                 + " )                                 ", nativeQuery = true)
    public List<UserEntity> DMUserSQLByuserid(@Param(value = "userid") Long userid);
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
}
