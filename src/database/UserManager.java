package database;

import entity.User;
import exceptions.InvalidUserException;
import exceptions.UserAlreadyExistException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, User> users=new HashMap<>();

    public UserManager() {
    //    this.users=new HashMap<>();
    }

    public void add_Users(User user)
    {
        if(users!=null && users.containsKey(user.getName()))
            throw new UserAlreadyExistException();
        users.put(user.getName(), user);
    }

    public User getUser(String userName)
    {
        if(!users.containsKey(userName))
            throw new InvalidUserException();
        return users.get(userName);
    }

    public Collection<User> getUsers() {
        return users.values();
    }
}
