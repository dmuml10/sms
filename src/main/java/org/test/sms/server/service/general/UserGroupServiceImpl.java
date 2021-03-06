package org.test.sms.server.service.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.test.sms.common.entity.general.UserGroup;
import org.test.sms.common.enums.general.ErrorCode;
import org.test.sms.common.exception.AppException;
import org.test.sms.common.filter.AbstractFilter;
import org.test.sms.common.service.general.UserGroupService;
import org.test.sms.common.service.general.UserService;
import org.test.sms.server.dao.interfaces.general.UserGroupDao;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserGroupServiceImpl implements UserGroupService {

    private UserGroupDao dao;

    private UserService userService;

    @Autowired
    public UserGroupServiceImpl(UserGroupDao dao, UserService userService) {
        this.dao = dao;
        this.userService = userService;
    }

    @Override
    public UserGroup add(UserGroup entity) throws AppException {
        return dao.add(entity);
    }

    @Override
    public UserGroup update(UserGroup entity) throws AppException {
        return dao.update(entity);
    }

    @Override
    public void delete(long id) throws AppException {
        if (userService.exists(id)) {
            throw new AppException(ErrorCode.USER_GROUP_HAS_USERS);
        }

        dao.delete(id);
    }

    @Override
    public Optional<UserGroup> get(long id) {
        return dao.get(id);
    }

    @Override
    public long getCount(AbstractFilter filter) {
        return dao.getCount(filter);
    }

    @Override
    public List<UserGroup> getList(AbstractFilter filter) {
        return dao.getList(filter);
    }
}