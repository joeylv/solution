package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
public class DatabaseService {
    @Autowired
    JdbcTemplate jdbcTemplate;

//    AtomicInteger count = new AtomicInteger(0);

    /**
     * 秒杀 购买
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean buy(String goodsCode, String userId) {

        String sql = "update tb_miaosha set  goods_nums = goods_nums -1 where goods_code = '" + goodsCode + "' and goods_nums -1 >=0";

        int count = jdbcTemplate.update(sql);
        if (count != 1) {
            return false;
        }
        String insertSql = "insert into tb_records(goods_code, user_id) values('" + goodsCode + "','" + userId + "')";
        int insertCount = jdbcTemplate.update(insertSql);
        if (insertCount != 1) {
            return false;
        }
        return true;
    }

    /**
     * 抢票 查询
     */

    public String query(String ticketSeq) {
        String sql = "select ticket_stock from tb_ticket where ticket_seq = '" + ticketSeq + "'";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql);
        return result.get("ticket_stock").toString();
    }

}
