
import java.util.Scanner;

public class B1236 {
	public static void main(String args[]) 
	{
		Scanner sc = new Scanner(System.in);
		int row, col;
		row = sc.nextInt();
		col = sc.nextInt();
		char arr[][] = new char[row][col];
		boolean r[] = new boolean[row];
		boolean c[] = new boolean[col];
		int count_r = row;
		int count_c = col;
		for(int i = 0; i<row; i++)
		{	
			String str = sc.next();
			for(int j = 0 ; j<col; j++)
			{
				arr[i][j]=str.charAt(j);
			}
		}

		for(int k = 0; k<row; k++)
		{
			for(int l = 0; l<col; l++)
			{
				if(arr[k][l]=='X')
				{
					r[k]=true;
					c[l]=true;
				}
			}
		}
		// false ���� ����
		count_r = 0; count_c = 0;
		for (int i = 0; i<row; i++)
		{
			if(r[i]==false)
			{
				count_r++;
			}
			// �� ���̸�ŭ r[] ���� ���鼭 ���� false�̸� ++;
		}
			
		for (int j = 0; j<col; j++)
		{
			if(c[j]==false)
			{
				count_c++;
			}
			// �� ���̸�ŭ c[] ���� ���鼭 ���� false�̸� ++;
		}
		// ��?
		System.out.println(Math.max(count_r, count_c));
		sc.close();
	}

}
