package com.graduationproject.repositories;

import com.graduationproject.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByFromAndTo(String from, String to);
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
    List<Order> findAllByUserId(@Param("userId") Integer userId);
    @Query(value = "SELECT * FROM orders WHERE user_id = :userId AND order_status = 'NOT_ACTIVE'", nativeQuery = true)
    List<Order> findNotActiveOrders(@Param("userId") Integer userId);
    @Query(value = "SELECT * FROM orders WHERE commuter_id = :commuterId AND order_status != 'NOT_ACTIVE'", nativeQuery = true)
    List<Order> findCommuterOrders(@Param("commuterId") Integer commuterId);
}