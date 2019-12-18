package com.telink.bluetooth.light.database;

import android.util.Log;

import com.telink.bluetooth.light.bean.DeviceBean;
import com.telink.bluetooth.light.bean.GroupBean;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * 分组名
 *
 * @Author: zhujiang
 * @Date: 2019/12/18 10:24
 */
public class GroupDao extends RealmObject {

    private final static String TAG = "groupDao";

    private final static String COLUMN_ID = "id";

    @PrimaryKey
    private int id;

    private String name;

    public static void saveOrUpdate(final GroupBean bean) {
        Log.w(TAG, "save " + bean.getName() + " " + bean.getDeivceList().toString());
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //如果没有排序号， 就查找本地最大的 +1
                if (bean.getId() == 0) {
                    Number number = realm.where(DeviceDao.class).max(COLUMN_ID);
                    int id = 1;
                    if (number != null) {
                        id = number.intValue();
                        id++;
                    }
                    bean.setId(id);
                }
                //查询本地是否有记录
                GroupDao dao =
                        realm.where(GroupDao.class).equalTo(COLUMN_ID, bean.getId()).findFirst();
                //没有记录
                if (dao == null) {
                    realm.copyToRealmOrUpdate(castDao(bean));
                } else {
                    //有记录
                    dao.name = bean.getName();
                    RealmList list = new RealmList();
                    for (DeviceBean deviceBean : bean.getDeivceList()) {
                        list.add(DeviceDao.castDao(deviceBean));
                    }
//                    dao.deviceList = list;
                }
            }
        });
    }

    private static GroupDao castDao(GroupBean bean) {
        GroupDao dao = new GroupDao();
        dao.id = bean.getId();
        dao.name = bean.getName();
//        RealmList<DeviceDao> list = new RealmList();
//        for (DeviceBean device : bean.getDeivceList()) {
//            list.add( DeviceDao.castDao(device) );
//        }
//        dao.deviceList = list;
        return dao;
    }

    private GroupBean castBean() {
        GroupBean bean = new GroupBean();
        bean.setId(id);
        bean.setName(this.name);

//        List<DeviceBean> list = new ArrayList<>();
//        if (deviceList != null) {
//            for (DeviceDao dao : deviceList) {
//                list.add(dao.castBean());
//            }
//        }
//        bean.setDeivceList(list);

        return bean;
    }

    public static void delete(final GroupBean deviceBean) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                DeviceDao dao = realm.where(DeviceDao.class)
                        .equalTo(COLUMN_ID, deviceBean.getId())
                        .findFirst();
                if (dao != null) {
                    dao.deleteFromRealm();
                }
            }
        });
    }


}
