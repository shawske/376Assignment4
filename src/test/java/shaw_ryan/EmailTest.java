package shaw_ryan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmailTest {
	
	private static final String[] TEST_EMAILS = { "ab@bc.com", "a.b@c.org", "abcdefgjsjistesodjr@slkdosingosiend.com.bd" };
	private static final String[] BAD_EMAILS = {};
	//private String[] testValidChars = { " ", "a", "A", "\uc5ec", "0123456789", "01234567890123456789", "\n"}
	private static final String EMAIL_TEST = "ab@bc.com";
	private static final String NAME = "John";
	private static final String VALUE = "5";
	
	private Emailconcrete email;
	
	
	
	@Before
	public void setUpEmailTest() throws Exception {
		
		email = new Emailconcrete();
		
	}
	
	@After
	public void tearDownEmailTest() throws Exception{
		
	}
	
	
	
	
	//This is for the second 2 addBcc() methods, it just adds the email array and asserts the size. 100% coverage.
	@Test
	public void testAddBcc() throws Exception {
		
		email.addBcc(TEST_EMAILS);
		
		
		
		assertEquals(3, email.getBccAddresses().size());
	}
	
	//This is for the first addBcc() method by just calling it with a string email address. 100% coverage.
	@Test
	public void testAddBcc2() throws Exception {
		email.addBcc(EMAIL_TEST);
	}
	
	
	
	
	//This is also for the second 2 addCc() methods, by adding email array and asserting size. 100% coverage.
	@Test
	public void testAddCc() throws Exception {
		email.addCc(TEST_EMAILS);
		
		assertEquals(3, email.getCcAddresses().size());
		
		
	}
	
	//This is the first addCc() method by calling it with just a string email address. 100% coverage.
	@Test
	public void testAddCc2() throws Exception {
		email.addCc(EMAIL_TEST);
		
	}
	
	
	
	//This is the addHeader() method where I just created a name and value strings and inserted them. 78.3% coverage.
	@Test
	public void testAddHeader() throws Exception{
		email.addHeader(NAME, VALUE);
		String invalidName = null;
		String invalidValue = null;
		
		email.addHeader(invalidName, VALUE);
		email.addHeader(NAME, invalidValue);
	}
	
	
	
	//This is for the first 2 addReplyTo() methods, by just adding a string email and a string name, 100% coverage.
	@Test
	public void testAddReplyTo() throws Exception{
		email.addReplyTo(EMAIL_TEST, NAME);
		
	}
	
	//This is for the last addReplyTo() method, by just using the string email.  100% coverage
	@Test
	public void testAddReplyto2() throws Exception{
		email.addReplyTo(EMAIL_TEST);
	}
	
	
	
	
	//This is the buildMimeMessage() method, I added a new property and set a session instance to it.  Then I set all
	//	 the different attributes to test in the method, and then created a new mime message. This has only 62.1% coverage.
	@Test
	public void testBuildMimeMessage() throws Exception{
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", "Local");
		
		Session session = Session.getDefaultInstance(props);
		email.setMailSession(session);
		email.setSubject("Test Email");
		email.setMsg("This is a test");
		email.addTo(EMAIL_TEST);
		email.setFrom(EMAIL_TEST);
		email.addCc(EMAIL_TEST);
		email.addHeader(NAME, VALUE);
		email.addBcc(EMAIL_TEST);
		email.addReplyTo(EMAIL_TEST);
		email.setMsg(null);
		email.setContent(null);
		
		email.buildMimeMessage();
		MimeMessage message = email.getMimeMessage();
		
		assertNotNull(message);
		assertEquals("Test Email", message.getSubject());
		
		
	}
	
	
	//This is for the method getHostName, I originally call it, then I set host name and create a session
	//	and assert it isn't null then call it again.  This is 82.4% coverage
	@Test
	public void testGetHostName() throws Exception{
		email.getHostName();
		
		email.setHostName("Local");
		Session session = email.getMailSession();
		assertNotNull(session);
		email.getHostName();
	
		
	}
	
	
	
	//This is to test getMailSession() method, I set the host name, the smtp port, the debug and ssl on connect
	//	then I create a session and assert that it is not null.  This is 80.6% coverage
	@Test
	public void testGetMailSession() throws Exception{
		email.setHostName("Local");
		email.setSmtpPort(1000);
		email.setDebug(true);
		email.setSSLOnConnect(true);
		
		Session session = email.getMailSession();
		
		assertNotNull(session);
		
	}
	
	
	//Testing getSentDate for both if it is null or if there is a date already set. 100% coverage.
	@Test
	public void testGetSentDate() throws Exception{
		
		email.getSentDate();
		
		Date sentDate = new Date(16715);
		email.setSentDate(sentDate);
		assertNotNull(sentDate);
		email.getSentDate();
	}
	
	
	
	//This is testing the getSocketConnectionTimeout method. 100% coverage
	@Test
	public void testGetSocketConnectionTimeout() throws Exception{
		
		email.getSocketConnectionTimeout();
		
	}
	
	
	//This test is for all 3 setFrom() methods and is covering all three at the same time. The EMAIL_TEST is
	//	is used and is a string of an email.  100% coverage on all.
	@Test
	public void testSetFrom() throws Exception{
		
		email.setFrom(EMAIL_TEST);
		
		
		
	}

}
