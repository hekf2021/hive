import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;




public class HiveTestCase {
	public static void main(String[] args) throws Exception {
		Class.forName("org.apache.hive.jdbc.HiveDriver");

		//String dropSQL = "drop table javabloger";
		//String createSQL = "create table javabloger (key int, value string)";
		// hive插入数据支持两种方式一种：load文件，令一种为从另一个表中查询进行插入（感觉这是个鸡肋）
		// hive是不支持insert into...values(....)这种操作的
		//String insterSQL = "LOAD DATA LOCAL INPATH '/work/hive/examples/files/kv1.txt' OVERWRITE INTO TABLE javabloger";
		String querySQL = "SELECT a.* FROM nifi.info a";

		Connection con = DriverManager.getConnection("jdbc:hive2://10.111.134.60:10000/nifi", "root", "");
		Statement stmt = con.createStatement();
		//stmt.executeQuery(dropSQL); // 执行删除语句
		//stmt.executeQuery(createSQL); // 执行建表语句
		//stmt.executeQuery(insterSQL); // 执行插入语句
		ResultSet res = stmt.executeQuery(querySQL); // 执行查询语句
		//Configuration conf = new Configuration(true);
		while (res.next()) {
			System.out.println("Result: key:" + res.getString(1) + "  –>  value:" + res.getString(2));
		}
	}
}
