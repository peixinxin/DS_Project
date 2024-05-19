import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WordCounter
{
	private String urlStr;
	private String content;

	public WordCounter(String urlStr)
	{
		this.urlStr = urlStr;
	}
	
	private String fetchContent() throws IOException {
	    URL url = new URL(this.urlStr);
	    URLConnection conn = url.openConnection();
	    InputStream in = conn.getInputStream();
	    BufferedReader br = new BufferedReader(new InputStreamReader(in));

	    StringBuilder retVal = new StringBuilder();
	    String line;

	    while ((line = br.readLine()) != null) {
	        retVal.append(line).append("\n");
	    }

	    return retVal.toString();
	}

	public int countKeyword(String keyword)
	{
		try {
	        if (content == null) {
	            content = fetchContent();
	        }

	        content = content.toUpperCase();
	        keyword = keyword.toUpperCase();

	        int retVal = 0;
	        int fromIdx = 0;
	        int found = -1;

	        while ((found = content.indexOf(keyword, fromIdx)) != -1) {
	            retVal++;
	            fromIdx = found + keyword.length();
	        }

	        return retVal;
	    } catch (IOException e) {
	        return 0; // 返回0表示計算失敗
	    }
	}
}