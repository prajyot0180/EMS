package com.prajyot.dao;

import java.sql.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.prajyot.database.HibernateConnection;
import com.prajyot.interfaces.EmployeeTaskInterface;
import com.prajyot.entity.EmployeeTaskEntity;

public class HbEmployeeTaskDao implements EmployeeTaskInterface {

	private int noOfRecords;
	public int getNoOfRecords() {
		return noOfRecords;
	}
	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}
	
	static final Logger logger = LogManager.getLogger(JdbcEmployeeTaskDao.class);

	@Override
	public List<EmployeeTaskEntity> viewAllEmployeeTask(int offSet, String employeeUserName) {
		logger.info("Hibernate viewAllEmployeeTask() called");
		Session session = null;
		List<EmployeeTaskEntity> list = null;
		try {
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			int recoordPerPage = 13;
			Query<EmployeeTaskEntity> query = session.createQuery("from EmployeeTaskEntity where ename = : ename order by id", EmployeeTaskEntity.class);
			query.setParameter("ename", employeeUserName);
			query.setFirstResult(offSet);
			query.setMaxResults(recoordPerPage);
			list = query.getResultList();
			logger.info(list.size());
		}catch(Exception e) {
			logger.info("Catch called viewAllEmployee()" + e);
		}finally {
			if(session != null)
				session.close();
		}
		return list;
	}
	
	
	public List<EmployeeTaskEntity> allEmployeeTask(String employeeUserName) {
		logger.info("Hibernate viewAllEmployeeTask() called");
		Session session = null;
		List<EmployeeTaskEntity> list = null;
		try {
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			Query<EmployeeTaskEntity> query = session.createQuery("from EmployeeTaskEntity where ename = : ename order by id", EmployeeTaskEntity.class);
			query.setParameter("ename", employeeUserName);
			list = query.getResultList();
			logger.info(list.size());
		}catch(Exception e) {
			logger.info("Catch called viewAllEmployee()" + e);
		}finally {
			if(session != null)
				session.close();
		}
		return list;
	}
	
	@Override
	public void addEmployeeTask(String taskname, String status, String note, Date sqlDate, String employeeUserName) {
		logger.info("Hibernate addEmployeeTask() called");
		Session session = null;
		EmployeeTaskEntity employeeTask = new EmployeeTaskEntity();
		try {	
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			Transaction transaction = session.beginTransaction();
			employeeTask.setTaskName(taskname);
			employeeTask.setEname(employeeUserName);
			employeeTask.setNote(note);
			employeeTask.setStatus(status);
			employeeTask.setDate(sqlDate);
			employeeTask.setLastUpdate(sqlDate);
			session.save(employeeTask);
			transaction.commit();
		}catch(Exception e) {
			logger.info("Catch called addEmployeeTask()" + e);
		}finally {
			if(session != null)
				session.close();
		}
	}

	@Override
	public void updateEmployeeTask(String updatetask, String updatestatus, String updatenote, Date sqlDate,
			Integer idvalue, Date sqlLastUpdate) {
		logger.info("Hibernate updateEmployeeTask() called");
		Session session = null;
		EmployeeTaskEntity employeeTask = null;
		try {
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			Transaction transaction = session.beginTransaction();
			Query<EmployeeTaskEntity> query = session.createQuery("from EmployeeTaskEntity where id= :id", EmployeeTaskEntity.class);
			query.setParameter("id", idvalue);
			employeeTask = query.uniqueResult();
			employeeTask.setTaskName(updatetask);
			employeeTask.setNote(updatenote);
			employeeTask.setStatus(updatestatus);
			employeeTask.setLastUpdate(sqlLastUpdate);
			session.saveOrUpdate(employeeTask);
			transaction.commit();
		}catch(Exception e) {
			logger.info("Catch called updateEmployeeTask()" + e);
		}finally {
			if(session != null)
				session.close();
		}
	}

	@Override
	public void deleteEmployeeTask(Integer taskId) {
		logger.info("Hibernate deleteEmployeeTask() called");
		Session session = null;
		EmployeeTaskEntity employeeTask = null;		
		try {
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			Transaction transaction = session.beginTransaction();
			Query<EmployeeTaskEntity> query = session.createQuery("from EmployeeTaskEntity where id= :id", EmployeeTaskEntity.class);
			query.setParameter("id", taskId);
			employeeTask = query.uniqueResult();
			session.delete(employeeTask);
			transaction.commit();
		}catch(Exception e) {
			logger.info("Catch called deleteEmployeeTask()" + e);
		}finally {
			if(session != null)
				session.close();
		}
	}

	@Override
	public int getTotalTask(String employeeUserName) {
		logger.info("Hibernate getTotalTask() called");
		Session session = null;
		try {
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			Query<EmployeeTaskEntity> query = session.createQuery("from EmployeeTaskEntity where ename= :name", EmployeeTaskEntity.class);
			query.setParameter("name", employeeUserName);
			int totalTask = query.getResultList().size();
			return totalTask;
		}catch(Exception e) {
			logger.info("Catch called getTotalTask()" + e);
		}finally {
			if(session != null)
				session.close();
		}
		return 0;
	}

	@Override
	public int getCompletedTask(String employeeUserName) {
		logger.info("Hibernate getCompletedTask() called");
		Session session = null;
		try {
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			Query<EmployeeTaskEntity> query = session.createQuery("from EmployeeTaskEntity where ename= :name and status = :status", EmployeeTaskEntity.class);
			query.setParameter("name", employeeUserName);
			query.setParameter("status", "completed");
			int completeTask = query.getResultList().size();
			return completeTask;
		}catch(Exception e) {
			logger.info("Catch called getCompletedTask()" + e);
		}finally {
			if(session != null)
				session.close();
		}
		return 0;
	}

	@Override
	public int getReaminTask(String employeeUserName) {
		logger.info("Hibernate getReaminTask() called");
		Session session = null;
		try {
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			Query<EmployeeTaskEntity> query = session.createQuery("from EmployeeTaskEntity where ename= :name and status = :status", EmployeeTaskEntity.class);
			query.setParameter("name", employeeUserName);
			query.setParameter("status", "in progress");
			int remainTask = query.getResultList().size();
			return remainTask;
		}catch(Exception e) {
			logger.info("Catch called getReaminTask()" + e);
		}finally {
			if(session != null)
				session.close();
		}
		return 0;
	}

	@Override
	public boolean dateExist(String sdate) {
		logger.info("Hibernate dateExist() called");
		Session session = null;
		EmployeeTaskEntity employeeTask = null;		
		try {
			session = HibernateConnection.getSessionFactory().openSession(); //factory.openSession();
			Query<EmployeeTaskEntity> query = session.createQuery("from EmployeeTaskEntity where date = TO_DATE( :date, 'YYYY-MM-DD')", EmployeeTaskEntity.class);
			query.setParameter("date", sdate);
			employeeTask = query.uniqueResult();
			if(employeeTask != null) {
				return true;
			}
		}catch(Exception e) {
			logger.info("Catch called dateExist()" + e);
		}finally {
			if(session != null)
				session.close();
		}
		return false;
	}
}
