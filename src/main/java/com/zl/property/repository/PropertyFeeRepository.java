package com.zl.property.repository;

import com.zl.property.model.hib.server.PropertyFee;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
@ComponentScan
public interface PropertyFeeRepository  extends CrudRepository<PropertyFee, Long> {
    //自定义sql
    @Query(value = "select p.* from p_server_property_fee p where  p.room_id=?1 and p.pay_state =?2 limit ?3 , ?4", nativeQuery = true)
    @Modifying
    List<PropertyFee> findPropertyFee(String roomId, String payState,int pageIndex,int pageSize);

}