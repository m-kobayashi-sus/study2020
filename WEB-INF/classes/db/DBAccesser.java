package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBAccesser {

	private String driver;
	private String url;
	private String user;
	private String password;
	private Connection cn;
	private Statement st;
	private ResultSet rs;

	/**
	 * コンストラクタ
	 * @param driver ドライバー
	 * @param url URL
	 * @param user ユーザー名
	 * @param password パスワード
	 */
	public DBAccesser(String driver, String url, String user, String password) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	/**
	 * 引数なしのコンストラクタ
	 * 既定値を使用する
	 */
	public DBAccesser() {
		driver = "org.postgresql.Driver";
		url = "jdbc:postgresql://localhost:5433/attendance";
		user = "postgres";
		password = "password";
	}
	
	/**
	 * データベースへの接続を行う
	 */
	public synchronized void open() throws Exception {
		Class.forName(driver);
		cn = DriverManager.getConnection(url, user, password);
		st = cn.createStatement();
	}

	/**
	 * SQL 文を実行した結果の ResultSet を返す
	 * @param sql SQL 文
	 */
	public ResultSet getResultSet(String sql) throws Exception  {
		if ( st.execute(sql) )  {
			return st.getResultSet();
		}
		return null;
	}

	/**
	 * SQL 文の実行
	 * @param sql SQL 文
	 */
	public void execute(String sql) throws Exception  {
		st.execute(sql);
	}

	/**
	 * コミット
	 */
	public void commit() throws Exception  {
		cn.commit();
	}

	/**
	 * ロールバック
	 */
	public void rollback() throws Exception  {
		cn.rollback();
	}

	/**
	 * データベースへのコネクションのクローズ
	 */
	public synchronized void close() throws Exception {
		if ( rs  != null ){
			rs.close();
		}
		if ( st  != null ){
			st.close();
		}
		if ( cn != null ){
			cn.close();
		}
	}
}
