package com.meifawu.meifawu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meifawu.meifawu.dao.BarberStateMapper;
import com.meifawu.meifawu.entity.BarberState;
import com.meifawu.meifawu.service.IBarberStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 孟子弘
 * @since 2021-12-28
 */
@Service
public class BarberStateServiceImpl extends ServiceImpl<BarberStateMapper, BarberState> implements IBarberStateService {
    @Autowired
    BarberStateMapper barberStateMapper;
    @Override
    public List<BarberState> findAllBarberState() {
        return barberStateMapper.findAllBarberState();
    }

    @Override
    public List<BarberState> findAllBarberStateByDate(LocalDate theDate) {
        return barberStateMapper.findAllBarberStateByDate(theDate);
    }

    @Override
    public int updateStateByBarberName(BarberState barberState) {
        return barberStateMapper.updateStateByBarberName(barberState);
    }

}
