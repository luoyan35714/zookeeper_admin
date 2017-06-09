import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

/**
 * Description:[For Wolfly] <br>
 * Date: 2017年6月9日 <br>
 * Copyright (c) 2017 Wolfly <br>
 * 
 * @author neusoft
 * @version V1.0
 */

/**
 * @Description: TODO <br>
 * @date 2017年6月9日 上午9:06:05 <br>
 * 
 * @author neusoft
 */
public class Test {

	public static void main(String[] args) throws Exception {
		System.out.println(DigestAuthenticationProvider.generateDigest("test:test"));
	}
}
