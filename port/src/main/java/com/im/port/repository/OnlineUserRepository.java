package com.im.port.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.im.port.vo.entity.OnlineUserEntity;

public interface OnlineUserRepository extends JpaRepository<OnlineUserEntity, Long> {
    
    public OnlineUserEntity findByUserid(String userid); // Jpa query methods
    
    // 영속성 컨텍스트와 DB 간의 동기화 문제가 발생할 수 있어 동기화를 해주는 작업
    @Transactional
    // 영속성 컨텍스트를 거치지 않고 바로 DB에 반영
    @Modifying
    @Query(value = "delete from onlineuser where sessionid = :sessionid", nativeQuery = true)
    public void deleteSQLBySessionid(@Param("sessionid") String sessionid);
}
