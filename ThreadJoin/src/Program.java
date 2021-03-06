
import static java.lang.System.out;

public class Program {

	public static void main(String[] args) throws InterruptedException {
		
		 final Thread t0 = new Thread(() -> {
			for(int i=1; i<=100;i++)
							out.printf("%s : %d\n",Thread.currentThread().getName(), i);
		 });
		 
		 Thread t1 = new Thread(() -> {
			 
			try {
				t0.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int i=1; i<=100;i++)
							out.printf("%s : %d\n",Thread.currentThread().getName(), i);
		 });
		 
		 t0.start();
		 //Thread.sleep(1000);
		 t1.start();
		 
		 //t0.join(); // �����������, ��� ������� ����� ��������� ����������� �����  t0,t1
		 t1.join();
		 //Thread.sleep(1000);
		 
		 out.println(Thread.currentThread().getName());
	}

}
