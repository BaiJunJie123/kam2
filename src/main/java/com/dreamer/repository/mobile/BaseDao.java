package com.dreamer.repository.mobile;

import org.hibernate.criterion.DetachedCriteria;

import com.dreamer.domain.mall.transfer.Transfer;
import com.dreamer.domain.pmall.goods.PmallGoods;
import com.dreamer.domain.pmall.order.Order;

import ps.mx.otter.utils.SearchParameter;

import java.util.List;
import java.util.Map;

/**
 * Created by huangfei on 26/06/2017.
 */
public interface BaseDao<T> {
	// 显示本人订单
	/*List<Transfer> finddingdanbyuser(String kamname,Integer stat,Integer end);*/
	//股东只能购买一个
	int findbuyKouhong(String kamnum);
	// 只有股东才能看到口红
	int findkam(String name);
    // bjj work 从数据库查出6个新增的活动口红
	List<PmallGoods> findkonghong();
    List<T> findAll();

    //保存
    T merge(T entity);

    //删除
    void delete(Object entity);

    //根据ID获取
    T get(Integer id);

    //根据一个参数获取
    T get(String name,Object value);

    //根据参数获取 模糊查询
    T like(String name,Object value);

    //根据参数获取 模糊查询
     List<T> likeList(String name,Object value);

    //根据参数获取
    List<T> getList(String name,Object value);

    //根据一组参数获取
    T get(Map<String,Object> map);

    //根据一组参数获取
    List<T> getList(Map<String,Object> map);

    //根据参数获取
    List<T> getListIn(String name,List<Object> value);

    //或语句
    List<T> getOr(Map<String,Object> map);

    //通过一群参数获取
     List<T> findByCriteria(DetachedCriteria dc, Integer firstResult, Integer maxResults);

    //通过一群参数获取
    List<T> findByCriteria(DetachedCriteria dc);
//    public List<T> findAll();


    List<T> searchByPage(SearchParameter<T> parameter, DetachedCriteria criteria);

    void addRestraction(DetachedCriteria dc,String name,Object o);

}
