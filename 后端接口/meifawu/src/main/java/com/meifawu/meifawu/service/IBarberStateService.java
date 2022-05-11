package com.meifawu.meifawu.service;

import com.meifawu.meifawu.entity.BarberState;
import com.baomidou.mybatisplus.extension.service.IService;
import com.meifawu.meifawu.entity.User;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 孟子弘
 * @since 2021-12-28
 */
public interface IBarberStateService extends IService<BarberState> {
    public List<BarberState> findAllBarberState();
    public List<BarberState> findAllBarberStateByDate(LocalDate theDate);
    public int updateStateByBarberName(BarberState barberState);

}
