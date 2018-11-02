package com.dreamer.repository.mobile;

import com.dreamer.domain.pmall.order.OrderItem;
import com.dreamer.domain.pmall.order.PaymentStatus;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by huangfei on 11/07/2017.
 */
@Repository
public class OrderItemDao extends BaseDaoImpl<OrderItem> {

    public List<OrderItem> findOrderItemByGoodsAndUserAndTime(Integer gid, Integer uid, Date date){
//        String hql = "from ";
//        Queue query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
        DetachedCriteria dc = DetachedCriteria.forClass(OrderItem.class);
        dc.add(Restrictions.eq("pmallGoods.id",gid));//购买产品
        DetachedCriteria order = dc.createCriteria("pmallOrder");
        order.add(Restrictions.eq("paymentStatus",PaymentStatus.PAID));//已经支付
//        System.out.println(date);
        order.add(Restrictions.ge("updateTime",date));
        DetachedCriteria agent = order.createCriteria("user");
        agent.add(Restrictions.eq("id",uid));//购买人
        return  findByCriteria(dc);
    }

	@SuppressWarnings({ "rawtypes", "deprecation" })
	public void updateDeliverInfo(Integer id, String logisticsCode,
			String logistics) {
		Session session = this.getHibernateTemplate().getSessionFactory()
				.openSession();
		Transaction tx = session.beginTransaction();
		String hql="update Order ch set ch.logisticsCode = ? , ch.logistics = ? where ch.id= ?";
		Query query = session.createQuery(hql);
		query.setString(0,logisticsCode);
		query.setString(1,logistics);
		query.setInteger(2,id);
		query.executeUpdate();
		tx.commit();
		session.close();
		
	}

}
