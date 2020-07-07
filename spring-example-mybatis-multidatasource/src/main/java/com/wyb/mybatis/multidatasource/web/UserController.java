package com.wyb.mybatis.multidatasource.web;

import com.wyb.mybatis.multidatasource.dao.mapper.test1.User1Mapper;
import com.wyb.mybatis.multidatasource.dao.mapper.test2.User2Mapper;
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
    private User1Mapper user1Mapper;

	@Autowired
	private User2Mapper user2Mapper;

	@RequestMapping("/getUsers")
	public List<UserDo> getUsers() {
		List<UserDo> users=user1Mapper.getAll();
		log.info(users.toString());
		return users;
	}

    @RequestMapping("/getUser")
    public UserDo getUser(Long id) {
        UserDo user=user2Mapper.getOne(id);
        return user;
    }

    @RequestMapping("/add")
    public void save(UserDo user) {
        user2Mapper.insert(user);
    }

    @RequestMapping(value="update")
    public void update(UserDo user) {
        user2Mapper.update(user);
    }

    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        user1Mapper.delete(id);
    }

}
