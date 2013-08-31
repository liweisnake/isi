/*
 * Copyright (c) 2007 Hewlett-Packard Company, All Rights Reserved.
 *
 * RESTRICTED RIGHTS LEGEND Use, duplication, or disclosure by the U.S.
 * Government is subject to restrictions as set forth in sub-paragraph
 * (c)(1)(ii) of the Rights in Technical Data and Computer Software
 * clause in DFARS 252.227-7013.
 *
 * Hewlett-Packard Company
 * 3000 Hanover Street
 * Palo Alto, CA 94304 U.S.A.
 * Rights for non-DOD U.S. Government Departments and Agencies are as
 * set forth in FAR 52.227-19(c)(1,2).
 */
package skynet.isi;
import java.io.InputStream;
import java.io.InputStreamReader;

import junit.framework.TestCase;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import skynet.isi.utils.ConfigurationRepository;


@Component
class TestBaseConfigure {

	private String driverClass = ConfigurationRepository.pl.getProperty("jdbc.driver");//"com.mysql.jdbc.Driver";
	private String connectionUrl = ConfigurationRepository.pl.getProperty("jdbc.url");//"jdbc:mysql://localhost/mydb?useUnicode=true&characterEncoding=utf-8";
	private String username = ConfigurationRepository.pl.getProperty("jdbc.username");//Enums.PL.getProperty("jdbc.username");
	private String password = ConfigurationRepository.pl.getProperty("jdbc.password");//Enums.PL.getProperty("jdbc.password");
	
	// private String driverClass = "org.hsqldb.jdbcDriver";
	// private String connectionUrl = "jdbc:hsqldb:.";
	// private String username = "sa";
	// private String password = "";

	private boolean loadinitialdata = true;

	public boolean isLoadinitialdata() {
		return loadinitialdata;
	}

	public void setLoadinitialdata(boolean loadinitialdata) {
		this.loadinitialdata = loadinitialdata;
	}

	public String getConnectionUrl() {
		return connectionUrl;
	}

	public void setConnectionUrl(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

@ActiveProfiles("test")
public abstract class DBEnablerTestBase extends TestCase {
	
//	protected String webBasePath = "C:\\levi\\develop\\fsmsworkspace\\fsm\\src\\web\\";

	@Autowired
	protected TestBaseConfigure testBaseConfigure;

	protected IDatabaseTester databaseTester = null;

	public abstract String dataSetFileName();

	protected void onSetUp() {
	};

	protected void onTearDown() {
	};

	@Before
	public void setUp() throws Exception {
		// initialize your dataset here
		databaseTester = new JdbcDatabaseTester(
				testBaseConfigure.getDriverClass(),
				testBaseConfigure.getConnectionUrl(),
				testBaseConfigure.getUsername(),
				testBaseConfigure.getPassword());
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		if (testBaseConfigure.isLoadinitialdata()) {
			InputStream is = getClass().getResourceAsStream(dataSetFileName());
			InputStreamReader reader = new InputStreamReader(is, "utf-8");
			IDataSet dataSet = new XmlDataSet(reader);
			databaseTester.setDataSet(dataSet);
		} else {
			IDataSet dataSet = new DefaultDataSet();
			databaseTester.setDataSet(dataSet);
		}
		databaseTester.onSetup();
		onSetUp();

	}

	@After
	public void tearDown() throws Exception {
		databaseTester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
		onTearDown();

		// used to resolve the lazy initial problem

	}
}
