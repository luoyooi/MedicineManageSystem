package com.service;

import com.bean.PutStorage;

/**
 * @author shkstart
 * @create 2020-06-05 16:30
 */
public interface PutStorageService {

    // 添加库存
    public Boolean addStorage(PutStorage storage);

    // 删除库存
    public Boolean delStorage(Integer order_id);
}
