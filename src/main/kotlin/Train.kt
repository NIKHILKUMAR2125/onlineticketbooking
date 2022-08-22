import java.sql.DriverManager
//model class
data class Data2(val Train_ID:Int,val Train_NO: Int,val Train_Name:String,val Source:String,val Destination:String,val Start_Date:String,val Time:String,val Arrival_Time:String)

fun main(args:Array<String>){
    println("hello")
    val jdbcUrl = "jdbc:mysql://localhost:3306/onlineticketbooking"
    val connection = DriverManager.getConnection(jdbcUrl,"root","0987654321")
    println(connection.isValid(0))
//insert into data base

    val insert_res = connection.createStatement().executeUpdate("insert into train(Train_ID,Train_NO,Train_Name,Source,Destination,Start_Date,Time,Arrival_Time) values('1',76545,'kk express','Delhi','Banglore','04-08-22','6:00AM','04-08-22')")

    if(insert_res > 0) {
        println("successfuly inserted record into train db !!! ")
    }else{
        println("insert not sucessful")
    }

    //update records
    val update_res = connection.createStatement().executeUpdate("update train set Train_Name='aaaabb' where Train_id=17 ")
    if(update_res>0)
    {
        println("record succesfully updated")
    }
    else{
        println("insert not update")
    }

    //delete query records
    val delete_res = connection.createStatement().executeUpdate("delete from train where passenger_id =11 ")
    if(delete_res>0)
    {
        println("record succesfully deleted")
    }
    else{
        println("insert not deleted")
    }



//fetch the qurey from database
    val query = connection.prepareStatement("select * from train")
    val result = query.executeQuery()
    val user = mutableListOf<Data2>()

    while (result.next()){
        val Train_ID:Int = result.getInt("Train_ID")
        val Train_NO:Int = result.getInt("Train_NO")
        val Train_Name:String = result.getString("Train_Name")
        val Source:String = result.getString("Source")
        val Destination:String = result.getString("Destination")
        val Start_Date:String = result.getString("Start_Date")
        val Time:String=result.getString("Time")
        val Arrival_Time:String=result.getString("Arrival_Time")
        user.add(Data2(Train_ID,Train_NO,Train_Name,Source,Destination,Start_Date,Time,Arrival_Time))
    }
    println(user)
}