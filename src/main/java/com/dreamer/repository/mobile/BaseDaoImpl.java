package com.dreamer.repository.mobile;

import org.apache.commons.collections.map.HashedMap;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.dreamer.domain.mall.transfer.Transfer;
import com.dreamer.domain.pmall.goods.PmallGoods;

import ps.mx.otter.utils.SearchParameter;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by huangfei on 26/06/2017.
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {


    private Class<T> clazz;

    public BaseDaoImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.clazz = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public List<T> findAll() {
        return getHibernateTemplate().loadAll(clazz);
    }

    @Override
    public T merge(T entity) {
        return getHibernateTemplate().merge(entity);
    }

    @Override
    public void delete(Object entity) {
        getHibernateTemplate().delete(entity);
    }

    @Override
    public T get(Integer id) {
        return getHibernateTemplate().get(clazz, id);
    }

    @Override
    public List<T> findByCriteria(DetachedCriteria dc, Integer firstResult, Integer maxResults) {
        if (firstResult == null || maxResults == null) {
            return (List<T>) getHibernateTemplate().findByCriteria(dc);
        }
        return (List<T>) getHibernateTemplate().findByCriteria(dc, firstResult, maxResults);
    }

    @Override
    public List<T> findByCriteria(DetachedCriteria dc) {
        return (List<T>) getHibernateTemplate().findByCriteria(dc);
    }

    @Override
    public T get(String name, Object value) {

        return getFirst(getList(name, value));
    }

    @Override
    public T get(Map map) {
        return getFirst(getList(map));
    }

    @Override
    public T like(String name, Object value) {
        return getFirst(likeList(name, value));
    }

    @Override
    public List<T> likeList(String name, Object value) {
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        dc.add(Restrictions.like(name, value));
        return findByCriteria(dc, null, null);
    }


    @Override
    public List<T> getList(String name, Object value) {
        Map<String, Object> map = new HashedMap();
        map.put(name, value);
        return getList(map);
    }


    @Override
    public List<T> getOr(Map<String, Object> map) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        Disjunction disjunction = Restrictions.disjunction();
        int i = 0;
        for (Object key : map.keySet()) {
            if (map.get(key) != null && !map.get(key).equals("")) {
                disjunction.add(Restrictions.eq((String) key, map.get(key)));
            } else {
                i++;
            }
        }
        if (map.size() == i) {//如果都为空
            return null;
        }
        criteria.add(disjunction);
        return findByCriteria(criteria);
    }

    @Override
    public List<T> getListIn(String name, List<Object> value) {
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        dc.add(Restrictions.in(name, value));
        return findByCriteria(dc, null, null);
    }

    @Override
    public List<T> getList(Map map) {
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
        for (Object key : map.keySet()) {
            if (map.get(key) != null && !map.get(key).equals("")) {
                //加入排序
                if (key.equals("ASC")) {
                    criteria.addOrder(Order.asc((String) map.get(key)));
                } else if (key.equals("DESC")) {
                    criteria.addOrder(Order.desc((String) map.get(key)));
                } else {//非排序 加入过滤
                    criteria.add(Restrictions.eq((String) key, map.get(key)));
                }
            }
        }
        return findByCriteria(criteria, null, null);
    }

    public Criteria getCriteria(DetachedCriteria dc) {
        Criteria criteria = dc.getExecutableCriteria(getHibernateTemplate().getSessionFactory().getCurrentSession());
        return criteria;
    }
   //============= 进入此处find
    @Override
    public List<T> searchByPage(SearchParameter<T> parameter, DetachedCriteria criteria) {
        //获取总数
        criteria.setProjection(Projections.rowCount());
        Object o =  getCriteria(criteria).uniqueResult();
        if(o==null){
            parameter.setTotalRows(0);
        }else {
            parameter.setTotalRows(((Long) getCriteria(criteria).uniqueResult()).intValue());
        }

        //取消总数  分页查询
        criteria.setProjection(null);
        parameter.gotoPage();//下一页
        if (parameter.noRows()) {//没有记录
            return new ArrayList<>();
        } else {
            List<T> list = findByCriteria(criteria, (parameter.caculateFirstIndexForCurrentPage()), parameter.rowsOfPerPage());
            return list;
        }
    }

    @Override
    public void addRestraction(DetachedCriteria dc, String name, Object o) {
        if (o == null || o.equals("")) return;
        dc.add(Restrictions.eq(name, o));
    }

    /**
     * 如果有返回
     *
     * @param list
     * @return
     */
    private T getFirst(List<T> list) {
        if (list != null && list.size() != 0) return list.get(0);
        return null;
    }


    public void addExample(DetachedCriteria dc, String name, Object object) {
        if (object != null) {
            DetachedCriteria dctem = dc.createCriteria(name);
            Example example1 = Example.create(object).enableLike(MatchMode.ANYWHERE);
            dctem.add(example1);
        }
    }
    // bjj work  find kouhong
	@Override
	public List<PmallGoods> findkonghong() {
		// TODO Auto-generated method stub
		// List<PmallGoods> ji = new ArrayList<PmallGoods>();
		 return this.getHibernateTemplate().execute(new HibernateCallback<List<PmallGoods>>(){

			@Override
			public List<PmallGoods> doInHibernate(Session arg0) throws HibernateException {
				// TODO Auto-generated method stub
				   String sql = "from PmallGoods as p where p.id in (3525,3526,3527,3528,3529,3530)";
				   
					return arg0.createQuery(sql).list();
			}
			 
		 });
	
	}
   // bjj update 只有股东才能看到口红
	@Override
	public int findkam(final String name) {
		// TODO Auto-generated method stub
		List<Object> ss = new ArrayList<Object>();
	   ss =   this.getHibernateTemplate().execute(new HibernateCallback<List<Object>>(){

			@Override
			public List<Object> doInHibernate(Session arg0) throws HibernateException {
				// TODO Auto-generated method stub
				String sql = "select count(1) from Agent a where a.agentCode = ?";
				List<Object> ji = new ArrayList<Object>();
				  long shu = (long) arg0.createQuery(sql).setParameter(0, name).uniqueResult();
				  ji.add(shu);
				  return ji;
			}});
	     Number nn =  (Number) ss.get(0);
	     return nn.intValue();
	}
   // 股东只能购买一个口红
	@Override
	public int findbuyKouhong(final String kamnum) {
		// TODO Auto-generated method stub
		List<Object> dd = new ArrayList<Object>();
		dd = this.getHibernateTemplate().execute(new HibernateCallback<List<Object>>(){
			
			@Override
		 public List<Object> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				List<Object> ss = new ArrayList<Object>();
				String sql ="select count(1) from OrderItem e where e.pmallOrder.user.agentCode = ? and e.pmallGoods.id in (3525,3526,3527,3528,3529,3530)";
				long num = (long) session.createQuery(sql).setParameter(0, kamnum).uniqueResult();
				ss.add(num);
				return ss;
			}
			
		});
		
		 Number nn =  (Number) dd.get(0);
	     return nn.intValue();
	}
    // 仅仅显示自己的订单
	
	/*public List<Transfer> finddingdanbyuser(final String kamname,final Integer stat,final Integer end) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().execute(new HibernateCallback<List<Transfer>>(){

			@Override
			public List<Transfer> doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				String sql ="from Transfer t where t.fromAgent.agentCode = ?";
				session.createQuery(sql).setParameter(0, kamname).setFirstResult(stat).setMaxResults(end).list();
				return null;
			}
			
		});
	}*/

}
