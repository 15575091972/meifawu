package com.meifawu.meifawu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meifawu.meifawu.dao.ProjectsMapper;
import com.meifawu.meifawu.entity.Projects;
import com.meifawu.meifawu.service.IProjectsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 孟子弘
 * @since 2021-12-24
 */
@Service
public class ProjectsServiceImpl extends ServiceImpl<ProjectsMapper, Projects> implements IProjectsService {

}
