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
	 * �R���X�g���N�^
	 * @param driver �h���C�o�[
	 * @param url URL
	 * @param user ���[�U�[��
	 * @param password �p�X���[�h
	 */
	public DBAccesser(String driver, String url, String user, String password) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	/**
	 * �����Ȃ��̃R���X�g���N�^
	 * ����l���g�p����
	 */
	public DBAccesser() {
		driver = "org.postgresql.Driver";
		url = "jdbc:postgresql://localhost:5433/attendance";
		user = "postgres";
		password = "password";
	}
	
	/**
	 * �f�[�^�x�[�X�ւ̐ڑ����s��
	 */
	public synchronized void open() throws Exception {
		Class.forName(driver);
		cn = DriverManager.getConnection(url, user, password);
		st = cn.createStatement();
	}

	/**
	 * SQL �������s�������ʂ� ResultSet ��Ԃ�
	 * @param sql SQL ��
	 */
	public ResultSet getResultSet(String sql) throws Exception  {
		if ( st.execute(sql) )  {
			return st.getResultSet();
		}
		return null;
	}

	/**
	 * SQL ���̎��s
	 * @param sql SQL ��
	 */
	public void execute(String sql) throws Exception  {
		st.execute(sql);
	}

	/**
	 * �R�~�b�g
	 */
	public void commit() throws Exception  {
		cn.commit();
	}

	/**
	 * ���[���o�b�N
	 */
	public void rollback() throws Exception  {
		cn.rollback();
	}

	/**
	 * �f�[�^�x�[�X�ւ̃R�l�N�V�����̃N���[�Y
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