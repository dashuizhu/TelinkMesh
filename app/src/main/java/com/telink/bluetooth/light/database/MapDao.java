package com.telink.bluetooth.light.database;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 组和设备的映射表
 *
 * @Author: zhujiang
 * @Date: 2019/12/18 11:15
 */
public class MapDao extends RealmObject {

    private final static String TAG = MapDao.class.getSimpleName();


    private final static String COLUMN_ID = "id";

    @PrimaryKey
    private int    id;
    private int    groupId;
    private String deviceId;

    public static void save(final int groupId, final String deviceId) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //如果没有排序号， 就查找本地最大的 +1
                Number number = realm.where(DeviceDao.class).max(COLUMN_ID);
                int id = 1;
                if (number != null) {
                    id = number.intValue();
                    id++;
                }
                //没有记录
                MapDao dao = new MapDao();
                dao.id = id;
                dao.deviceId = deviceId;
                dao.groupId = groupId;
            }
        });
    }

    public static void update(final int mapId, final int groupId, final String deviceId) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //查询本地是否有记录
                MapDao dao =
                        realm.where(MapDao.class).equalTo(COLUMN_ID, mapId).findFirst();
                //没有记录
                if (dao == null) {
                    dao = new MapDao();
                    dao.id = mapId;
                    dao.groupId = groupId;
                    dao.deviceId = deviceId;
                    realm.copyToRealmOrUpdate(dao);
                } else {
                    //有记录
                    dao.groupId = groupId;
                    dao.deviceId = deviceId;
                }
            }
        });
    }

    public static void delete(final int mapId) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                DeviceDao dao = realm.where(DeviceDao.class)
                        .equalTo(COLUMN_ID, mapId)
                        .findFirst();
                if (dao != null) {
                    dao.deleteFromRealm();
                }
            }
        });
    }

}
