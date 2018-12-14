package com.dreamer.service.mobile;

import org.hibernate.criterion.DetachedCriteria;

import com.dreamer.domain.pmall.goods.PmallGoods;

import java.util.List;
import java.util.Map;

/**
 * Created by huangfei on 02/07/2017.
 */
public interface BaseHandler<T>  {

    T merge(T object);

    T save(T object);

    void delete(T object);

    T get(Integer id);

    T get(String name,Object value);

    //根据一组参数获取
    T get(Map<String,Object> map);

    //根据一组参数获取
    List<T> getList(Map<String,Object> map);

    //根据一组参数获取
    List<T> getListIn(String name,List<Object> value);


    //根据一组参数获取
    List<T> getList(String name,Object value);

    List<T> findAll();

    List<T> getOr(Map<String,Object> map);

    //模糊查询
    //根据参数获取 模糊查询
    T like(String name,Object value);

    //根据参数获取 模糊查询
    List<T> likeList(String name,Object value);

    void addRestraction(DetachedCriteria dc, String name, Object o);
   
    // bjj work kouhong
    List<PmallGoods> findkonghong();
    
 // 只有股东才能看到口红
 	int findkam(String name);
 	//股东只能购买一个
  	int findbuyKouhong(String kamnum);
   
  	// 订单查询

}
