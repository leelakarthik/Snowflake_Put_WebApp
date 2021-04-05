package com.example.put_api.put_api;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Statement;

@Component
public class Put_into_stage {

    public void Put_file(String File_path, String File_name, String stage_name, SnowConnection snowConnection) {
        // create statement
        try {
            Connection connection = SnowConnection.getConnection();
            System.out.println("staging files");
            Statement statement = connection.createStatement();
            statement.executeUpdate("use database db_name; ");
            statement.executeUpdate("create stage IF NOT EXISTS " + stage_name);
            statement.executeQuery("PUT file://" + File_path + "/" + File_name + " @" + stage_name + " overwrite=true");
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new StageException("Could not stage file " + File_name
                    + ". Please try again!");
        }
//        ResultSet x = statement.executeQuery("show stages;");
//        while (x.next()) {
//            System.out.println(x.getString(2));
//        }

    }
}
