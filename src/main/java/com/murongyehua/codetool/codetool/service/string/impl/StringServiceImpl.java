package com.murongyehua.codetool.codetool.service.string.impl;

import cn.hutool.core.util.StrUtil;
import com.murongyehua.codetool.codetool.dto.ResultMap;
import com.murongyehua.codetool.codetool.service.string.StringService;
import org.springframework.stereotype.Service;

/**
 * @author liul
 * @version 1.0 2019/9/3
 */
@Service
public class StringServiceImpl implements StringService {
    @Override
    public ResultMap normalUUID() {
        return ResultMap.isSuccess(StrUtil.uuid());
    }

    @Override
    public ResultMap simpleUUID() {
        return ResultMap.isSuccess(StrUtil.uuid().replaceAll(StrUtil.DASHED, StrUtil.EMPTY));
    }
}
