/**
 * @Description: TODO <br>
 * @date 2017年6月9日 上午9:06:05 <br>
 * 
 * @author Freud
 */
public class Test {

	public static void main(String[] args) throws Exception {
		int i = 0;
		while (true) {
			Thread.sleep(1000);
			System.out.println(++i);
		}
	}
}
