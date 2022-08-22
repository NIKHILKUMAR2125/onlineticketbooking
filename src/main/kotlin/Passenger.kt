import java.sql.DriverManager
//model class
data class Data(val passenger_id:Int,val passenger_name: String,val passenger_age:String,val gender:String,val phone:String)

fun main(args:Array<String>){
    println("hello")
    val jdbcUrl = "jdbc:mysql://localhost:3306/onlineticketbooking"
    val connection = DriverManager.getConnection(jdbcUrl,"root","0987654321")
    println(connection.isValid(0))
//insert into data base

    val insert_res = connection.createStatement().executeUpdate("insert into passengers(Passenger_ID,Passenger_Name,Passenger_Age,Gender,Phone) values(002,'Julie',23,'M','963258712')")

    if(insert_res > 0) {
        println("successfuly inserted record into passenger db !!! ")
    }else{
        println("insert not sucessful")
    }

    //update records
    val update_res = connection.createStatement().executeUpdate("update passengers set Passenger_Name='aaaabb' where Passenger_ID=17 ")
    if(update_res>0)
    {
        println("record succesfully updated")
    }
    else{
        println("insert not update")
    }

    //delete query records
    val delete_res = connection.createStatement().executeUpdate("delete from passengers where Passenger_ID =11 ")
    if(delete_res>0)
    {
        println("record succesfully deleted")
    }
    else{
        println("insert not deleted")
    }



//fetch the qurey from database
    val query = connection.prepareStatement("select * from passengers")
    val result = query.executeQuery()
    val user = mutableListOf<Data>()

    while (result.next()){
        val Passenger_ID = result.getInt("Passenger_ID")
        val Passenger_Name = result.getString("Passenger_Name")
        val Passenger_Age:String = result.getString("Passenger_Age")
        val Gender:String = result.getString("Gender")
        val Phone:String = result.getString("Phone")
        user.add(Data(Passenger_ID,Passenger_Name, Passenger_Age,Gender,Phone))
    }
    println(user)
}