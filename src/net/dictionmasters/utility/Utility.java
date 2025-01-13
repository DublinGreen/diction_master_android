package net.dictionmasters.utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.dictionmasters.config.Config;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.util.Log;

public class Utility extends Activity {

	/**
	 * @param encodedString
	 * @return bitmap (from given string)
	 */
	public static Bitmap StringToBitMap(String encodedString) {
		try {
			byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
			Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0,
					encodeByte.length);
			return bitmap;
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	/**
	 * Creates an xml from a resultset
	 * 
	 * @author green
	 * @param rootElement
	 * @param elementRow
	 * @param absPath
	 * @param fileName
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @throws ParserConfigurationException
	 * @throws TransformerConfigurationException
	 * @throws TransformerException
	 */
	public static boolean createResultSetXml(String rootElement,
			String elementRow, String absPath, String fileName, ResultSet rs)
			throws SQLException, ParserConfigurationException,
			TransformerConfigurationException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		Element results = doc.createElement(rootElement);
		doc.appendChild(results);

		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int colCount = rsmd.getColumnCount();

			while (rs.next()) {
				Element row = doc.createElement(elementRow);
				results.appendChild(row);
				for (int ii = 1; ii <= colCount; ii++) {
					String columnName = rsmd.getColumnName(ii);
					Object value = rs.getObject(ii);
					Element node = doc.createElement(columnName);
					node.appendChild(doc.createTextNode(value.toString()));
					row.appendChild(node);
				}
			}
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}

		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(absPath + fileName));
		transformer.transform(source, result);
		return true;
	}

	/**
	 * Makes a request and return the response
	 * 
	 * @author green
	 * @param myurl
	 * @return (downloaded url String)
	 * @throws IOException
	 */
	public static String downloadUrl(String myurl) throws IOException {
		InputStream is = null;
		int len = 200000;
		try {
			URL url = new URL(myurl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(15000);
			conn.setConnectTimeout(15000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.connect();
			// int response = conn.getResponseCode();
			is = conn.getInputStream();
			String contentAsString = readIt(is, len);
			return contentAsString;
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	/**
	 * Read the InputStream of the request
	 * 
	 * @param stream
	 * @param len
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @author green
	 */
	public static String readIt(InputStream stream, int len)
			throws IOException, UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		char[] buffer = new char[len];
		reader.read(buffer);
		return new String(buffer);
	}

	/**
	 * Check the Internet connection
	 * 
	 * @author green
	 * @param context
	 * @return
	 */
	public static Boolean checkWifiState(Context context) {
		try {
			if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.KITKAT) {
				ConnectivityManager connectivityManager = (ConnectivityManager) context
						.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo activeNetworkInfo = connectivityManager
						.getActiveNetworkInfo();
				return activeNetworkInfo.isConnected();
			}else{
				return true; // From lollipop 
			}
		} catch (Exception e) {
			return true;
		}
	}

	/**
	 * Logs a message with a tag
	 * 
	 * @author green
	 * @param message
	 */
	public static void log(String message) {
		Log.d(Config.LOG_TAG, message);
	}

}