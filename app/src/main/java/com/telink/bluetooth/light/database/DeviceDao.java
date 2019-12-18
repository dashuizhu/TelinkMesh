package com.telink.bluetooth.light.database;

import com.telink.bluetooth.light.bean.DeviceBean;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * 设备列表
 *
 * @Author: zhujiang
 * @Date: 2019/12/18 10:27
 */
public class DeviceDao extends RealmObject {

    private final static String TAG       = DeviceDao.class.getSimpleName();
    private final static String COLUMN_ID = "id";

    private String id;
    private String name;
    private String net;
    private String psd;

    public static DeviceDao castDao(DeviceBean bean) {
        DeviceDao dao = new DeviceDao();
        dao.id = bean.getId();
        dao.name = bean.getName();
        dao.net = bean.getNet();
        dao.psd = bean.getPsd();
        return dao;
    }

    public DeviceBean castBean() {
        DeviceBean bean = new DeviceBean();
        bean.setId(bean.getId());
        bean.setName(this.name);
        bean.setNet(this.net);
        bean.setPsd(this.psd);
        return bean;
    }

    public static String save(final DeviceBean bean) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        //查询本地是否有记录
        DeviceDao dao =
                realm.where(DeviceDao.class).equalTo(COLUMN_ID, bean.getId()).findFirst();
        //没有记录
        if (dao == null) {
            dao = castDao(bean);
            realm.copyToRealmOrUpdate(dao);
        } else {
            //有记录
            dao.name = bean.getName();
        }
        return dao.id;

    }

}
