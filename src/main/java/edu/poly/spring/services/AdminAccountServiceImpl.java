package edu.poly.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.spring.models.Admin;
import edu.poly.spring.reponsitories.AdminReponsitory;

@Service
public class AdminAccountServiceImpl implements AdminAccountService {
	@Autowired
	private AdminReponsitory adminReponsitory;

	@Override
	public Optional<Admin> findByUsernameIgnoreCase(String username ) {
		return adminReponsitory.findByUsernameIgnoreCase(username);
	}


	@Override
	public Optional<Admin> findByUsernameAndPassword(String username, String password) {
		return adminReponsitory.findByUsernameAndPassword(username, password);
	}



	@Override
	public Admin save(Admin entity) {
		return adminReponsitory.save(entity);
	}

	@Override
	public <S extends Admin> Optional<S> findOne(Example<S> example) {
		return adminReponsitory.findOne(example);
	}

	@Override
	public Page<Admin> findAll(Pageable pageable) {
		return adminReponsitory.findAll(pageable);
	}

	@Override
	public List<Admin> findAll() {
		return adminReponsitory.findAll();
	}

	@Override
	public List<Admin> findAll(Sort sort) {
		return adminReponsitory.findAll(sort);
	}

	@Override
	public List<Admin> findAllById(Iterable<String> ids) {
		return adminReponsitory.findAllById(ids);
	}

	@Override
	public <S extends Admin> List<S> saveAll(Iterable<S> entities) {
		return adminReponsitory.saveAll(entities);
	}

	@Override
	public Optional<Admin> findById(String ids) {
		return adminReponsitory.findById(ids);
	}

	@Override
	public void flush() {
		adminReponsitory.flush();
	}

	@Override
	public <S extends Admin> S saveAndFlush(S entity) {
		return adminReponsitory.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(String id) {
		return adminReponsitory.existsById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Admin> entities) {
		adminReponsitory.deleteInBatch(entities);
	}

	@Override
	public <S extends Admin> Page<S> findAll(Example<S> example, Pageable pageable) {
		return adminReponsitory.findAll(example, pageable);
	}

	@Override
	public void deleteAllInBatch() {
		adminReponsitory.deleteAllInBatch();
	}

	@Override
	public Admin getOne(String id) {
		return adminReponsitory.getOne(id);
	}

	@Override
	public <S extends Admin> long count(Example<S> example) {
		return adminReponsitory.count(example);
	}

	@Override
	public <S extends Admin> boolean exists(Example<S> example) {
		return adminReponsitory.exists(example);
	}

	@Override
	public <S extends Admin> List<S> findAll(Example<S> example) {
		return adminReponsitory.findAll(example);
	}

	@Override
	public long count() {
		return adminReponsitory.count();
	}

	@Override
	public void deleteById(String id) {
		adminReponsitory.deleteById(id);
	}

	@Override
	public <S extends Admin> List<S> findAll(Example<S> example, Sort sort) {
		return adminReponsitory.findAll(example, sort);
	}

	@Override
	public void delete(Admin entity) {
		adminReponsitory.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends Admin> entities) {
		adminReponsitory.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		adminReponsitory.deleteAll();
	}

}
