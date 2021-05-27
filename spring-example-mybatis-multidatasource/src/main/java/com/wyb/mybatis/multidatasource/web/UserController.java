package com.wyb.mybatis.multidatasource.web;

import com.wyb.mybatis.multidatasource.dao.mapper.master.MUserMapper;
import com.wyb.mybatis.multidatasource.dao.mapper.slave.SUserMapper;
import com.wyb.mybatis.multidatasource.dao.model.UserDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private MUserMapper mUserMapper;

	@Autowired
	private SUserMapper sUserMapper;

	@RequestMapping("/getUsers")
	public List<UserDo> getUsers() {
		List<UserDo> users= mUserMapper.getAll();
		log.info(users.toString());
		return users;
	}

    @RequestMapping("/getUser")
    public UserDo getUser(Long id) {
        UserDo user= sUserMapper.getOne(id);
        return user;
    }

    @RequestMapping("/add")
    public void save(UserDo user) {
        sUserMapper.insert(user);
    }

    @RequestMapping(value="update")
    public void update(UserDo user) {
        sUserMapper.update(user);
    }

    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        mUserMapper.delete(id);
    }

}
