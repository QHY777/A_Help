package TipsOrTools;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;


/**
 * 獲得MP3文件的信息
 *
 */
public class MP3 {


	private String charset = "utf-8";//解析MP3信息時用的字符編碼

	private byte[] buf;//MP3的標簽信息的byte數組

	/**
	 * 實例化一個獲得MP3文件的信息的類
	 * @param mp3 MP3文件
	 * @throws IOException 讀取MP3出錯或則MP3文件不存在
	 */
	public MP3(File mp3) throws IOException{

		buf = new byte[128];//初始化標簽信息的byte數組

		RandomAccessFile raf = new RandomAccessFile(mp3, "r");//隨機讀寫方式打開MP3文件
		raf.seek(raf.length() - 128);//移動到文件MP3末尾
		raf.read(buf);//讀取標簽信息

		raf.close();//關閉文件

		if(buf.length != 128){//數據是否合法
			throw new IOException("MP3標簽信息數據長度不合法!");
		}


	}

	/**
	 * 獲得目前解析時用的字符編碼
	 * @return 目前解析時用的字符編碼
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * 設置解析時用的字符編碼
	 * @param charset 解析時用的字符編碼
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSongName(){
		try {
			return new String(buf,3,30,charset).trim();
		} catch (UnsupportedEncodingException e) {
			return new String(buf,3,30).trim();
		}
	}

	public String getArtist(){
		try {
			return new String(buf,33,30,charset).trim();
		} catch (UnsupportedEncodingException e) {
			return new String(buf,33,30).trim();
		}
	}

	public String getAlbum(){
		try {
			return new String(buf,63,30,charset).trim();
		} catch (UnsupportedEncodingException e) {
			return new String(buf,63,30).trim();
		}
	}

	public String getYear(){
		try {
			return new String(buf,93,4,charset).trim();
		} catch (UnsupportedEncodingException e) {
			return new String(buf,93,4).trim();
		}
	}

	public String getComment(){
		try {
			return new String(buf,97,28,charset).trim();
		} catch (UnsupportedEncodingException e) {
			return new String(buf,97,28).trim();
		}
	}

	public static void main(String[] args) {
		//TODO 演示
		File MP3FILE = new File("G:/test/母爱的光辉【小咪】第1集.mp3");
		try {
			MP3 info = new MP3(MP3FILE);
			info.setCharset("UTF-8");
			System.out.println(info.getSongName());
			System.out.println(info.getArtist());
			System.out.println(info.getAlbum());
			System.out.println(info.getYear());
			System.out.println(info.getComment());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
