package job;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class MyJob
{
	public int j_id; /* ������ҵID */
	public int j_deadline; /* ������ҵ���� */
	public int j_benefit; /* ������ҵЧ�� */
}

public class JobSchedule
{
	private List<MyJob> jobList = new ArrayList<MyJob>();/* ��ҵ�б� */
	private List<Boolean> visit = new ArrayList<Boolean>();/* ��ʱ��δ�����Ƿ���� */
	private List<Integer> processorlist = new ArrayList<Integer>();/* ������������ҵ��� */
	private int maxBenefit;/* ���Ч�� */

	/* ��ʼ��������ҵ */
	private void JobInit()
	{
		int job_init[][] = { { 1, 1, 10 }, { 2, 2, 25 }, { 3, 3, 15 },
				{ 4, 1, 5 }, { 5, 2, 30 }, { 6, 3, 15 } };
		/* ����ҵ��ӵ���ҵ�б��� */
		for (int i = 0; i < job_init.length; i++)
		{
			MyJob tmpJob = new MyJob();
			tmpJob.j_id = job_init[i][0];
			tmpJob.j_deadline = job_init[i][1];
			tmpJob.j_benefit = job_init[i][2];
			jobList.add(tmpJob);
		}
		/* ��ʼ������ʱ��δ������Ϊ����״̬ */
		for (int j = 0; j < jobList.size(); j++)
			visit.add(false);
		/* ��ʼ�����Ч�� */
		maxBenefit = 0;
	}

	/* ����ҵЧ�潵��������� ��ѡ�������㷨 */
	private void SortByBenefit()
	{
		jobList.sort(new Comparator<MyJob>() {
			@Override
			public int compare(MyJob o1, MyJob o2) {
				return  o1.j_benefit - o2.j_benefit;
			}
		});
	}

	/* ��ҵ���� */
	private void JobScheduling()
	{

		for (int i = 0; i < jobList.size(); i++)
		{
			/* �����ǰ��ҵ��ֹ����ʱ���п��ô����������ȸ���ҵ���д��� */
			if (!visit.get(jobList.get(i).j_deadline))
			{
				/* ���õ�ǰʱ�̸ô�����ѱ�ռ�� */
				visit.set(jobList.get(i).j_deadline, true);
				/* ������ҵ��ӵ��Ѵ�����ҵ�б��� */
				processorlist.add(jobList.get(i).j_id);
				/* �����Ѵ�������Ч�� */
				maxBenefit += jobList.get(i).j_benefit;
			}
		}
	}

	/* ��ӡ��ҵ���Ƚ�� */
	private void JobSchedulingResult()
	{
		int i = 0;
		System.out.println("********Scheduling Result********");
		while (i < processorlist.size())
		{
			System.out.print("Job[" + processorlist.get(i) + "]\t");
			i++;
		}
		System.out.println();
		System.out.println("The maxBenefit is " + maxBenefit);
	}

	/* ��ӡ��ҵ */
	private void JobPrint()
	{
		System.out.println("********Job Details********");
		System.out.println("j_id\t" + "j_deadline\t" + "j_benefit");
		for (int i = 0; i < jobList.size(); i++)
			System.out.println(jobList.get(i).j_id + "\t"
					+ jobList.get(i).j_deadline + "\t\t"
					+ jobList.get(i).j_benefit);
	}

	public static void main(String[] args)
	{
		JobSchedule js = new JobSchedule();
		js.JobInit();/* ��ҵ��ʼ�� */
		js.JobPrint();/* ��ӡ��ҵ */
		js.SortByBenefit();/* ��ҵ���� */
		js.JobPrint();/* ��ӡ��ҵ */
		js.JobScheduling();/* ��ҵ���� */
		js.JobSchedulingResult();/* ��ҵ���Ƚ�� */
	}
}
