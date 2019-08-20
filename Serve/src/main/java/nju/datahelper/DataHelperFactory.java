package nju.datahelper;

/**
 * Created by thinkpad on 2017/12/4.
 */
public class DataHelperFactory {
    private static DataHelper dataHelper;

    /**
     * 构建以Hibernate框架为数据存取方式的DataHelper
     *
     * @return 以Hibernate框架为数据存取方式的DataHelper
     */
    public synchronized static DataHelper getHibernateDataHelper() {
        if (dataHelper == null) {
            dataHelper = new HibernateHelper();
        }
        return dataHelper;
    }
}
