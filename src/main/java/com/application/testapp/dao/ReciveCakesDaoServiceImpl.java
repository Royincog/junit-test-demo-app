package com.application.testapp.dao;
import com.application.testapp.entity.Cake;
import com.application.testapp.utils.DbUtils;
import com.application.testapp.utils.DbUtilsImpl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ReciveCakesDaoServiceImpl implements DaoService{

    private List<Cake> listOFCakes; //We are storing the list as array List

    public ReciveCakesDaoServiceImpl(List<Cake> listOFCakes){
        this.listOFCakes = listOFCakes;
        setup();
    }
    private void setup() {
        try {
            //Initialize the connection
            Connection connection = init();
            Statement statement = connection.createStatement();
            //Get the result set add it to the query
            executeStatement(statement);
            //Close the connection
            closeConnection(connection,statement);
      } catch (SQLException e) {
        throw new RuntimeException(e);
        }
    }
    /*
    * Get me the ResultSEt from the Connection !!
    * */
    private ResultSet getResultSetForQuery(Statement statement , String query){

        try {
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    * Add on the cake to the list
    * */
    private void addListOFCakeFromResultSet(ResultSet resultSet,List<Cake> cakeList) {
       try {
           while (resultSet.next()) {
               Cake cake = new Cake(resultSet.getInt("CAKEID"),resultSet.getString("CAKENAME"),resultSet.getString("CAKEFLAVOUR"));
               cakeList.add(cake);
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
    }
    /*
    * Intialize the connection
    * */
    private Connection init(){
        DbUtils dbUtils = new DbUtilsImpl(); // Calling the Util function to get the Connection Object
        Connection connection = dbUtils.getConnection(); // Getting the Connection From Connection Object
        return connection;
    }
    private void executeStatement(Statement statement){
        ResultSet resultSet = getResultSetForQuery(statement,"SELECT * FROM CAKETABLE");
        addListOFCakeFromResultSet(resultSet,listOFCakes);
    }
    private void closeConnection(Connection connection,Statement statement){
        try {
            connection.close(); //close the connection
            statement.close(); //close staement
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Cake> retriveListOfCakes() {
        return this.listOFCakes;
    }
}
