package com.meifawu.meifawu.dao;

import com.meifawu.meifawu.entity.BarberState;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 孟子弘
 * @since 2021-12-28
 */
public interface BarberStateMapper extends BaseMapper<BarberState> {
    public List<BarberState> findAllBarberState();
    public List<BarberState> findAllBarberStateByDate(LocalDate theDate);
    public int updateStateByBarberName(BarberState barberState);

}
