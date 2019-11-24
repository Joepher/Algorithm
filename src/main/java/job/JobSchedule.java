package job;

import java.util.ArrayList;
import java.util.List;

class MyJob
{
	public int j_id; /* 定义作业ID */
	public int j_deadline; /* 定义作业期限 */
	public int j_benefit; /* 定义作业效益 */
}

public class JobSchedule
{
	private List<MyJob> jobList = new ArrayList<MyJob>();/* 作业列表 */
	private List<Boolean> visit = new ArrayList<Boolean>();/* 该时间段处理机是否空闲 */
	private List<Integer> processorlist = new ArrayList<Integer>();/* 处理机处理的作业编号 */
	private int maxBenefit;/* 最大效益 */

	/* 初始化所有作业 */
	private void JobInit()
	{
		int job_init[][] = { { 1, 1, 10 }, { 2, 2, 25 }, { 3, 3, 15 },
				{ 4, 1, 5 }, { 5, 2, 30 }, { 6, 3, 15 } };
		/* 将作业添加到作业列表中 */
		for (int i = 0; i < job_init.length; i++)
		{
			MyJob tmpJob = new MyJob();
			tmpJob.j_id = job_init[i][0];
			tmpJob.j_deadline = job_init[i][1];
			tmpJob.j_benefit = job_init[i][2];
			jobList.add(tmpJob);
		}
		/* 初始化所有时间段处理机均为可用状态 */
		for (int j = 0; j < jobList.size(); j++)
			visit.add(false);
		/* 初始化最大效益 */
		maxBenefit = 0;
	}

	/* 按作业效益降序进行排序 ：选择排序算法 */
	private void SortByBenefit()
	{
		for (int i = 0; i < jobList.size() - 1; i++)
		{
			for (int j = i + 1; j < jobList.size(); ++j)
			{
				if (jobList.get(i).j_benefit < jobList.get(j).j_benefit)
				{
					MyJob tmpJob = new MyJob();
					tmpJob = jobList.get(i);
					jobList.set(i, jobList.get(j));
					jobList.set(j, tmpJob);
				}
			}
		}
	}

	/* 作业调度 */
	private void JobScheduling()
	{

		for (int i = 0; i < jobList.size(); i++)
		{
			/* 如果当前作业截止期限时刻有可用处理机，则调度该作业进行处理 */
			if (!visit.get(jobList.get(i).j_deadline))
			{
				/* 设置当前时刻该处理机已被占用 */
				visit.set(jobList.get(i).j_deadline, true);
				/* 将该作业添加到已处理作业列表中 */
				processorlist.add(jobList.get(i).j_id);
				/* 更新已创造的最大效益 */
				maxBenefit += jobList.get(i).j_benefit;
			}
		}
	}

	/* 打印作业调度结果 */
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

	/* 打印作业 */
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
		js.JobInit();/* 作业初始化 */
		js.JobPrint();/* 打印作业 */
		js.SortByBenefit();/* 作业排序 */
		js.JobPrint();/* 打印作业 */
		js.JobScheduling();/* 作业调度 */
		js.JobSchedulingResult();/* 作业调度结果 */
	}
}
