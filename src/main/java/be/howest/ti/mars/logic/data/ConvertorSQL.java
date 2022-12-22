package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.users.PricePlan;
import be.howest.ti.mars.logic.domain.users.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConvertorSQL {

    protected User sqlToUser(ResultSet rs) throws SQLException{
        return new User(rs.getString("uid"), rs.getString("firstname"), rs.getString("lastname"), PricePlan.valueOf(rs.getString("pricePlan")));
    }

}
