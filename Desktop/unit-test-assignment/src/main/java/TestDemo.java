import java.util.Random;

public class TestDemo {
	public int addPositive(int a, int b) {
		int sum = 0;
		if (a > 0 && b > 0) {
			sum = a + b;
			return sum;
		}
		else
			throw new IllegalArgumentException("Both parameters must be positive!");	
	}
	public int randomNumberSquared() {
        int number = getRandomInt();
        return number * number;
    }

    int getRandomInt() {
        Random random = new Random();
        return random.nextInt(10) + 1;
    }
		
}
