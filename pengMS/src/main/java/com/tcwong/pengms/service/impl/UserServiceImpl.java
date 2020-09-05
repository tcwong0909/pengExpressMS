package com.tcwong.pengms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tcwong.pengms.dao.UserMapper;
import com.tcwong.pengms.dao.UserMapperEx;
import com.tcwong.pengms.dto.UserDO;
import com.tcwong.pengms.dto.UserRequest;
import com.tcwong.pengms.dto.UserVO;
import com.tcwong.pengms.model.Role;
import com.tcwong.pengms.model.User;
import com.tcwong.pengms.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserMapperEx userMapperEx;
    @Override
    public int addUser(User user) {
//        String password = new Md5Hash("123456").toString();
//        user.setPassword((new Md5Hash(password,user.getAccount(),1024)).toString());
        return userMapper.insert(user);
    }
    @Override
    public int deleteByIds(String ids) {
        String[] split = ids.split(",");

//        userMapper.deleteByIds(split);
        return split.length;
    }

    @Override
    public int editUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int editPassword(User user) {
//        Integer userid = ((User) (SecurityUtils.getSubject().getSession()
//                .getAttribute("user"))).getUserid();
//        user.setUserid(userid);
//        String password = userMapper.selectByPrimaryKey(user.getUserid()).getPassword();
//        String oldPassword = new Md5Hash(user.getOldPassword(), ((User) (SecurityUtils.getSubject().getSession()
//                .getAttribute("user"))).getAccount(), 1024).toString();
//        if (!oldPassword.equals(password)) {
//            return 0;
//        }
//        Md5Hash md5Hash = new Md5Hash(user.getPassword(), ((User) (SecurityUtils.getSubject().getSession().getAttribute("user"))).getAccount(), 1024);
//        user.setPassword(md5Hash.toString());
        int i = userMapper.updateByPrimaryKeySelective(user);
        return i ;
    }

    /**
     * Description 查询用户列表
     *
     * @param request 用户管理
     * @return
     * @author tcwong
     * @date 2020/9/2
     */
    @Override
    public PageInfo<UserVO> listUsers(UserRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        User user = new User();
        BeanUtils.copyProperties(request, user);
        List<UserDO> userDOS = userMapperEx.listUsers(user);
        PageInfo<UserDO> userDOPageInfo = new PageInfo<>(userDOS);
        long total = userDOPageInfo.getTotal();
        LinkedList<UserVO> userVOS = new LinkedList<>();
        userDOS.forEach(userDO -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userDO,userVO);
            Role role = userDO.getRole();
            if (role != null) {
                userVO.setRoleName(role.getName());
            }
            userVOS.add(userVO);
        });
        PageInfo<UserVO> result = new PageInfo<>(userVOS);
        result.setTotal(total);
        return result ;
    }

}
