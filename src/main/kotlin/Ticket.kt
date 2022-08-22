import java.sql.DriverManager
//model class
data class Data1(val ID:Int,val Ticket_Number: Int,val Passenger_ID:Int,val Train_ID:Int,val Ticket_Price:Int)

fun main(args:Array<String>){
    println("hello")
    val jdbcUrl = "jdbc:mysql://localhost:3306/onlineticketbooking"
    val connection = DriverManager.getConnection(jdbcUrl,"root","0987654321")
    println(connection.isValid(0))
//insert into data base

    val insert_res = connection.createStatement().executeUpdate("insert into ticket(ID,Ticket_Number,Passenger_ID,Train_ID) values('1',67567,'001','1','1500')")

    if(insert_res > 0) {
        println("successfuly inserted record into ticket db !!! ")
    }else{
        println("insert not sucessful")
    }

    //update records
    val update_res = connection.createStatement().executeUpdate("update ticket set Passenger_ID='aaaabb' where Train_Number=19 ")
    if(update_res>0)
    {
        println("record succesfully updated")
    }
    else{
        println("insert not update")
    }

    //delete query records
    val delete_res = connection.createStatement().executeUpdate("delete from ticket where Passenger_id =11 ")
    if(delete_res>0)
    {
        println("record succesfully deleted")
    }
    else{
        println("insert not deleted")
    }



//fetch the qurey from database
    val query = connection.prepareStatement("select * from ticket")
    val result = query.executeQuery()
    val user = mutableListOf<Data1>()

    while (result.next()){
        val ID:Int = result.getInt("ID")
        val Ticket_Number:Int = result.getInt("Ticket_Number")
        val Passenger_ID:Int = result.getInt("Passenger_ID")
        val Train_ID:Int = result.getInt("Train_ID")
        val Ticket_Price:Int =result.getInt("Ticket_Price")

        user.add(Data1(ID,Ticket_Number, Passenger_ID,Train_ID,Ticket_Price))
    }
    println(user)
}