package com.service;

import com.bean.PutStorage;
import com.dao.PutStorageDao;
import com.dao.PutStorageDaoImpl;

/**
 * @author shkstart
 * @create 2020-06-05 16:31
 */
public class PutStorageServiceImpl implements PutStorageService{
    private PutStorageDao psd = new PutStorageDaoImpl();

    @Override
    public Boolean addStorage(PutStorage storage) {
        // 返回插入是否成功
        return psd.insertPutStorage(storage) > 0;
    }

    @Override
    public Boolean delStorage(Integer eno) {

        return psd.deletePutStorageById(eno) > 0;
    }
}
