package idv.lance.service;

import idv.lance.dao.entity.UserView;

import java.util.List;

public interface UserService {
    List<UserView> users();
}
